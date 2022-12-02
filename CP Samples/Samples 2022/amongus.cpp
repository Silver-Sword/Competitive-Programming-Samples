// Among Us
// Problem B
// 2021 Jiangsu Collegiate Programming Contest

// Problem Description
/*
    The are 2 imposters and at most 8 crewmates. The imposters must kill all the crewmates to
    win the game, while the crewmates must complete their tasks as quickly as possible.
    There are n rooms and m paths between those rooms that the imposters can travel.  
    The imposters can predict where the crewmates will be at certain times.  If an imposter is ever
    in the same place as a crewmate, the imposter can kill that crewmate.  Determine the shortest time
    it would take for the imposters to kill all the crewmates.
*/

// Solution Description
/*
    Compute each imposter separately and then combine their results by flipping the bits in the bitmask.
    The bitmask will represent which crewmates have been killed.  The dp will track:
    dp(bitmask, vertex) = min time to reach this state.  Perform a bfs to get each of the weighted paths
    to the states.
*/

#include<bits/stdc++.h>
using namespace std;

#define all(x) begin(x), end(x)

typedef vector<int> vi;
typedef pair<int, int> pii;
typedef vector<pii> vii;

const int BLANK = 1e9, INF = 1e9 + 1e5;
int target;
int crew, rooms;
vector<vi> space;

vi bfs(int start, vector<vector<vi>> &times, vector<vii> &adj)
{
    vector<vi> dist (1 << crew, vi (rooms, INF));
    vector<vi> visited (1 << crew, vi (rooms));

    auto calcMinTime = [&](int mask, int node, int startTime)
    {
        int worstTime = startTime;
        for(int i = 0, d = 1; i < crew; i++, d <<= 1)
        {
            if(d & mask)
            {
                vi::iterator low = lower_bound(all(times[i][node]), startTime);
                if(low == times[i][node].end())
                {
                    worstTime = INF;
                    break;
                }
                worstTime = max(worstTime, *low);
            }
        }
        return worstTime;
    };
    priority_queue<pair<int, pii>, vector<pair<int, pii>>, greater<pair<int, pii>>> q;
    q.push({0, {0, start}});
    dist[0][start] = 0;

    while(!q.empty())
    {
        int mask = q.top().second.first, node = q.top().second.second; q.pop();

        if(visited[mask][node]) continue;
        visited[mask][node] = true;

        // add more states to mask
        for(int i = 0, d = 1; i < crew; i++, d <<= 1)
        {
            if(d & mask) continue; // overlapping; no need to calc
            vi::iterator lb = lower_bound(all(times[i][node]), dist[mask][node]);
            int minTime = (lb == times[i][node].end() ? INF : *lb);
            if(minTime >= INF) continue;

            if(dist[mask | d][node] > minTime)
            {
                dist[mask | d][node] = minTime;
                q.push({minTime, {mask | d, node}});
            }
        }

        // transition to other places
        for(pii &next : adj[node])
        {
            if(dist[mask][next.first] > dist[mask][node] + next.second)
            {
                dist[mask][next.first] = dist[mask][node] + next.second;
                q.push({ dist[mask][next.first], {mask, next.first}});
            }
        }
        
    }

    vi ans (1 << crew, INF);
    for(int i = 0; i <= target; i++)
    {
        for(int j = 0; j < rooms; j++)
        {
            ans[i] = min(ans[i], dist[i][j]);
        }
    }

    return ans;
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int tt; cin >> tt;
    while(tt--)
    {
        int paths; cin >> rooms >> paths >> crew;
        target = (1 << crew) - 1;

        vector<vii> adj (rooms);
        for(int i = 0; i < paths; i++)
        {
            int u, v, time;
            cin >> u >> v >> time; u--; v--;
            adj[u].push_back({v, time});
            adj[v].push_back({u, time});
        }

        int estimates, maxTime;
        cin >> estimates >> maxTime;
        vector<vector<vi>> est (crew, vector<vi> (rooms));
        
        for(int i = 0; i < estimates; i++)
        {
            int id, room, time; cin >> id >> room >> time;
            id--; room--;
            est[id][room].push_back(time);
        }

        for(int i = 0; i < crew; i++) 
        {
            for(int j = 0; j < rooms; j++)
            {
                sort(all(est[i][j]));
            }
        }

        int x, y; cin >> x >> y;
        x--; y--;

        vi a = bfs(x, est, adj), b = bfs(y, est, adj);

        int best = min(a[target], b[target]);
        for(int first = 0; first <= target; first++)
        {
            assert(a[first] >= 0 && b[first] >= 0);
            best = min(best, max(a[first], b[target & ~first]));
        }

        cout << (best <= maxTime ? best : -1) << endl;
    }
}