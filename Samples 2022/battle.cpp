// Kiwis vs Kangaroos II
// Problem K
// 2017 South Pacific Regionals

/*
Problem Excerpt:

"You have decided that you will hold a head-to-head programming tournament. 
Each country, Australia and New Zealand, will send some number
of programmers (not necessarily the same number) to compete in the tournament. 
The programmers from Australia are called ‘kangaroos’, and the
programmers from New Zealand are called ‘kiwis’. You have set up n stadiums 
to hold the tournament. The tournament will take place in n separate rounds.

In each round, n different kangaroos will battle against n different kiwis, with one kangaroo battling one kiwi
in each stadium (n battles per round, so n^2 battles in total). To keep things interesting for the spectators, no
programmer may battle in any given stadium more than once, though they may battle against the same opponent
multiple times in different rounds.

The king of the kangaroos has nominated m kangaroos. The ith kangaroo must fight in exactly t_i different
battles. Similarly, the queen of the kiwis has nominated k kiwis. The ith kiwi must fight in exactly s_i battles.
Find a valid tournament schedule that satisfies the above constraints."

Output a valid set of fights where rows/columns represent rounds/stadiums.
m, k <= n <= 200
*/

// Solution Idea:
// You can greedily place each animal among the diagnols.  If you sort
// the animal input before placement, no animal will fight twice in the 
// same stadium or battle (row or column).  This solution relies on the 
// constraints that m and k are only up to n.  Proof left as an exercise
// to the reader.

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

int n;
vector<vi> solve(vi &arr)
{
    vii freq;
    for(int i = 0; i < arr.size(); i++) freq.push_back({arr[i], i+1});
    sort(all(freq));
    reverse(all(freq));

    vector<vi> ans (n, vi (n));
    int idx = 0;

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        {
            ans[(i+j)%n][j] = freq[idx].second;
            freq[idx].first --;
            if(!freq[idx].first) idx++;
        }
    }

    return ans;
}


int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int m, k; cin >> n >> m >> k;

    vi kang (m); 
    for(int i = 0; i < m; i++) cin >> kang[i];
    
    vi kiwi (k); 
    for(int i = 0; i < k; i++) cin >> kiwi[i];

    vector<vi> A = solve(kang), B = solve(kiwi);

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        {
            cout << A[i][j] << "v" << B[i][j] << " ";
        }

        cout << endl;
    }

    return 0;
}