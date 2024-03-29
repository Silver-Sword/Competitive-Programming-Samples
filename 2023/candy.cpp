// Candy Rush
// Latin American Regionals 2023-2024

// Problem Description
/*
    There are k types of candies. Given a list of n candies on display,
    find the maximum contiguous substring of candies such that all k candies
    have the same frequency within the substring. 

    1 <= n, k <= 4*10^5
*/

// Solution Description
/*
    Process the candies in order.  For each candy, compute a prefix hash
    of the excess candies and the total number of candies that can be equally distributed.  
    If the prefix hash already exists in the hash set, then there must exist a 
    smaller prefix which can be subtracted out to get a perfectly distributable set of
    candies.  In that case, a possible answer answer is range between the two hashes.
    Otherwise, add the hash to the set.

    O((n+k) log n)
*/

/* Template Code and Program Setup */
#include <bits/stdc++.h>
using namespace std;

#define nl '\n'
#define sz(x) (int)(x).size()
#define rep(i,a,b) for(int i = a; i < (b); i++)
#define all(x) begin(x), end(x)
typedef vector<int> vi;
typedef long long ll;

const int MAX = 1e6;

/* Hackpack */
typedef uint64_t ull;
struct H
{
    ull x; H(ull x=0) : x(x) {}
    H operator +(H o) {return x + o.x + (x + o.x < x);}
    H operator- (H o) {return *this + ~o.x;}
    H operator* (H o) {auto m = (__uint128_t)x * o.x;
        return H((ull)m) + (ull)(m >> 64);}
    ull get() const {return x + !~x;}
    bool operator== (H o) const {return get() == o.get();}
    bool operator< (H o) const{return get() < o.get();}
};

static const H C = (ll) 1e11+3;

struct HashInterval
{
    vector<H> ha, pw;
    HashInterval(string &str) : ha(sz(str)+1), pw(ha)
    {
        pw[0] = 1;
        rep(i,0,sz(str))
            ha[i+1] = ha[i] * C + str[i],
            pw[i+1] = pw[i] * C;
    }
    H hashInterval(int a, int b) { //[a, b)
        return ha[b] - ha[a] * pw[b-a];
    }
};

vector<H> getHashes(string &str, int length)
{
    if(sz(str) < length) return {};
    H h = 0, pw = 1;
    rep(i,0,length)
        h = h * C + str[i], pw = pw * C;
    vector<H> ret = {h};
    rep(i,length,sz(str)){
        ret.push_back(h = h * C + str[i] - pw * str[i-length]);
    }
    return ret;
}

H hashString(string &s) {H h{}; for(char c : s) h = h*C+c; return h;}

/* Solution Code */
vector<H> pw (MAX);
void solve()
{
    // input
    int n, k; cin >> n >> k;

    // variables for prefix processing
    map<H, int> hash;
    vi atTarget (n+1, k);
    vi freq(k);

    // precomp the hash of one of each candy
    H full (0);
    for(int i = 0; i < k; i++) full = full + pw[i];

    // empty prefix hash
    H h (0);
    hash[h] = 0;

    // process the prefixes
    int cur = 0, v, best = 0;
    for(int i = 0; i < n; i++)
    {
        cin >> v, v--;
        atTarget[freq[v]]--;
        freq[v]++;

        // update hash here
        h = h + pw[v];
        // update the check of the excess candies
        while(!atTarget[cur]) 
        {
            h = h - full;
            cur++;
        }

        // hash already exists as a prefix, extract answer
        if(hash.find(h) != hash.end())
            best = max(best, cur - hash[h]);
        // add prefix hash to set
        else hash[h] = cur;
    }

    // output the answer
    cout << best*k << nl;
}

// driver function
int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    // precomp
    pw[0] = 1;
    for(int i = 1; i < MAX; i++)
        pw[i] = pw[i-1] * C;
    solve();
    return 0;
}