// Points and Rectangles
// Southeastern European Regionals 2018

// Problem Description
/*
    There are q queries. There are 2 types of queries:
    1. add a point (x, y)
    2. add a rectangle (x1, y1), (x2, y2)

    After each query, output the number of pairs of points and rectangles such that
    the point lies on or within the rectangle.

    1 <= q <= 10^5
    1 <= x, y <= 10^9

    O(q * log^2(q))
*/

// Solution Description
/*
    Use 2 2D implicit binary index trees to keep track of the existing points and 
    rectangles.  Points represent index queries/updates and rectangles are
    range queries/updates.
*/

#include <bits/stdc++.h>
using namespace std;

#define sz(x) (int)(x).size()
#define all(x) begin(x), end(x)
#define nl '\n'
typedef long long ll;
typedef vector<int> vi;
typedef vector<vi> vvi;

struct FT {
	vector<ll> s;
	FT(int n) : s(n) {}
	void update(int pos, ll dif) { // a[pos] += dif
		for (; pos < sz(s); pos |= pos + 1) s[pos] += dif;
	}
	ll query(int pos) { // sum of values in [0, pos)
		ll res = 0;
		for (; pos > 0; pos &= pos - 1) res += s[pos-1];
		return res;
	}
	int lower_bound(ll sum) {// min pos st sum of [0, pos] >= sum
		// Returns n if no sum is >= sum, or -1 if empty sum is.
		if (sum <= 0) return -1;
		int pos = 0;
		for (int pw = 1 << 25; pw; pw >>= 1) {
			if (pos + pw <= sz(s) && s[pos + pw-1] < sum)
				pos += pw, sum -= s[pos-1];
		}
		return pos;
	}
};

struct FT2 {
	vector<vi> ys; vector<FT> ft;
	FT2(int limx) : ys(limx) {}
	void fakeUpdate(int x, int y) {
		for (; x < sz(ys); x |= x + 1) ys[x].push_back(y);
	}
	void init() {
		for (vi& v : ys) sort(all(v)), ft.emplace_back(sz(v));
	}
	int ind(int x, int y) {
		return (int)(lower_bound(all(ys[x]), y) - ys[x].begin()); }
	void update(int x, int y, ll dif) {
		for (; x < sz(ys); x |= x + 1)
			ft[x].update(ind(x, y), dif);
	}
	ll query(int x, int y) {
		ll sum = 0;
		for (; x; x &= x - 1)
			sum += ft[x-1].query(ind(x-1, y));
		return sum;
	}
};

void solve()
{
    int q; cin >> q;

    vvi query (q);
    vvi p (2);
    for(int i = 0; i < q; i++)
    {
        int type; cin >> type;
        if(type == 1)
            query[i] = vi (2);
        else
            query[i] = vi (4);
        
        for(int j = 0; j < sz(query[i]); j++)
        {
            cin >> query[i][j];
            p[j&1].push_back(query[i][j]);
        }
    }

    // coord compress
    vector<map<int, int>> key (2);
    for(int i = 0; i < 2; i++)
    {
        sort(all(p[i]));
        for(int j = 0; j < sz(p[i]); j++)
        {
            if(j != 0 && p[i][j] == p[i][j-1]) continue;
            key[i][p[i][j]] = sz(key[i]);
        }
    }

    for(int i = 0; i < q; i++)
    {
        for(int j = 0; j < sz(query[i]); j++)
        {
            query[i][j] = key[j&1][query[i][j]];
        }
    }

    // bit
    FT2 pt (sz(p[0]) + 2);
    FT2 rect (sz(p[0]) + 2);

    for(int i = 0; i < q; i++)
    {
        if(sz(query[i]) == 2)
            pt.fakeUpdate(query[i][0], query[i][1]);
        else
        {
            rect.fakeUpdate(query[i][0], query[i][1]);
            rect.fakeUpdate(query[i][0], query[i][3]+1);
            rect.fakeUpdate(query[i][2]+1, query[i][1]);
            rect.fakeUpdate(query[i][2]+1, query[i][3]+1);
        }
    }

    pt.init();
    rect.init();

    ll ans = 0;
    for(int id = 0; id < q; id++)
    {
        if(sz(query[id]) == 2) // point
        {
            int x = query[id][0], y = query[id][1];
            
            // query 
            ans += rect.query(x+1, y+1);
            cout << ans << nl;

            // update
            pt.update(x,y,1);
        }
        else // rectangle
        {
            int xs = query[id][0], ys = query[id][1], xe = query[id][2], ye = query[id][3];
            
            // query
            ans += pt.query(xe+1, ye+1) 
                 - pt.query(xe+1, ys)
                 - pt.query(xs, ye+1)
                 + pt.query(xs, ys);
            cout << ans << nl;

            // update
            rect.update(xs, ys, 1);
            rect.update(xs, ye+1, -1);
            rect.update(xe+1, ys, -1);
            rect.update(xe+1,ye+1, 1);
        }
    }
}
int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    solve();
    return 0;
}