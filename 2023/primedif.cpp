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
    Observation: +/- 1 is a valid difference, so a row of incremeted numbers is valid
                 for example: 1,2,3 is valid, as is 4,5,6,7
    Observation: placing two of these rows adjacent to each other will give a difference
                 of m*k for every column.
    
    Set each column to a sequence of the form x to x+m-1
    Then, order the rows such that the rows starting with x and x+m are not adjacent.
    This will force the difference to be k*m and k cannot be 1, so the difference must
    be composite.

    O(nm)
*/

/* Template Code and Program Setup */
#include <bits/stdc++.h>
using namespace std;

#define nl '\n'
#define all(x) begin(x), end(x)
typedef vector<int> vi;
typedef vector<vi> vvi;

/* Solution Code */
int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int tt; cin >> tt;
    while(tt--)
    {
        // input
        int n, m;
        cin >> n >> m;
        vvi arr (n, vi (m));
        for(int i = 0; i < n; i++)
        {
            iota(all(arr[i]), i*m+1);
        }

        // print out every other row
        int d = 2;
        for(int idx = 1, count = 0; count < n; count++, idx += d)
        {
            if(idx >= n) 
                idx = 0;

            for(int j = 0; j < m; j++) 
                cout << arr[idx][j] << " ";
            cout << nl;
        } 
    }

    return 0;
}