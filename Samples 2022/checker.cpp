// Checker Slide
// Problem C
// 2021 ICPC Greater NY Regional Contest

// Problem Description
/*
    Given a 6 by 6 board and the initial positions of 4 checkers, determine 
    the minimum move set to reach an ending position set. The checkers may move in any 
    of the cardinal directions, but must move until they hit another checker or the 
    end of the board.
*/

// Solution Description
/*
    Use a bfs (where the states are stored as hashes) to determine the minimum distance
    store each move to restore the move set used to reach the target positions
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

int SZ = 6;

vi dy = {0, 0, 1, -1};
vi dx = {1, -1, 0, 0};

void printBoard(vii board)
{
    for(pii p : board) cout << p.first << " " << p.second << " ";
}

vii decode(ll board)
{
    ll bit = 1;
    vii ans;

    for(int i = 0; i < SZ; i++)
        for(int j = 0; j < SZ; j++, bit <<= 1)
            if((board & bit) > 0)
                ans.push_back({i, j});
    return ans;
}

ll getHash(vii &board)
{
    ll ans = 0;
    for(pii &check : board)
    {
        ans |= (((ll)1) << (check.first * SZ + check.second));
    }
    
    return ans;
}

void outputMove(ll start, ll end)
{
    // get start
    ll bit = 1;
    for(int i = 0; i < SZ; i++) 
        for(int j = 0; j < SZ; j++, bit <<= 1) 
            if((bit & start) > 0 && (bit & end) == 0) 
            {
                cout << i << " " << j << " ";
            }

    bit = 1;
    for(int i = 0; i < SZ; i++) 
        for(int j = 0; j < SZ; j++, bit <<= 1) 
            if((bit & start) == 0 && (bit & end) > 0) 
            {
                cout << i << " " << j << endl; 
                return; 
            }
}

bool noOverlap(int y, int x, vii &board)
{
    for(int i = 0; i < 4; i++)
        if(board[i].first == y && board[i].second == x)
            return false;
    return true;
}

bool valid(int y, int x, vii &board)
{
    return y >= 0 && y < SZ && x >= 0 && x < SZ && noOverlap(y, x, board);
}



vl move(ll start)
{
    vii board = decode(start);
    vl ret;

    for(int i = 0; i < 4; i++)  // which check to move
    {
        for(int d = 0; d < 4; d++)  // which direction to move
        {
            int y = board[i].first, x = board[i].second;
            while(valid(y + dy[d], x + dx[d], board))
            {
                y += dy[d];
                x += dx[d];
            }
            pii temp = board[i];
            board[i] = {y, x};
            ret.push_back(getHash(board));
            board[i] = temp;
        }
    }

    return ret;
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    vii board (4);
    for(int i = 0; i < 4; i++) cin >> board[i].first >> board[i].second;
    ll start = getHash(board);

    vii last (4);
    for(int i = 0; i < 4; i++) cin >> last[i].first >> last[i].second;
    ll target = getHash(last);

    map<ll, int> dist;
    map<ll, ll> prev;

    dist[start] = 0;
    deque<ll> q = {start};

    int cur;
    while(!q.empty())
    {
        ll key = q.front(); q.pop_front();
        if(key == target) break;
        cur = dist[key];


        vl next = move(key);

        for(ll nxt : next)
        {
            if(nxt != start && dist[nxt] == 0)
            {
                q.push_back(nxt);
                dist[nxt] = cur + 1;
                prev[nxt] = key;
            }
        }
    }

    cout << dist[target] << endl;

    vl path;
    path.push_back(target);
    ll curHash = target, prevHash;
    while(curHash != start)
    {
        prevHash = prev[curHash];
        curHash = prevHash;
        path.push_back(curHash);
    }

    for(int i = path.size() - 1; i > 0; i--) 
    {
        outputMove(path[i], path[i-1]);
    }

    return 0;
}