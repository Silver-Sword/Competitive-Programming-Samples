// Dynamic Connectivity
// CSES Problem Set
// https://cses.fi/problemset/task/2133

// Problem Description
/*
    Given an undirected graph with n nodes and m edges, process q queries.
    Queries are in one of two forms:
        1. Add an edge between node a and node b
        2. Remove an edge between node a and node b
    After each query, output the number of disjoint components in the graph.

    2 <= n <= 10^5
    1 <= m, q <= 10^5
*/

// Solution Description
/*
    Use a segment tree to store range of times that an edge is active.  Times represent
    the index of a query. 
    
    Then, dfs through the segment tree. At each node in the tree,
    add that node's edge set to the disjoint set when entering the node and rollback/
    remove that node's edge set when leaving the node.

    Output the number of components at each leaf in the segment tree.
*/

#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef pair<int, int> pii;
typedef vector<pii> vii;
#define sz(x)(int)(x).size()
#define nl '\n'

struct RollbackUF {
        vi e; vector<pii> st;
        RollbackUF(int n) : e(n, -1) {}
        int size(int x) { return -e[find(x)]; }
        int find(int x) { return e[x] < 0 ? x : find(e[x]); }
        int time() { return sz(st); }
        void rollback(int t) {
                for (int i = time(); i --> t;)
                        e[st[i].first] = st[i].second;
                st.resize(t);
        }
        bool join(int a, int b) {
                a = find(a), b = find(b);
                if (a == b) return false;
                if (e[a] > e[b]) swap(a, b);
                st.push_back({a, e[a]});
                st.push_back({b, e[b]});
                e[a] += e[b]; e[b] = a;
                return true;
        }
};

struct Tree
{
    int L, R, mid;
    vi on;
    Tree *left, *right;
    Tree(int l, int r)
    {
        L = l; R = r;
        mid = (L + R) / 2;

        if(L == R) return;
        left = new Tree(L, mid);
        right = new Tree(mid + 1, R);
    }
    void update(int l, int r, int idx)
    {
        if(L > r || l > R) return;
        if(L >= l && R <= r)
        {
            on.push_back(idx);
            return;
        }

        left->update(l, r, idx);
        right->update(l, r, idx);
    }
};

void solve()
{
    int n, m, k; cin >> n >> m >> k;
    RollbackUF dsu (n);
    Tree *root = new Tree(0, k);

    map<pii, int> idx;
    vii ed;
    vi timeOn;

    auto turnOn = [&] (int u, int v, int time)
    {
        if(idx.find({u, v}) == idx.end())
        {
            idx[{u, v}] = sz(idx);
            timeOn.push_back(-1);
            ed.push_back({u, v});
        }
        int id = idx[{u, v}];
        timeOn[id] = time;
    };

    auto turnOff = [&] (int u, int v, int time)
    {
        int id = idx[{u, v}];
        int s = timeOn[id];
        root->update(s, time, id);

        timeOn[id] = -1;
    };

    int u, v, op;
    for(int i = 0; i < m; i++)
    {
        cin >> u >> v;
        u--; v--;
        if(u > v) swap(u, v);
        turnOn(u, v, 0);
    }

    for(int i = 1; i <= k; i++)
    {
        cin >> op >> u >> v;
        u--; v--;
        if(u > v) swap(u, v);

        if(op == 1)
        {
            turnOn(u, v, i);
        }
        else
        {
            turnOff(u, v, i-1);
        }
    }

    for(int i = 0; i < sz(timeOn); i++)
        if(timeOn[i] != -1)
            turnOff(ed[i].first, ed[i].second, k);

    auto dfs = [&] (Tree *cur, auto &&dfs) -> void
    {
        int cur_time = dsu.time();
        // turn everything on
        for(int e : cur->on)
            dsu.join(ed[e].first, ed[e].second);

        if(cur->L == cur->R)
        {
            if(cur->L != 0) cout << " ";
            cout << (n - dsu.time() / 2);
        }
        else
        {
            dfs(cur->left, dfs);
            dfs(cur->right, dfs);
        }

        dsu.rollback(cur_time);
    };

    // dfs
    dfs(root, dfs);
    cout << nl;
}
int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    solve();
    return 0;
}