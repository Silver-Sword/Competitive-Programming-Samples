#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef vector<vi> vvi;
typedef pair<int, int> pii;
typedef vector<pii> vii;
typedef long long ll;
typedef vector<ll> vl;
typedef vector<vl> vvl;
typedef pair<ll, ll> pll;
typedef vector<double> vd;
typedef long double ld;

#define rep(i,a,b) for(int i = a; i < (b); i++)
#define sz(x) (int)(x).size()
#define nl '\n'
#define all(x) begin(x), end(x)

const int DEBUG = false;

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int tt; cin >> tt;
    
    while(tt--)
    {
        int n, m;
        cin >> n >> m;
        vvi arr (n, vi (m));
        for(int i = 0; i < n; i++)
        {
            iota(all(arr[i]), i*m+1);
        }

        int d = 2;
        for(int idx = 1, count = 0; count < n; count++, idx += d)
        {
            if(idx >= n) idx = 0;

            rep(j,0,m) cout << arr[idx][j] << " ";
            cout << nl;
        } 
        if(DEBUG) cout << nl;
    }

    return 0;
}