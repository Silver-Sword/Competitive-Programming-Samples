// Push a Box
// Problem credits: Nathan Pinsker

// Problem Description
/*
    You are given an R by C grid, where '.' is an open cell, '#' is a blocked cell,
    'A' is the starting position of the character, and 'B' is the starting position of the box.
    You are then given q (<= 50,000) queries that each provide a position.  For each of those queries,
    output whether or not the character can push the box to the target query position.  The character
    can push along cardinal directions, but neither the character nor the box can traverse outside
    the grid or over '#' cells.
*/

// Solution Description
/*
    The character can only push a box in a direction if they can stand on the other side.
    The box may block the character's ability to move to its other side.  Use 2VCCs to 
    determine whether the character can move around the box.
    Then bfs from the starting positions to see if the character can push the box to
    every/any cell in the grid.
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

vi dy = {0, 0, 1, -1};
vi dx = {1, -1, 0, 0};

vector<string> board;
int rows, cols, queries, edgeCounter;
vector<vi> compon;

vi num, st;
vector<vector<pii>> ed;
vii edge;
int Time;
template<class F>
int dfs(int at, int par, F& f){
    int me = num[at] = ++Time, e, y, top = me;
    for (auto pa : ed[at]) if (pa.second != par) {
        tie(y, e) = pa;
        if (num[y]) {
            top = min(top, num[y]);
            if (num[y] < me)
                st.push_back(e);
        } else {
            int si = sz(st);
            int up = dfs(y, e, f);
            top = min(top, up);
            if (up == me) {
                st.push_back(e);
                f(vi(st.begin() + si, st.end()));
                st.resize(si);
            }
            else if (up < me) st.push_back(e);
            else {
                // bridge case: same as other case
                st.push_back(e);
                f(vi(st.begin() + si, st.end()));
                st.resize(si);
            }
        }
    }
    return top;
}

template<class F>
void bicomps(F f){
    num.assign(sz(ed), 0);
    rep(i,0,sz(ed)) if (!num[i]) dfs(i, -1, f);
}

vi metaMap;

int N;

bool valid(int y, int x)
{
    return y >= 0 && x >= 0 && y < rows && x < cols;
}

int getId(int y, int x)
{
    return y * cols + x;
}

int getId(int y, int x, int dir)
{
    return getId(y, x) * 4 + dir;
}

pii getPoint(char target)
{
    for(int i = 0; i < rows; i++)
        for(int j = 0; j < cols; j++)
            if(board[i][j] == target)
                return {i, j};

    assert(false);
    return {-1, -1};
}

int findStart(pii &A, pii &B, vector<vector<pii>> &graph)
{
    int start = getId(A.first, A.second), target = getId(B.first, B.second);

    deque<int> q;
    vi used (N);
    used[start] = true;
    q.push_back(start);

    while(!q.empty())
    {
        int node = q.front(); q.pop_front();

        if(node == target) break;
        
        for(pii p : graph[node])
        {
            int next = p.first;
            if(used[next]) continue;

            used[next] = true;
            q.push_back(next);
        }
    }

    if(used[target])
    {
        for(int d = 0; d < 4; d++)
        {
            int y = B.first + dy[d], x = B.second + dx[d];

            if(valid(y, x) && used[getId(y, x)])
                return getId(B.first, B.second, d);
        }
    }

    return -1;
}


void BR() {
    int metaNodes = 0;
    bicomps([&] (const vi edgelist)
    {  
        for(int eId : edgelist) 
        {
            compon[edge[eId].first].push_back(metaNodes);
            compon[edge[eId].second].push_back(metaNodes);
        }
        ++metaNodes;
    });
}


bool sameSet(int y, int x, int i, int j)
{
    int a = getId(y, x), b = getId(i, j);
    for(int A : compon[a])
        for(int B : compon[b])
            if(A == B)
                return true;
    return false;
}

void bfs(int start, vector<vi> &reachable)
{
    deque<int> q;
    q.push_back(start);
    vi used (rows * cols * 4);
    used[start] = true;

    while(!q.empty())
    {
        int nodeKey = q.front(); q.pop_front();
        int By = nodeKey / 4 / cols, Bx = (nodeKey / 4) % cols, dir = nodeKey % 4;
        int Ay = By + dy[dir], Ax = Bx + dx[dir];

        int y, x;

        for(int d = 0; d < 4; d++)  // d is the (new) direction of A
        {
            y = By + dy[d]; x = Bx + dx[d];
            if(valid(y, x) && !used[getId(By, Bx, d)] && sameSet(Ay, Ax, y, x))
            {
                int next = getId(By, Bx, d);
                q.push_back(next);
                used[next] = true;
            }
        }

        // and try to push B
        y = By - dy[dir]; x = Bx - dx[dir];
        if(valid(y, x) && !used[getId(y, x, dir)] && board[y][x] != '#')
        {
            reachable[y][x] = true;
            int next = getId(y, x, dir);
            q.push_back(next);
            used[next] = true;
        }
    }
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    cin >> rows >> cols >> queries;
    N = rows * cols;
    metaMap = vi (N, -1);
    compon = vector<vi> (N);

    board = vector<string> (rows);
    for(int i = 0; i < rows; i++) cin >> board[i];

    // set up adj
    ed = vector<vector<pii>> (rows * cols);
    edgeCounter = 0;
    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < cols; j++)
        {
            if(board[i][j] == '#') continue;
            if(j != cols - 1 && board[i][j+1] != '#')
            {
                ed[getId(i, j + 1)].emplace_back(getId(i, j), edgeCounter);
                ed[getId(i, j)].emplace_back(getId(i, j + 1), edgeCounter++);
                edge.push_back({getId(i, j), getId(i, j+1)});
            }

            if(i != rows - 1 && board[i+1][j] != '#')
            {
                ed[getId(i + 1, j)].emplace_back(getId(i, j), edgeCounter);
                ed[getId(i, j)].emplace_back(getId(i + 1, j), edgeCounter++);
                edge.push_back({getId(i, j), getId(i+1, j)});
            }
        }
    }

    // set up groups using articulation points

    vector<vi> reachable (rows, vi (cols));
    deque<int> q;
    pii A = getPoint('A');
    pii B = getPoint('B');

    reachable[B.first][B.second] = true;

    // get the first reachable A-B position
    int start = findStart(A, B, ed);

    // if q is empty, no reachable positions
    // otherwise, bfs
    if(start != -1)
    {
        BR();
        bfs(start, reachable);
    }

    int y, x;
    while(queries--)
    {
        cin >>y >> x; y--; x--;
        cout << (reachable[y][x] ? "YES" : "NO") << endl;
    }

    return 0;
}