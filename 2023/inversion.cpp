// Inversions
// Southeastern European Regional 2018

// Problem Description
/*
    You are given an undirected graph, that represents a permutation of length n, 
    such that each edge (u, v) in the graph represents an inversion between the 
    value at index u and the value at index v in the permutation.

    A set of vertices is independent if no two vertices in the set have an edge
    between them.

    A set of vertices is dominant if every vertice that does not belong to the set
    has an edge to a vertice in the set.

    Determine the number of indepedent-dominant sets in the given graph.

    1 <= n <= 100
*/

// Solution Description
/*  
    Observation 1: The permutation can be extracted from the graph by sorting each index
                   by the number of values it is less/greater than
    Observation 2: An indepdent set is an ordered subsequence in the permutation
    Observation 3: A dominant set is one where each index not in the set is out of 
                   order relative to the set

    Reduced Problem: Count the number of ordered subsequences in the permutation, such
                     that no additional values in the permutation can be validly added
                     to that subsequence/set.

    Use a dp where the state space is the current index and the previously taken index.
    
    O(n^2)
*/

/* Template Code and Program Setup */
#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef vector<vi> vvi;
typedef pair<int, int> pii;
typedef vector<pii> vii;
typedef long long ll;
typedef vector<ll> vl;

#define nl '\n'
#define all(x) begin(x), end(x)
#define sz(x) (int)(x).size()

/* Solution Code */
int n;

// returns the permutation given the graph that represents it
vi buildPerm(vvi &adj)
{
    vi perm (n);
    
    vii out (n);
    for(int i = 0; i < n; i++) 
        out[i] = {sz(adj[i]), i};

    sort(all(out));

    for(int val = 0; val < n; val++)
    {
        perm[out[val].second] = val;
    }

    return perm;
}

vl dp;
const int BLANK = -1;
// run the dp starting at index idx (which has been chosen)
ll recurse(int idx, vi &perm)
{
    if(dp[idx] != BLANK) return dp[idx];

    int ran_min = 1e9;
    ll ans = 0;
    for(int i = idx + 1; i < n; i++)
    {
        // make sure the next chosen value is larger than the value at idx
        // and smaller than all values between idx and i-1
        if(perm[i] < perm[idx] || perm[i] > ran_min) continue;

        ran_min = perm[i];
        // update the count
        ans += recurse(i, perm);
    }

    // if idx is the last value in the sequence, return 1 by default
    if(ran_min >= (int) 1e9) return dp[idx] = 1;
    // return the count
    return dp[idx] = ans;
}

void solve()
{
    // input
    int m; cin >> n >> m;
    vvi matrix (n, vi (n));
    int u, v;
    for(int i = 0; i < m; i++)
    {
        cin >> u >> v;
        u--; v--;
        matrix[u][v] = matrix[v][u] = 1;
    }

    vvi adj (n);
    for(int i = 0; i < n; i ++)
    {
        for(int j = 0; j < i; j++)
        {
            if(matrix[i][j]) adj[j].push_back(i);
            else adj[i].push_back(j);
        }
    }

    // get the permutation
    vi perm = buildPerm(adj); 

    int prev_min = 1e9;
    ll total = 0;
    dp = vl (n, BLANK);
    for(int i = 0; i < n; i++)
    {
        // must take a prefix min as the starting value
        if(perm[i] < prev_min)
        {
            prev_min = perm[i];
            // update the count with this starting index
            total += recurse(i, perm);
        }
    }
    // output the total
    cout << total << nl;
}

// driver function
int main()
{
    cin.tie()->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    solve();
    return 0;
}