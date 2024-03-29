// Jellyfish and Math
// Codeforces Round 901 (Div. 1)

// Problem Description
/*
    Given integers a, b, c, d, and m, you may perform the following
    operations any number times:
    a := a & b
    a := a | b
    b := a ⊕ b
    b := b ⊕ m

    Determine the minimum number of operations to satisfy (a,b) = (c,d).

    0 <= a,b,c,d,m < 2^30
    There are up to 10^5 testcases, t
*/

// Solution Description
/*
    The solution includes running a bfs on all reachable values. However,
    using sets for each bfs is too slow for this particular problem. In order 
    to bypass this limitation, the valid move set must be precomputed.

    O(t)
    Note that the precomp is about 5^8 operations, and is constant.
*/

/* Template Code and Program Setup */
#pragma GCC optimize("O2,O3,unroll-loops")
#pragma GCC target("avx2,bmi,bmi2,lzcnt,popcnt")
#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef long double ld;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef vector<int> vi;
typedef vector<pii> vii;
typedef vector<pll> vll;
typedef vector<ll> vl;
typedef vector<vi> vvi;
typedef vector<vl> vvl;

#define nl '\n'
#define rep(i,a,b) for(int i = a; i <(b); i++)
#define all(x) begin(x), end(x)
#define sz(x) (int)(x).size()

/* Solution Code */
const int INF = 1e9;
const int CAP = 1552;
int m;

// guessing a max number of moves
// 438672899 76082099 478699952 433209552 433242322 
ll target;
set<ll> states;
ll state[4];
ll q[CAP];

ll NUM = 1 << 30;

// tree
vvi next_move (CAP);
vvi next_node (CAP);
int num_moves[CAP];

// perform the naive bfs from (a, b) and generate the valid and complete move set
void gen_tree(ll a, ll b, ll c, ll d, ll m)
{
    states.clear();

    int front = 0, last = 0;
    ll target = (c << 30) + d;
    int counter = 0, prune = 1;
    
    q[last++] = (a << 30) + b;
    states.insert((a << 30) + b);

    while(front < last && prune < 1552)
    {
        counter++;
        int S = last;

        while(front < S)
        {
            int node = front;
            ll key = q[front++];

            // moves
            state[0] = key & (((key & (NUM-1)) << 30) | (NUM-1));
            state[1] = key | ((key & (NUM-1)) << 30);
            state[2] = key ^ (key >> 30);
            state[3] = key ^ m;

            for(int i = 0; i < 4; i++)
            {
                ll next = state[i];
                if(!states.insert(next).second) continue;

                // add the move used to get here if it generated a unique value
                next_move[node].push_back(i);
                next_node[node].push_back(last);
                num_moves[last] = counter;

                q[last++] = next;
            }
        }
    }

    assert(front == CAP);
}

void solve()
{
    // take in input
    int b, d;
    int front = 0, last = 0;
    ll a, c;
    cin >> a >> b >> c >> d >> m;

    // case out when (a, b) = (c, d)
    if(a == c && b == d) 
    {
        cout << 0 << nl;
        return;
    }

    ll target = (c << 30) + d;
    states.clear();
    q[0] = (a << 30) + b;
    states.insert((a << 30) + b);
    int moves = 0;

    // walk through the generated move set and check whether any of the resulting values are (c, d)
    for(int i = 0; i < CAP; i++)
    {
        ll key = q[i];

        state[0] = key & (((key & (NUM-1)) << 30) | (NUM-1));
        state[1] = key | ((key & (NUM-1)) << 30);
        state[2] = key ^ (key >> 30);
        state[3] = key ^ m;

        moves = sz(next_move[i]); 
        for(int m = 0; m < moves; m++)
        {
            ll next = state[next_move[i][m]];
            if(next == target) 
            {
                cout << num_moves[next_node[i][m]] << nl;
                return;
            }
            else q[next_node[i][m]] = next;
        }
    }

    cout << -1 << nl;
}

// driver function
int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    // precomp the move set based on generated and tested values
    gen_tree(438672899, 76082099, 478699952, 433209552, 433242322);
    
    int tt; cin >> tt;
    while(tt--)
        solve();
    return 0;
}