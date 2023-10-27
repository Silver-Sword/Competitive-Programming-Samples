// lifeguards
// unknown contest source
// Problem credits: Brian Dean

// Problem Description
/*
    Farmer John has opened a swimming pool for his cows, figuring it will help them relax and produce
    more milk.

    To ensure safety, he hires N cows as lifeguards, each of which has a shift that covers some contiguous
    interval of time during the day. For simplicity, the pool is open from time 0 until time 10^9 on a daily
    basis, so each shift can be described by two integers, giving the time at which a cow starts and ends
    her shift. For example, a lifeguard starting at time t=4 and ending at time t=7 covers three units of time
    (note that the endpoints are "points" in time).

    Unfortunately, Farmer John hired K more lifeguards than he has the funds to support. Given that he
    must fire exactly K lifeguards, what is the maximum amount of time that can still be covered by the
    shifts of the remaining lifeguards? An interval of time is covered if at least one lifeguard is present.

    INPUT FORMAT
    The first line of input contains N and K (K ≤ N ≤ 10^5, 1 ≤ K ≤ 100). Each of the next N lines describes
    a lifeguard in terms of two integers in the range [0, 10^9], giving the starting and ending point of a
    lifeguard's shift. All such endpoints are distinct. Shifts of different lifeguards might overlap.

    OUTPUT FORMAT
    Output a single number, the maximum amount of time that can still be covered if Farmer John fires K
    lifeguards.
*/

// Solution Description
/*
    Remove guard intervals that are enclosed inside other guard intervals.  Update k to reflect this.
    Perform a dp in which the state space is dp(last guard taken, remaining to fire) = best score starting at this state.
*/

#include <bits/stdc++.h>
using namespace std;

#define rep(i, a, b) for(int i = a; i < b; i++)
#define all(x) begin(x), end(x)
#define sz(x) (int) x.size()

typedef vector<int> vi;
typedef long long ll;
typedef pair<int, int> pii;
typedef vector<ll> vl;
typedef vector<pii> vii;

bool DEBUG = false;
int INF = 1e9;

bool inside(pii &cand, pii &range)
{
    return (range.first <= cand.first && range.second >= cand.second);
}

vii removeInside(vii &guards)
{
    vii ret;
    ret.push_back(guards[0]);

    for(int i = 1; i < guards.size(); i++)
    {
        // if i am in the prev
        if(inside(guards[i], ret.back())) continue;

        while(!ret.empty() && inside(ret.back(), guards[i]))
        {
            ret.pop_back();
        }

        ret.push_back(guards[i]);
    }

    return ret;
}

int score(pii &g)
{
    return g.second - g.first;
}

int calcOverlap(vii &guard, int a, int b)
{
    return max(0, guard[a].second - guard[b].first);
}

void compute(vector<vi> &prev, vector<vi> &nxt, int idx, vii &guard, int left)
{
    // dp state is [last taken][ks left]
    // nxt 0 means take this, k is same
    // O(n ^ 2)
    // taking
    for(int k = 0; k <= left; k++)
    {
        // take any last for every k
        for(int prevLast = 0, prevIdx = idx - 1; prevLast < prev.size(); prevIdx--, prevLast++)
        {
            if(prevIdx < 0)
            {
                if(prev[prevLast][k] == -INF) continue;
                nxt[0][k] = max(nxt[0][k], score(guard[idx])); 
            }

            else
            {
                if(prev[prevLast][k] == -INF) continue;
                nxt[0][k] = max(nxt[0][k], prev[prevLast][k] + score(guard[idx]) - calcOverlap(guard, prevIdx, idx));
            }
        }
    }

    // nxt not 0 means not taking this, k is one less
    // O (n ^ 2)
    for(int k = 0; k < left; k++)
    {
        for(int last = 1; last < prev.size(); last++)
            nxt[last][k] = prev[last - 1][k + 1];
    }
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int n, k;
    cin >> n >> k;

    // remove intervals that are strictly within other intervals
    vii guard (n);
    for(int i = 0; i < n; i++)
        cin >> guard[i].first >> guard[i].second;
    
    sort(guard.begin(), guard.end());
    guard = removeInside(guard);
    k -= (n - guard.size()); // update k to match what was removed

    if(k <= 0)
    {
        ll ans = 0;
        // calculate from just guards (no need to remove any)
        int x = 0;
        for(pii g : guard)
        {
            x = max(x, g.first);
            ans += g.second - x;
            x = g.second;
        }
        cout << ans << endl;
    }

    else
    {
        // will now only have ranges that overlap or not
        // dp state is [last taken][k's left]
        vector<vi> prev (k + 1, vi (k + 1, -INF));
        vector<vi> nxt (k + 1, vi (k + 1, -INF));

        // choose init previous
        nxt[0][k] = score(guard[0]);
        nxt[1][k-1] = 0;

        for(int idx = 1; idx < guard.size(); idx++)
        {
            prev = nxt;
            nxt = vector<vi> (k + 1, vi (k + 1, -INF));

            // needs to be at most k^2
            compute(prev, nxt, idx, guard, k);
        }

        int ans = 0;
        for(int last = 0; last < nxt.size(); last++)
            ans = max(ans, nxt[last][0]);
        cout << ans << endl;
    }
}