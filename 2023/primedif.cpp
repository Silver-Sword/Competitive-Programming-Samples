// No Prime Difference
// Codeforces Round 877 (Div. 2)
// https://codeforces.com/contest/1838/problem/C

// Problem Description
/*
    Given n and m, use the integers 1 through n*m, and fill an n by m grid
    such that no two adjacent cells have a difference in values that is prime.
    Adjacent cells are those that share an edge.

    4 <= n, m <= 1000
*/

// Solution Description
/*

*/

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
    }

    return 0;
}