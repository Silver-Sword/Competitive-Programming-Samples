// Open-Pit Mining
// Problem B
// unknown source

/* 
Problem Description

You are given n (<= 200) blocks.  Each block has an associated cost and profit.
Then, you given m (<= n - 1) dependencies, in which block a blocks block b.
In other words, you cannot mine block b until you mine block a.
You must determine what is the maximum possible profit if you mine the optimal set of blocks.
*/

// Solution Description: 
// Use network flow where the source is connected to all blocks
// which have a positive net profit, the sink is connected to all
// blocks which have a negative net profit, and the each block that has
// a dependency has an edge to its dependency.  The maximum profit is the
// total possible profit minus the flow (cost).

#include <bits/stdc++.h>
using namespace std;

#define rep(i, a, b) for(int i = a; i < b; i++)
#define all(x) begin(x), end(x)
#define sz(x) (int) x.size()

typedef vector<int> vi;
typedef long long ll;
typedef pair<int, int> pii;
typedef vector<ll> vl;
typedef vector<pii> vii;

int INF = 1e9, n;

template<class T> T edmondsKarp(vector<unordered_map<int, T>>& graph, int source, int sink) {
	assert(source != sink);
	T flow = 0;
	vi par(sz(graph)), q = par;

	for (;;) {
		fill(all(par), -1);
		par[source] = 0;
		int ptr = 1;
		q[0] = source;

		rep(i,0,ptr) {
			int x = q[i];
			for (auto e : graph[x]) {
				if (par[e.first] == -1 && e.second > 0) {
					par[e.first] = x;
					q[ptr++] = e.first;
					if (e.first == sink) goto out;
				}
			}
		}
		return flow;

    out:
		T inc = numeric_limits<T>::max();
		for (int y = sink; y != source; y = par[y])
			inc = min(inc, graph[par[y]][y]);

		flow += inc;
		for (int y = sink; y != source; y = par[y]) {
			int p = par[y];
			if ((graph[p][y] -= inc) <= 0) graph[p].erase(y);
			graph[y][p] += inc;
		}
	}
}

vector<unordered_map<int, int>> makeGraph(vector<vi> &reverse, vi &block)
{
    vector<unordered_map<int, int>> graph (n + 2);
    int src = n, sink = src + 1;

    // node to node
    for(int i = 0; i < n; i++)
    {
        for(int j : reverse[i])
        {
            graph[i][j] = INF;
        }
    }

    // src to positive costs
    for(int i = 0; i < n; i++)
    {
        if(block[i] > 0)
        {
            graph[src][i] = block[i];
        }
    }

    // neg costs to sink
    for(int i = 0; i < n; i++)
    {
        if(block[i] < 0)
        {
            graph[i][sink] = -block[i];
        }
    }

    return graph;
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    cin >> n;

    vi block (n);
    vector<vi> forward (n), reverse (n);

    int val, cost, ans = 0;
    for(int i = 0; i < n; i++)
    {
        cin >> val >> cost;
        block[i] = val - cost;

        int m, u;
        cin >> m;
        for(int j = 0; j < m;j++)
        {
            cin >> u;
            u--;
            forward[i].push_back(u);
            reverse[u].push_back(i);
        }

        if(block[i] > 0) ans += block[i];
    }

    vector<unordered_map<int, int>> graph = makeGraph(reverse, block);

    ans = ans - edmondsKarp<int>(graph, n, n + 1);
    cout << ans << endl;
}