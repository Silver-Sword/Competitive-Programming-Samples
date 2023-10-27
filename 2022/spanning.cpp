// Red/Blue Spanning Tree
// Problem J
// 2012 University of Chicago Invitational Programming Contest

// Problem Excerpt
/*
    "Given an undirected, unweighted, connected graph, where each edge is colored either 
    blue or red, determine whether a spanning tree with exactly k blue edges exists."
*/

// Solution Description
/*
    Find an MST using only blue edges.  Make sure this MST has at least k edges in it.
    Find an MST using only red edges.  Then, update the red MST with all the edges from
    the blue MST.  Make sure the new red/blue MST covers the graph.

    If (and only if) both conditions are satisified, a spanning tree with exactly k
    blue edges must exist.
*/

#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef pair<int, int> pii;
typedef vector<pii> vii;
typedef long long ll;
typedef vector<ll> vl;

const int BLANK = -1;

struct UF {
    vi e;
    UF(int n) : e(n, -1) {}
    bool sameSet(int a, int b) { return find(a) == find(b); }
    int size(int x) { return -e[find(x)]; }
    int find(int x) { return e[x] < 0 ? x : e[x] = find(e[x]); }
    bool join(int a, int b) {
        a = find(a), b = find(b);
        if (a == b) return false;
        if (e[a] > e[b]) swap(a, b);
        e[a] += e[b]; e[b] = a;
        return true;
    }
};

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int n, m, k;
    cin >> n >> m >> k;

    vii blue, red;
    int u, v; char type;
    for(int i = 0; i < m; i++) 
    {
        cin >> type >> u >> v;
        u--; v--;

        if(type == 'B')
            blue.push_back({u, v});
        else
            red.push_back({u, v});
    }

    // make blue spanning tree
    // make sure the blue spanning tree is made up of at least k edges
    UF ufBlue (n);
    vii blueST;

    for(pii &edge : blue)
    {
        if(ufBlue.join(edge.first, edge.second))
            blueST.push_back(edge);
    }

    if(blueST.size() < k)
    {
        cout << 0 << endl;
        return 0;
    }

    // make red spanning tree
    // add blue spanning tree edges to red spanning tree
    // make sure that the red ST is completed in at most k blue edges
    UF ufRed (n);
    for(pii &r : red)
    {
        ufRed.join(r.first, r.second);
    }

    int used = 0;
    for(pii &b : blue)
    {
        if(ufRed.join(b.first, b.second))
            used++;
    }

    if(used > k)
    {
        cout << 0 << endl;
        return 0;
    }

    cout << 1 << endl;
}   

