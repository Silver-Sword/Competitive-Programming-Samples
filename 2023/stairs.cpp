// Counting Stairs
// Northern Subregional 2018

// Problem Description
/*
    Determine the number of symmetric staircases that can be built with n blocks
    modulo a constant prime. A staircase is symmetric if it is the same across
    the x=y line.  A step must be equal or less than the previous step.

    There are up to 10^4 testcases.
    1 <= n <= 2*10^5
*/

// Solution Description
/*
    Example Staircase:
    #
    ##
    ###

    For each n, brute force the size of the center square of the stair.
    Then, do a dp for the number of ways to place the remaining blocks.
    The dp will represent for each (number of columns, number of remaining blocks),
    how many ways are their to validly place the blocks.  The transitions are either
    add another row to the remaining columns or reduce the number of columns that
    are being processed.

    O(t) where the precomp is O(maxn sqrt(maxn)) and maxn is the maximum value of n
*/

/* Template Code and Program Setup */
#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef long long ll;
typedef vector<ll> vl;
typedef vector<vl> vvl;

#define nl '\n'
#define sz(x) (int)(x).size()
#define all(x) begin(x), end(x)

/* Solution Code */
const ll MOD = 998'244'353;
const int MAXN = 2e5, SQRTN = 450, BLANK = -1;
vl ans (MAXN+1);
vvl dpWays(SQRTN+1, vl ((MAXN >> 1)+1, BLANK));

ll solve(int col_left, int block_left)
{
    if(block_left == 0 || col_left == 1) return 1;
    else if(!col_left) return 0;
    else if(dpWays[col_left][block_left] != BLANK) return dpWays[col_left][block_left];

    ll ans = solve(col_left - 1, block_left);
    if(col_left <= block_left) ans = (ans + solve(col_left, block_left - col_left)) % MOD;

    return dpWays[col_left][block_left] = ans;
}
 
void precomp()
{
    // precomp
    for(int col = SQRTN; col >= 0; col--)
    {
        for(int left = MAXN >> 1; left >= 0; left--)
        {
            solve(col, left);
        }
    }

    for(int blocks = 1; blocks <= MAXN; blocks++)
    {
        for(int base = ((blocks & 1) ? 1 : 2); base * base <= blocks; base += 2)
        {
            ans[blocks] = (ans[blocks] + solve(base, (blocks - base * base) >> 1)) % MOD;
        }
    }
}

// driver function
int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    precomp();
    int tt; cin >> tt;
    int n; 
    while(tt--)
    {
        cin >> n;
        cout << ans[n] << nl;
    }

    return 0;
}