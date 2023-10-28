#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef vector<int> vi;
typedef vector<ll> vl;
typedef vector<vi> vvi;
typedef vector<vl> vvl;

#define nl '\n'
#define sz(x) (int)(x).size()

const int BLANK = -1, DIGIT = 21;

vl numChildren, arr, ans;
vvl onCost, offCost;
vvi adj;
int n;

void first_dfs(int node, int par)
{
    numChildren[node] = 0;

    for(int next : adj[node])
    {
        if(next == par) continue;
        first_dfs(next, node);
        numChildren[node] += numChildren[next] + 1;

        for(int i = 0, d = 1; i <= DIGIT; i++, d <<= 1)
        {
            if(arr[node] & d) // my digit is on
            {
                onCost[node][i] += onCost[next][i];
                offCost[node][i] += onCost[next][i];
            }
            else // my digit is off
            {
                onCost[node][i] += offCost[next][i];
                offCost[node][i] += offCost[next][i];
            }
        }
    }

    for(int i = 0, d = 1; i <= DIGIT; i++, d <<= 1)
    {
        if(arr[node] & d)
        {
            offCost[node][i] += (numChildren[node] + 1) * d;
        }
        else
        {
            onCost[node][i] += (numChildren[node] + 1) * d;
        }
    }
}

void second_dfs(int node, int par, vl &par_on_cost, vl &par_off_cost)
{

    vl on_cost (DIGIT+1), off_cost(DIGIT+1);
    for(int i = 0, d = 1; i <= DIGIT; i++, d <<= 1)
    {
        on_cost[i] += onCost[node][i];
        off_cost[i] += offCost[node][i];

        if(arr[node] & d) // my digit is on
        {
            on_cost[i] += par_on_cost[i];
            off_cost[i] += par_on_cost[i] + (n - (numChildren[node] + 1)) * d;
        }
        else // my digit is off
        {
            on_cost[i] += par_off_cost[i] + (n - (numChildren[node] + 1)) * d;
            off_cost[i] += par_off_cost[i];
        }
    }
    
    // calc my cost
    ll my_cost = 0;
    for(int i = 0, d = 1; i <= DIGIT; i++, d <<= 1)
    {
        if(arr[node] & d) my_cost += on_cost[i];
        else my_cost += off_cost[i];
    }
    ans[node] = my_cost;

    for(int next : adj[node])
    {
        if(next == par) continue;
        vl next_on_cost (on_cost);
        vl next_off_cost (off_cost);

        for(int i = 0, d = 1; i <= DIGIT; i++, d <<= 1)
        {
            if(arr[node] & d) // my digit is on
            {
                next_on_cost[i] -= onCost[next][i];
                next_off_cost[i] -= onCost[next][i] + (numChildren[next] + 1) * d;
            }
            else // my digit is off
            {
                next_on_cost[i] -= offCost[next][i] + (numChildren[next] + 1) * d;
                next_off_cost[i] -= offCost[next][i];
            }
        }
        second_dfs(next, node, next_on_cost, next_off_cost);
    }
}

void solve()
{
    cin >> n;
    numChildren = vl (n);
    arr = vl (n);
    ans = vl (n);
    adj = vvi (n);
    onCost = vvl (n, vl(DIGIT+1));
    offCost = vvl (n, vl(DIGIT+1));

    int u, v;

    for(int i = 0; i < n; i++) cin >> arr[i];
    for(int i = 1; i < n; i++)
    {
        cin >> u >> v;
        u--; v--;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    first_dfs(0, 0);

    vl unit (DIGIT+1, 0);
    second_dfs(0, 0, unit, unit);
    
    for(int i = 0; i < n; i++) cout << ans[i] << " ";
    cout << nl;
}
int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    int tt; cin >> tt;
    while(tt--)
        solve();
    return 0;
}