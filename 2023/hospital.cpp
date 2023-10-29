// Hospital
// Northern Subregional 2012-2013

// Problem Description
/*
    There are two kinds of nurses: staff nurses and special nurses.
    If special nurses go on vacation, their role must be filled, but
    only some nurses have the training and are able to fill the vacancy.  
    Special nurses may substitute for other special nurses, as long as
    the end result leaves no special role unfilled.

    You are given a set of n nurses, k of which are special nurses, and
    a set of valid substitutions for each special nurse. 

    First, determine which special nurses cannot go on vacation.

    Then, determine which pairs of nurses can each go on vacation, but not
    at the same time.

    1 <= k < n <= 1000
*/

// Solution Description
/*
    To determine which special nurses cannot go on vacation,
    run a dfs from all staff nurses and mark all visited nodes.
    Any special nurse that is not marked during this pass must not have a 
    valid substitution.

    To determine if two nurses cannot go on vacation at the same time find the 
    dominators of both nurses.  In other words, find the nurses that must be used
    for any/every possible substitution chain for a nurse.  If two nurses share a 
    dominating nurse, then they *cannot* go on vacation at the same time, because 
    otherwise, they would both require a nurse be moved to a different job.

    Find the shared dominators using a dominator tree and the lower common ancestor
    algorithm.
*/

#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef vector<vi> vvi;
typedef long long ll;
typedef pair<int, int> pii;
typedef vector<pii> vii;

#define nl '\n'
#define sz(x) (int)(x).size()
#define rep(i,a,b) for(int i = a; i < (b); i++)

void assertBad(int state)
{
    if(!state)
    {
        int a = 0, mod = 1e9 + 7;
        while(true)
            a = (a + 1) % mod;
    }
}
template<class T>
struct RMQ {
        vector<vector<T>> jmp;
        RMQ(const vector<T>& V) : jmp(1, V) {
                for (int pw = 1, k = 1; pw * 2 <= sz(V); pw *= 2, ++k) {
                        jmp.emplace_back(sz(V) - pw * 2 + 1);
                        rep(j,0,sz(jmp[k]))
                                jmp[k][j] = min(jmp[k - 1][j], jmp[k - 1][j + pw]);
                }
        }
        T query(int a, int b) {
                assertBad(a < b); // or return inf if a == b
                int dep = 31 - __builtin_clz(b - a);
                return min(jmp[dep][a], jmp[dep][b - (1 << dep)]);
        }
};

struct LCA {
        int T = 0;
        vi time, path, ret;
        RMQ<int> rmq;

        LCA(vector<vi>& C) : time(sz(C)), rmq((dfs(C,0,-1), ret)) {}
        void dfs(vector<vi>& C, int v, int par) {
                time[v] = T++;
                for (int y : C[v]) if (y != par) {
                        path.push_back(v), ret.push_back(time[v]);
                        dfs(C, y, v);
                }
        }

        int lca(int a, int b) {
                if (a == b) return a;
                tie(a, b) = minmax(time[a], time[b]);
                return path[rmq.query(a, b)];
        }
};

int onPath[1001];
struct DominatorTree {
        vector<vi> adj,
                ans; // input edges, edges of dominator tree (directed tree downwards from root)
        vector<vi> radj, child, sdomChild;
        vi label, rlabel, sdom, dom;
        vi par, bes;
        int co = 0;
        DominatorTree(int n) {
        n += n;
                adj = ans = radj = child = sdomChild =
                        vector<vi>(n);
                label = rlabel = sdom = dom = par = bes = vi(n);
        }
        void add_edge(int a, int b) {
        adj[a].push_back(b); }
        int get(int x) {
                if (par[x] != x) {
                        int t = get(par[x]);
                        par[x] = par[par[x]];
                        if (sdom[t] < sdom[bes[x]]) bes[x] = t;
                }
                return bes[x];
        }
        void dfs(int x) { // create DFS tree
                onPath[x] = true; // added
                label[x] = ++co;
                rlabel[co] = x;
                sdom[co] = par[co] = bes[co] = co;
                for (auto y : adj[x]) {
                        if (!label[y]) {
                                dfs(y);
                                child[label[x]].push_back(label[y]);
                        }
                        radj[label[y]].push_back(label[x]);
                }
        }
        void init(int root) {
                dfs(root);
                for (int i = co; i >= 1; --i) {
                        for (auto j : radj[i])
                                sdom[i] = min(sdom[i], sdom[get(j)]);
                        if (i > 1) sdomChild[sdom[i]].push_back(i);
                        for (auto j : sdomChild[i]) {
                                int k = get(j);
                                if (sdom[j] == sdom[k]) dom[j] = sdom[j];
                                else dom[j] = k;
                        }
                        for (auto j : child[i]) par[j] = i;
                }
                for (int i = 2; i < co + 1; ++i) {
                        if (dom[i] != sdom[i]) dom[i] = dom[dom[i]];
                        ans[rlabel[dom[i]]].push_back(rlabel[i]);
                }
        }
};

void solve()
{
    int n, k; cin >> n >> k;
    DominatorTree dt (n+1);

    int u, v;
    vii edge;
    for(int i = k + 1; i <= n; i++) dt.add_edge(0, i);
    for(int i = 0; i <= n; i++) onPath[i] = 0;
    for(int i = 1; i <= k;i ++)
    {
        int m; cin >> m;
        while(m--)
        {
            cin >> u;
            dt.add_edge(u, i);
        }
    }
    dt.init(0);

    LCA lca (dt.ans);

    vi invalid;
    for(int i = 1; i <= n; i++)
    {
        if(!onPath[i]) invalid.push_back(i);
    }

    cout << sz(invalid) << nl;
    for(int i : invalid) cout << i << " ";
    cout << nl; // assumes this is fine even if invalid has nothing

    vii pr;
    for(int i = 1; i <= k; i++)
    {
        if(!onPath[i]) continue;
        for(int j = i + 1; j <= n; j++)
        {
            if(!onPath[j]) continue;
            if(lca.lca(i, j) != 0)
            {
                pr.push_back({i, j});
            }
        }
    }

    cout << sz(pr) << nl;
    if(sz(pr) <= 10'000)
    {
        for(pii p : pr) cout << p.first << " " << p.second << nl;
    }
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    freopen("hospital.in", "r", stdin);
    freopen("hospital.out", "w", stdout);
    cin.exceptions(cin.failbit);
    solve();
    return 0;
}