// Tree Number Generator
// Problem K
// 2021 ICPC Mid-Atlantic USA Regional Contest

// Problem Description
/*
    You are given a tree with single digit edge weights and a modulo m.  
    Then you are given q queries (q <= 2*10^5), where each query 
    provides a node u and a node v and asks you to print 
    the number created by walking the path from u to v modulo m.

    There are up to 2*10^5 nodes.
*/

// Solution Description
/*
    Use LCA (lowest common ancestor) to find the highest node in the path and
    to walk the path quickly from u to v
*/

#include <bits/stdc++.h>

using namespace std;

#define rep(i,a,b) for(int i=a;i<(b);++i)
#define all(x) begin(x), end(x)
#define sz(x) (int)(x).size()
typedef long long ll;
typedef vector<int> vi;
typedef deque<int> di;
typedef pair<int,int> pii;
typedef vector<ll> vl;
typedef vector<pii> vii;

ll MOD;
vi timerIn, timerOut, depth;
vector<vi> par, adj;
vector<vl> score, revScore;
vl digit, ten;
int logger, timer = 0;

int computeLog2(int n)
{
    if(n == 1) return 1;
    return 1 + computeLog2(n / 2);
}

bool is_anc(int u, int v)
{
    return timerIn[u] <= timerIn[v] && timerOut[u] >= timerOut[v];
}

int lca(int u, int v)
{
    if(is_anc(u, v)) return u;
    if(is_anc(v, u)) return v;

    for(int p = logger - 1; p >= 0; p--){
        if(!is_anc(par[u][p], v))
            u = par[u][p];
    }

    return par[u][0];
}

ll getVal(ll a, int offset, ll b)
{
    return (((a * ten[offset]) % MOD) + b) % MOD;
}

void dfs(int node, int parent)
{
    timerIn[node] = ++ timer;
    par[node][0] = parent;
    depth[node] = depth[parent] + 1;
    score[node][0] = revScore[node][0] = digit[node];

    for(int i = 1; i < logger; i++)
    {
        par[node][i] = par[par[node][i-1]][i-1];

        if(par[node][i] == par[node][i-1])
        {
            score[node][i] = score[node][i-1];
            revScore[node][i] = revScore[node][i-1];
        }

        else
        {
            score[node][i] = getVal(score[node][i-1], depth[par[node][i-1]] - depth[par[node][i]], score[par[node][i-1]][i-1]);
            
            revScore[node][i] = getVal(revScore[par[node][i-1]][i-1], depth[node] - depth[par[node][i-1]], revScore[node][i-1]);    // need to check that the minus one is correct
        }
    }

    for(int next : adj[node])
    {
        if(next == parent) continue;
        dfs(next, node);
    }

    timerOut[node] = ++ timer;
}

void init(int n)
{
    logger = computeLog2(n);
    par = vector<vi> (n, vi (logger));
    adj = vector<vi> (n);

    score = vector<vl> (n, vl (logger));
    revScore = vector<vl> (n, vl (logger));

    timerIn = vi (n);
    timerOut = vi (n);
    depth = vi (n);

    digit = vl (n);
    ten = vl (n);


    ten[0] = 1;
    for(int i = 1; i < ten.size(); i++)
    {   
        ten[i] = (10 * ten[i-1]) % MOD;
    }
}


ll getForward(int node, int anc)
{
    if(node == anc) return digit[node];

    ll val = 0;
    for(int p = logger - 1; p >= 0; p--){
        if(depth[anc] <= depth[par[node][p]] && node != par[node][p])
        {
            val = getVal(val, depth[node] - depth[par[node][p]], score[node][p]);
            node = par[node][p];
        }
    }

    return getVal(val, 1, digit[anc]);
}

ll getRev(int node, int anc)
{
    if(node == anc) return 0;

    int start = depth[node];
    ll val = 0;
    for(int p = logger - 1; p >= 0; p--){
        if(depth[anc] <= depth[par[node][p]] && node != par[node][p])
        {
            val = getVal(revScore[node][p], start - depth[node], val);
            node = par[node][p];
        }
    }

    return val;
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int n, q, u, v;
    cin >> n >> MOD >> q;
    init(n);

    for(int i = 1; i < n; i++)
    {
        cin >>u >> v;
        u--; v--;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    for(int i = 0; i < n ;i++)
    {
        cin >> u;
        digit[i] = u;
    }

    // do precomputation stuff, dfs
    dfs(0, 0);

    while(q--)
    {
        cin >>u >> v;
        u --; v--;

        int anc = lca(u, v);
        
        ll a = getForward(u, anc);
        ll b = getRev(v, anc);

        int d = depth[v] - depth[anc];

        cout << getVal(a, d, b) << endl;
    }

    return 0;
}