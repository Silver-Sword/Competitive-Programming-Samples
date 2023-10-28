#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef vector<int> vi;
typedef vector<ll> vl;
#define nl '\n'
#define sz(x) (int)(x).size()
const ll MOD = 1e9 + 7, mod = MOD;

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

string ans = "";
map<ll, vi> lot;
vl fib;
int used[100];

void build(vi ls)
{
    for(int i : ls)
    {
        if(!ans.empty()) ans += '#';
        ans += string(i, '.');
    }
}

void buildAns(vi left, vi right)
{
    ans = "";
    build(left);
    build(right);
    assert(sz(ans) <= 200);
    cout << ans << nl << ans << nl;
}

int getRand(int lo, int hi)
{
    if(lo > hi) return lo;
    return (rand() % (hi - lo + 1)) + lo;
}

bool solve()
{
    ll target; cin >> target;

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

            if(pi == target)
            {
                buildAns(ls, {});
                return true;
            }
            
            if(lot.find(pi) != lot.end()) continue;
            if(lot.find(divide(target, pi)) != lot.end())
            {
                buildAns(ls, lot[divide(target, pi)]);
                return true;
            }
            lot[pi] = vi (ls);
        }
    }

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
int main()
{
    cin.tie(0)->sync_with_stdio(0);

    // precomp
    fib = vl (101);
    fib[0] = 1;
    fib[1] = 1;

    for(int i = 2; i <= 100; i++)
        fib[i] = (fib[i-1] + fib[i-2]) % MOD;
    
    srand(12'341'234);
    solve();
    
    return 0;
}