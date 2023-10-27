// Joining the KAK
// Problem J
// 2022 ICPC Gran Premio de Mexico 2da Fecha

// Problem Description
/*
    You are given a set of characters (up to 1000) and an integer k.  Output the
    kth lexicographical string that can be created from the given character set.  The 
    lexicographical strings include those of size less than the given input.

    For example, if given the set aba, the lexicographical ordering of strings that use 
    this character set is:
    a, b, aa, ab, ba, aab, aba, baa

    In this problem, shorter strings are given lexicographical priority, regardless of
    starting character.
*/

// Solution Description
/*
    Perform many dp's.  Get the length of the kth string by performing a dp to
    get the number of ways to arrange the character set into a particular string
    length.  Then, for each index in the kth string, brute force
    the current character and calculate (dp) the number of ways to now make the remainder
    of the string.  Update k appropriately.
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

const ll INF = 1e9, BLANK = -1, ALPHA = 26, MAX = 1001;
int kth;

vector<vl> choose;

void init()
{
    choose = vector<vl> (MAX, vl (MAX, INF));

    for(int n = 0; n < MAX; n++)
    {
        choose[n][0] = choose[n][n] = 1;
        for(int k = 1, nk = n - 1; k <= nk; k++, nk--)
        {
            choose[n][k] = choose[n][nk] = ((choose[n][k-1] * (n - k + 1)) / (k));
            if(choose[n][k] >= INF)
            {
                choose[n][k] = choose[n][nk] = INF;
                break;
            }
        }
    }
}

ll possibles(int use, int curLen)
{
    if(use == 0) return 1;
    return choose[use + curLen][curLen];
}

// get number of possibilities starting at idx i, w/ len l
ll recurse(int idx, int lenLeft, int curLen, vi &alpha, vector<vl> &dp)
{
    if(lenLeft <= 0) return lenLeft == 0;
    if(idx >= alpha.size()) return 0;
    if(dp[idx][lenLeft] != BLANK) return dp[idx][lenLeft];

    ll ans = 0;
    for(int use = 0; use <= min(lenLeft, alpha[idx]); use++)
    {
        ans = min(ans + possibles(use, curLen) * recurse(idx + 1, lenLeft - use, curLen + use, alpha, dp), INF);
    }
    
    return dp[idx][lenLeft] = ans;
}

int getLen(vi &alpha, int n, int k)
{
    kth = k - 1;    // set kth
    for(int len = 1; len <= n; len++)
    {
        vector<vl> dp (ALPHA, vl (len + 1, BLANK));
        ll quant = recurse(0, len, 0, alpha, dp);
        if(quant > kth)
            return len;
        kth -= quant;
    }

    assert(false);
    return 0;
}

string solve(int len, vi &alpha)
{
    string ans;

    auto chooseChar = [&] (int idx)
    {
        for(int a = 0; a < ALPHA; a++)
        {
            if(!alpha[a]) continue;
            alpha[a]--;
            vector<vl> dp (ALPHA, vl (len - idx, BLANK));

            ll quant = recurse(0, len - idx - 1, 0, alpha, dp);
            if(quant > kth)
            {
                ans += (char) (a + 'a');
                return;
            }

            kth -= quant;
            alpha[a]++;
        }

        assert(false);
        return;
    };

    for(int i = 0; i < len; i++)
    {
        chooseChar(i);
    }

    return ans;
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    init();
    int n, k; 
    cin >> n >> k;

    string str;
    cin >> str;

    vi alpha (ALPHA);
    for(char c : str) alpha[c-'a']++;

    int len = getLen(alpha, n, k);

    string ans = solve(len, alpha);

    cout << ans << endl;

    return 0;
}