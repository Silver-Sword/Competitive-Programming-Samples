#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef long long ll;
typedef vector<ll> vl;
typedef vector<vl> vvl;

#define nl '\n'
#define sz(x) (int)(x).size()
#define all(x) begin(x), end(x)

const ll MOD = 998'244'353;
const int MAXN = 2e5, SQRTN = 450, BLANK = -1;
// const int MAXN = 10000, SQRTN = 100, BLANK = -1;
vl ans (MAXN+1);
vvl dpWays(SQRTN+1, vl ((MAXN >> 1)+1, BLANK));

const int DEBUG = true;

ll solve(int col_left, int block_left)
{
    // if(DEBUG) cout << "\tsolve(col_left=" << col_left << ", block_left=" << block_left << ")" << endl;
    if(block_left == 0 || col_left == 1) return 1;
    else if(!col_left) return 0;
    else if(dpWays[col_left][block_left] != BLANK) return dpWays[col_left][block_left];

    ll ans = solve(col_left - 1, block_left);
    if(col_left <= block_left) ans = (ans + solve(col_left, block_left - col_left)) % MOD;

    // if(DEBUG) cout << "\tdp[col=" << col_left << "][blocks=" << block_left << "] = " << ans << nl;
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

int main(){
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