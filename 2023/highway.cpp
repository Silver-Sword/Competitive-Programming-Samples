// Highway Combinatorics
// German Regionals 2023

// Problem Description
/*
    Create a 2 by w sized grid, such the number of ways
    to completely fill the grid with 1 by 2 sized cars is congruent 
    to n mod 10^9+7.  You may choose w and you may prefill the grid with
    some number of cars.

    0 <= n < 10^9+7
    1 <= w <= 200
*/

// Solution Description
/*
    Observation: The number of ways to place cars inside a 2 by something grid
                 is equivalent to the Fibonacci sequence.

    Divide the grid into two sections.
    For the first half of the graph, randomly generate 10^6 grids of size up to 100
    using the Fibonacci sequence observation.  The top and bottom of the grid
    should be symmetric and be of the form: x blank spots followed by a separator
    followed by y blank spots, etc. Store all values that resulted.

    Then, do the same for the second half of the graph, except this time, calculate
    the value by dividing from the target (a reverse calculation) using modular inverse.
    If the resulting value exists within the set from the other half of the grid,
    then the two grids can be combined to form a valid grid of value n.

    O(1) [this algorithm's time complexity doesn't depend on any variables]
*/

/* Template Code and Program Setup */
#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef vector<int> vi;
typedef vector<ll> vl;
#define nl '\n'
#define sz(x) (int)(x).size()
const ll MOD = 1e9 + 7, mod = MOD;

/* Hackpack */
ll euclid(ll a, ll b, ll &x, ll &y) {
	if (!b) return x = 1, y = 0, a;
	ll d = euclid(b, a % b, y, x);
	return y -= a/b * x, d;
}

ll invert(ll &n)
{
    ll x, y, g = euclid(n, MOD, x, y);
	assert(g == 1);
    return ((x + MOD) % MOD);
}

ll divide(ll a, ll b)
{
    return (a * invert(b)) % MOD;
}

/* Solution Code */
string ans = "";
map<ll, vi> lot;
vl fib;
int used[100];

// build the output string from a list of empty lots
void build(vi ls)
{
    for(int i : ls)
    {
        if(!ans.empty()) ans += '#';
        ans += string(i, '.');
    }
}

// combine the left and right sections of the lot and output
void buildAns(vi left, vi right)
{
    ans = "";
    build(left);
    build(right);
    assert(sz(ans) <= 200);
    cout << ans << nl << ans << nl;
}

// get a random number between lo and hi
int getRand(int lo, int hi)
{
    if(lo > hi) return lo;
    return (rand() % (hi - lo + 1)) + lo;
}

bool solve()
{
    ll target; cin >> target;

    // handle min cases
    if(target == 0)
    {
        cout << ".##\n##.\n";
        return true;
    }
    else if(target==1)
    {
        cout << ".\n.\n";
        return true;
    }

    // left lot set
    for(int count = 1e6; count > 0; count--)
    {
        vi ls;
        ll pi = 1;
        int sum = -1;

        // pick random lot sizes until the entire left size is at most 100
        while(sum < 100)
        {
            int pick = getRand(2, 100-sum);
            sum += pick+1;
            if(sum >= 100) break;

            pi = (pi * fib[pick]) % MOD;
            ls.push_back(pick);

            // if target is equal to the result, then return this lot
            if(pi == target)
            {
                buildAns(ls, {});
                return true;
            }

            // if this lot has a duplicate count that has already been found, don't do anything with it
            if(lot.find(pi) != lot.end()) continue;
            // if this lot can combine with another already generated one, then the answer has been found
            if(lot.find(divide(target, pi)) != lot.end())
            {
                buildAns(ls, lot[divide(target, pi)]);
                return true;
            }
            // add the lot to the set
            lot[pi] = vi (ls);
        }
    }

    // pick random lot sizes for the right side
    // this is the same process as above, except the resulting value is divided from target
    for(int count = 1e6; count > 0; count--)
    {
        vi ls;
        ll pi = 1;
        int sum = -1;

        while(sum < 100)
        {
            int pick = getRand(2, 100-sum);
            sum += pick+1;
            if(sum >= 100) break;

            pi = (pi * fib[pick]) % MOD;
            ls.push_back(pick);

            if(divide(target, pi) == 1)
            {
                buildAns({} , ls);
                return true;
            }
            
            if(lot.find(divide(target, pi)) != lot.end())
            {
                buildAns(lot[divide(target, pi)], ls);
                return true;
            }
        }
    }
    return false;
}

// driver function
int main()
{
    cin.tie(0)->sync_with_stdio(0);

    // precomp the fib sequence
    fib = vl (101);
    fib[0] = 1;
    fib[1] = 1;

    for(int i = 2; i <= 100; i++)
        fib[i] = (fib[i-1] + fib[i-2]) % MOD;
    
    srand(12'341'234);
    solve();
    
    return 0;
}