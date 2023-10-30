// Bad Bunny
// UCF Local Contest 2023

// Problem Description
/*
    Given an undirected graph with n nodes and m edges, process q queries.
    Each query is in the form (a, b) and asks: how many nodes are on every 
    path from node a to node b (including a and b)?

    1 <= n <= 10^5
    n-1 <= m <= 2*10^5
    1 <= q <= 10^5
*/

// Solution Description
/*
    Find all the VCCs.  Create a metagraph where each node is either a VCC
    or a vertex that is in multiple VCCs. Edges should be added between nodes/groups
    that are adjacent in the original graph.  To get the number of nodes that
    are on every path between a and b, find the distance between a and b in the 
    metagraph.

    O(m + (n+q) log n)
*/

/* Template Code and Program Setup */
#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef vector<vi> vvi;
typedef pair<int, int> pii;
typedef vector<pii> vii;

#define sz(x) (int)(x).size()
#define rep(i,a,b) for(int i = a; i < (b); i++)
#define nl '\n'
#define all(x) begin(x), end(x)

/* Hackpack */
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
                assert(a < b); // or return inf if a == b
                int dep = 31 - __builtin_clz(b - a);
                return min(jmp[dep][a], jmp[dep][b - (1 << dep)]);
        }
};

struct LCA {
        int T = 0;
        vi time, path, ret, depth;
        RMQ<int> rmq;
    int dist(int a, int b){return depth[a] + depth[b] - 2*depth[lca(a,b)];}

        LCA(vector<vi>& C) : time(sz(C)), depth(sz(C)), rmq((dfs(C,0,-1, 0), ret)) {}
        void dfs(vector<vi>& C, int v, int par, int height) {
        depth[v] = height;
                time[v] = T++;
                for (int y : C[v]) if (y != par) {
                        path.push_back(v), ret.push_back(time[v]);
                        dfs(C, y, v, height+1);
                }
        }

        int lca(int a, int b) {
                if (a == b) return a;
                tie(a, b) = minmax(time[a], time[b]);
                return path[rmq.query(a, b)];
        }
};

vi num, st;
vector<vector<pii>> ed;
int Time;
template<class F>
int dfs(int at, int par, F& f) {
        int me = num[at] = ++Time, e, y, top = me;
        for (auto pa : ed[at]) if (pa.second != par) {
                tie(y, e) = pa;
                if (num[y]) {
                        top = min(top, num[y]);
                        if (num[y] < me)
                                st.push_back(e);
                } else {
                        int si = sz(st);
                        int up = dfs(y, e, f);
                        top = min(top, up);
                        if (up >= me) {
                                st.push_back(e);
                                f(vi(st.begin() + si, st.end()));
                                st.resize(si);
                        }
                        else st.push_back(e);
                }
        }
        return top;
}

template<class F>
void twoVCCs(F f) {
        num.assign(sz(ed), 0);
        rep(i,0,sz(ed)) if (!num[i]) dfs(i, -1, f);
}

/* Solution Code */
void solve()
{
    // input
    int n, m, u, v; cin >> n >> m;
    ed.resize(n);
    vii edges;
    for(int i = 0; i < m; i++)
    {
        cin >> u >> v;
        u--; v--;
        ed[u].push_back({v, i});
        ed[v].push_back({u, i});
        edges.push_back({u, v});
    }

    vvi metagraph;
    vi id (n);
    int vid = 0;
    vi vcc_id (m);
    vvi vccs (n);

    // build the meta graph of vccs and articulation points
    auto buildmetagraph = [&] ()
    {
        vi used (m);
        int counter = 0;
        twoVCCs([&](const vi &edgelist){
            // set all vcc ids
            for(int eid : edgelist)
            {
                vccs[edges[eid].first].push_back(counter);
                vccs[edges[eid].second].push_back(counter);
                used[eid] = true;
                vcc_id[eid] = counter;
            }
            counter++;
        });

        for(int i = 0; i < n; i++)
        {
            vccs[i].erase(unique(all(vccs[i])), vccs[i].end());
        }

        // figure out which nodes are articulation points
        for(int i = 0; i < n; i++)
        {
            if(sz(vccs[i]) == 1) // in 1 vcc
            {
                id[i] = vccs[i].front();
            }
            else
            {
                id[i] = counter++;
            }
        }

        // add articulation points
        metagraph = vvi (counter);
        auto addEdge = [&] (int u, int v)
        {
            metagraph[u].push_back(v);
            metagraph[v].push_back(u);
        };
        for(int i = 0; i < m; i++)
        {
            pii e = edges[i];
            if(id[e.first] != vcc_id[i])
                addEdge(id[e.first], vcc_id[i]);
            if(id[e.second] != vcc_id[i])
                addEdge(id[e.second], vcc_id[i]);
        }

        for(int i = 0; i < sz(metagraph); i++)
        {
            sort(all(metagraph[i]));
            metagraph[i].erase(unique(all(metagraph[i])), metagraph[i].end());
        }
    };

    buildmetagraph();
    LCA lca (metagraph);

    // process the queries
    int q; cin >> q;
    while(q--)
    {
        int s, t; cin >> s >> t; s--, t--;
        // compute the distance between the s and t nodes on the lca graph
        int dist = lca.dist(id[s], id[t]);
        if(!(dist & 1) && sz(vccs[s]) > 1 && sz(vccs[t]) > 1)
            dist--;
        dist >>= 1;
        dist += 2;
        cout << dist << nl;
    }
}

// driver function
int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    solve();
    return 0;
}