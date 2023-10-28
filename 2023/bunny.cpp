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
const int DEBUG = false;

void solve()
{
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

    // find articulation points between
    vvi metagraph;
    vi id (n);
    int vid = 0;
    vi ecc_id (m);
    vvi eccs (n);

    auto buildmetagraph = [&] ()
    {
        vi used (m);
        int counter = 0;
        twoVCCs([&](const vi &edgelist){
            // do some counting
            for(int eid : edgelist)
            {
                eccs[edges[eid].first].push_back(counter);
                eccs[edges[eid].second].push_back(counter);
                used[eid] = true;
                ecc_id[eid] = counter;
            }
            counter++;
        });

        for(int i = 0; i < n; i++)
        {
            eccs[i].erase(unique(all(eccs[i])), eccs[i].end());
        }

        for(int i = 0; i < n; i++)
        {
            if(sz(eccs[i]) == 1) // in 1 vcc
            {
                id[i] = eccs[i].front();
            }
            else
            {
                id[i] = counter++;
            }
        }

        metagraph = vvi (counter);
        auto addEdge = [&] (int u, int v)
        {
            metagraph[u].push_back(v);
            metagraph[v].push_back(u);
        };
        for(int i = 0; i < m; i++)
        {
            pii e = edges[i];
            if(id[e.first] != ecc_id[i])
                addEdge(id[e.first], ecc_id[i]);
            if(id[e.second] != ecc_id[i])
                addEdge(id[e.second], ecc_id[i]);
        }

        for(int i = 0; i < sz(metagraph); i++)
        {
            sort(all(metagraph[i]));
            metagraph[i].erase(unique(all(metagraph[i])), metagraph[i].end());
        }
    };

    buildmetagraph();
    LCA lca (metagraph);

    int q; cin >> q;
    while(q--)
    {
        int s, t; cin >> s >> t; s--, t--;
        int dist = lca.dist(id[s], id[t]);
        if(!(dist & 1) && sz(eccs[s]) > 1 && sz(eccs[t]) > 1)
            dist--;
        dist >>= 1;
        dist += 2;
        cout << dist << nl;
    }
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    solve();
    return 0;
}