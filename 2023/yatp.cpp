#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef vector<ll> vl;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef pair<int, int> pii;
typedef vector<ll> vl;

#define sz(x)(int)(x).size()
#define nl '\n'
#define all(x) begin(x), end(x)

vector<vector<pii>> adj;
vector<bool> is_removed;
vector<int> subtree_size;

const int DEBUG = false;

struct Line {
        mutable ll k, m, p;
        bool operator<(const Line& o) const { return k < o.k; }
        bool operator<(ll x) const { return p < x; }
};

struct LineContainer : multiset<Line, less<>> {
        // (for doubles, use inf = 1/.0, div(a,b) = a/b)
        static const ll inf = LLONG_MAX;
        ll div(ll a, ll b) { // floored division
                return a / b - ((a ^ b) < 0 && a % b); }
        bool isect(iterator x, iterator y) {
                if (y == end()) return x->p = inf, 0;
                if (x->k == y->k) x->p = x->m > y->m ? inf : -inf;
                else x->p = div(y->m - x->m, x->k - y->k);
                return x->p >= y->p;
        }
        void add(ll k, ll m) {
        k = -k; m = -m;
                auto z = insert({k, m, 0}), y = z++, x = y;
                while (isect(y, z)) z = erase(z);
                if (x != begin() && isect(--x, y)) isect(x, y = erase(y));
                while ((y = x) != begin() && (--x)->p >= y->p)
                        isect(x, erase(y));
        }
        ll query(ll x) {
                assert(!empty());
                auto l = *lower_bound(x);
                return -(l.k * x + l.m);
        }
};

/** DFS to calculate the size of the subtree rooted at `node` */
int get_subtree_size(int node, int parent = -1)
{
    subtree_size[node] = 1;
    for (pii &next : adj[node]) {
        int child = next.first;
         if (child == parent || is_removed[child]) { continue; }
         subtree_size[node] += get_subtree_size(child, node);
    }
    return subtree_size[node];
}

/**
 * Returns a centroid (a tree may have two centroids) of the subtree
 * containing node `node` after node removals
 * @param node current node
 * @param tree_size size of current subtree after node removals
 * @param parent parent of u
 * @return first centroid found
 */
int get_centroid(int node, int tree_size, int parent = -1)
{
    for (pii &next : adj[node]) {
        int child = next.first;
         if (child == parent || is_removed[child]) { continue; }
         if (subtree_size[child] * 2 > tree_size) {
                 return get_centroid(child, tree_size, node);
         }
    }
    return node;
}
/** Build up the centroid decomposition recursively */
vl ans;
void build_centroid_decomp(int node, vl &pen)
{
    int centroid = get_centroid(node, get_subtree_size(node));
    if(DEBUG) cout << "\tbuild centroid decomp(node=" << centroid << ")" << nl;

    // do something
    LineContainer cht;
    auto dfs1 = [&] (int cur, int par, ll depth, auto&& dfs1) -> void
    {
        cht.add(pen[cur], depth);
        if(DEBUG) cout << "\t\t\tupdate line with m=" << pen[cur] << ", b=" << depth << nl;

        for(pii next : adj[cur])
        {
            if(next.first == par || is_removed[next.first]) continue;
            dfs1(next.first, cur, depth + next.second, dfs1);
        }
    };
    dfs1(centroid, centroid, 0LL, dfs1);

    auto dfs2 = [&] (int cur, int par, ll depth, auto &&dfs2) -> void
    {
        ans[cur] = min(ans[cur], cht.query(pen[cur]) + depth);
        if(DEBUG) cout << "\t\tupdate ans[node=" << cur << "] with candidate " << cht.query(pen[cur]) << " + " << depth << nl;

        for(pii &next : adj[cur])
        {
            if(next.first == par || is_removed[next.first]) continue;
            dfs2(next.first, cur, depth + next.second, dfs2);
        }
    };
    dfs2 (centroid, centroid, 0LL, dfs2);

    is_removed[centroid] = true;
    for (pii next : adj[centroid]) {
        int child = next.first;
            if (is_removed[child]) { continue; }
            build_centroid_decomp(child, pen);
    }
}

vl pen;
void solve()
{
    int n; cin >> n;
    pen = vl (n);
    for(int i = 0; i < n; i++) cin >> pen[i];

    adj = vector<vector<pii>> (n);
    int u, v, w;
    for(int i = 1; i < n; i++)
    {
        cin >> u >> v >> w;
        u--; v--;
        adj[u].push_back({v, w});
        adj[v].push_back({u, w});
    }

    ans = vl (n, 1e18);
    subtree_size = vi (n);
    is_removed = vector<bool> (n);
    build_centroid_decomp(0, pen);

    ll total = 0;
    for(ll a : ans) total += a;
    cout << total << nl;
}
int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    solve();
    return 0;
}