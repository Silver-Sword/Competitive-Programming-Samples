// GPS on a Flat Earth
// Latin American Regionals 2023-2024

// Problem Description
/*
	Given a set of n towers locations (x, y) and their Manhattan 
	distances to a target point, d,  determine how many valid 
	target points exist.

	The Manhattan distance between points (x1, y1) and (x2, y2) 
	is equal to | x1 - x2 | + | y1 - y2 |.

	1 <= n <= 10^5
	|x|, |y| <= 10^4
	0 <= d <= 4*10^4
*/

// Solution Description
/*
	Observation: Manhattan distance makes a perfect diamond around a center point.

	Using the rotation trick, convert the diamonds of a Manhattan distance to
	axis-aligned squares.

	Sweep through the squares, ordered by their y-values.  Treat each top of the square
	as an add range, and each square bottom as a subtract range.  Additionally, because
	the Manhattan distances are exact, include a second square in the sweep that "cuts out"
	the inside of the full square.  In other words, subtract out a reduced range after the top
	and add that reduced range before the bottom.

	For each range of y values between sweep events, extract all valid x ranges
	from the segment tree, where all n squares intersect.
*/

#pragma GCC optimize("O3,unroll-loops")
#pragma GCC target("avx2,bmi,bmi2,lzcnt,popcnt")
#include <bits/stdc++.h>
using namespace std;

#define nl '\n'
#define sz(x) (int)(x).size()
#define rep(i,a,b) for(int i = a; i < (b); i++)
#define all(x) begin(x), end(x)
typedef vector<int> vi;
typedef long long ll;
typedef pair<int, int> pii;
typedef vector<vi> vvi;

const int MAX = 1e5, OFFSET = MAX;

inline int split(int tl, int tr) {
	int pw2 = 1 << __lg(tr - tl);
	return min(tl + pw2, tr - pw2 / 2);
}
inline int op(int le, int ri) {
	return max(le, ri);
}
struct seg_tree {
	int n, pw2;
	vector<int> tree, lazy;
	seg_tree(int a_n) : n(a_n), pw2(n ? 1 << __lg(2 * n - 1) : 0), tree(2 * n), lazy(n) {}
	inline void apply(int change, int u) {
		tree[u] += change;
		if (u < n) lazy[u] += change;
	}
	inline void push(int u) {
		if (lazy[u]) {
			apply(lazy[u], 2 * u);
			apply(lazy[u], 2 * u + 1);
			lazy[u] = 0;
		}
	}
	void update(int le, int ri, int change) {
		ri++;
		return update(le, ri, change, 0, n, 1);
	}
	void update(int le, int ri, int change, int tl, int tr, int u) {
		if (ri <= tl || tr <= le) return;
		if (le <= tl && tr <= ri) return apply(change, u);
		int tm = split(tl, tr);
		push(u);
		update(le, ri, change, tl, tm, 2 * u);
		update(le, ri, change, tm, tr, 2 * u + 1);
		tree[u] = op(tree[2 * u], tree[2 * u + 1]);
	}
	void query(int mx, vector<int> & ret) {
		if (tree[1] != mx)
			return;
		return query(0, n, 1, ret);
	}
	void query(int tl, int tr, int u, vector<int> & ret) {
		if (tl == tr - 1) {
			ret.push_back(tl);
			return;
		}
		int tm = split(tl, tr);
		push(u);
		if (tree[2 * u] == tree[u])
			query(tl, tm, 2 * u, ret);
		if (tree[2 * u + 1] == tree[u])
			query(tm, tr, 2 * u + 1, ret);
	}
};

vvi getSquare(int x, int y, int d)
{
	vvi sq (4, vi (2));

	sq[0] = {x, y - d};
	sq[1] = {x - d, y};
	sq[2] = {x + d, y};
	sq[3] = {x, y + d};

	return sq;
}

void solve()
{
	int n; cin >> n;
	vector<pair<pii, pii>> q;
	q.reserve(4 * n);

	int x, y, d;
	for(int i = 0; i < n; i++)
	{
		cin >> x >> y >> d;
		vector<vi> sq = getSquare(x, y, d);

		int x1 = sq[0][0] + sq[0][1];
		int x2 = sq[3][0] + sq[3][1];
		int y1 = sq[0][0] - sq[0][1];
		int y2 = sq[3][0] - sq[3][1];

		if(x1 > x2) swap(x1, x2);
		if(y1 > y2) swap(y1, y2);

		q.push_back({{y1, 1}, {x1, x2}});
		q.push_back({{y2+1, -1}, {x1, x2}});

		if(y1 + 1 < y2 && x1 + 1 < x2)
		{
			q.push_back({{y1+1, -1}, {x1+1, x2-1}});
			q.push_back({{y2, 1}, {x1+1, x2-1}});
		}
	}
	sort(all(q));

	// sweep
	seg_tree tree (MAX+MAX+1);

	vector<pii> ans;
	ans.reserve(16'000);
	auto convert = [&] (int i, int j) -> pii
	{
		int x = (i+j)/2;
		int y = i - x;

		return {x, y};
	};
	auto extract = [&] (int y1, int y2)
	{
		vi x;
		tree.query(n, x);
		if(x.empty()) return;

		for(int i = y1; i < y2; i++)
			for(int j : x)
			{
				if((i&1)^(j&1)) continue;
				pii pt = convert(j-OFFSET, i);
				ans.push_back(pt);
			}

	};
	int prev_y = -MAX-MAX;
	for (int fr = 0; fr < sz(q); )
	{
		// update with y
		int y = q[fr].first.first;
		int delta_y = y - prev_y;
		// ans += delta_y * computeFull();

		extract(prev_y, y);

		while(fr < sz(q) && q[fr].first.first == y)
		{
			int dif = q[fr].first.second;
			int s = q[fr].second.first;
			int e = q[fr].second.second;
			fr++;

			tree.update(s+OFFSET, e+OFFSET, dif);
		}

		prev_y = y;
	}
	sort(all(ans));
	for(pii p : ans)
		cout << p.first << " " << p.second << nl;
}

int main()
{
	cin.tie(0)->sync_with_stdio(0);
	solve();
	return 0;
}