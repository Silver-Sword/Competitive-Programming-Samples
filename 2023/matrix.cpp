#pragma GCC optimize("O3,unroll-loops")
#pragma GCC target("avx2,bmi,bmi2,lzcnt,popcnt")

#include <bits/stdc++.h>
using namespace std;

#define sz(x) (int)(x).size()
#define all(x) begin(x), end(x)
#define nl '\n'

typedef long long ll;
typedef vector<ll> vl;
typedef vector<int> vi;

int K;

const int MAXN = 20;
int rowfull[MAXN], colfull[MAXN];

struct Tree
{
    int L, R, mid;
    Tree *left, *right;
    int *ptr;
    int on, depth;

    Tree(int l, int r, int d, int *p) : L(l), R(r), depth(d), ptr(p), on (0), left (NULL), right (NULL)
    {
        mid = (L + R) / 2;
        
        if(L != R) 
        {
            left = new Tree(l, mid, d+1, p);
            right = new Tree(mid+1, r, d+1, p);
        }

        ptr[d]++;
    }
    void update(int idx, int dif)
    {
        if(L > idx || R < idx) return;

        if(!on || on == R - L + 1) ptr[depth]--;
        on += dif;
        if(!on || on == R - L + 1) ptr[depth]++;

        if(L != R)
        {
            left->update(idx, dif);
            right->update(idx, dif);
        }
    }
};

const int MAX = (1 << 20) + 1;
int rowstate[MAX], colstate[MAX];
void solve()
{
    int n, q; cin >> n >> q;
    K = n;
    Tree row (1, (1 << n), 0, &rowfull[0]), col (1, (1 << n), 0, &colfull[0]);
    auto calc = [&] ()
    {
        ll prev = 0, ans = 0, fact = 1;

        for(int i = 0; i <= n; i++)
        {
            ans += fact;
            fact *= 4;
        }

        for(int i = 0; i < n; i++)
        {
            prev = rowfull[i] * (ll) colfull[i] * 4LL;
            ans -= prev;
        }
        return ans;
    };

    int t, id;
    while(q--)
    {
        cin >> t >> id;
        if(t) 
        {
            col.update(id, colstate[id]);
            colstate[id] = -colstate[id];
        }
        else
        {
            row.update(id, rowstate[id]);
            rowstate[id] = -rowstate[id];
        }

        cout << calc() << nl;
    }
}
int main()
{
    for(int i = 0; i < MAX; i++) rowstate[i] = colstate[i] = 1;
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    solve();
    return 0;
}