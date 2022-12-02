// Two Buildings
// Problem L
// ICPC 2020 Asia Regional – Seoul 

// Problem Description
/*
    There are n buildings in sequence, where the ith building has height h_i. The score of two
    buildings (building i and building j) is (h_i + h_j) * (j - i) where j > i.  Output the
    maximum possible score across all pairs of buildings.
*/

// Solution Description
/*
    Get the strictly increasing sequence of buildings.  Get the strictly decreasing sequence of buildings.
    Solve with a divide and conquer technique:
        for the center building of the increasing sequence, find its best possible pair in the decreasing sequence.
        divide the increasing sequence and decreasing sequence at its chosen building.  Repeat these steps for 
        the first half of both sequences and then the second half of both sequences.
    "Merge" the result.
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
typedef pair<ll, ll> pll;

deque<pair<ll, ll>> leftCH, rightCH;

ll getScore(pll &a, pll &b)
{
    return (a.first + b.first) * abs(b.second - a.second);
}

ll solve(int l, int r, int L, int R)
{
    if(l > r) return 0;
    int leftIdx = (l + r) / 2;
    ll bestScore = 0, score;
    int bestIdx;

    for(int i = L; i <= R; i++)
    {
        score = getScore(leftCH[leftIdx], rightCH[i]);

        if(score > bestScore)
        {
            bestScore = score;
            bestIdx = i;
        }
    }

    return max(bestScore, max(solve(l, leftIdx - 1, L, bestIdx), solve(leftIdx + 1, r, bestIdx, R)));
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int n; cin >> n;

    vl height (n);
    for(int i = 0; i < n; i++) cin >> height[i];

    for(int i = 0; i < n; i++)
    {
        if(leftCH.empty() || leftCH.back().first < height[i] ) leftCH.push_back({height[i], i+1});
    }

    for(int i = n - 1; i >= 0; i--)
    {
        if(rightCH.empty() || rightCH.front().first < height[i]) rightCH.push_front({height[i], i+1});
    }

    cout << solve(0, leftCH.size() - 1, 0, rightCH.size()) << endl;
    return 0;
}