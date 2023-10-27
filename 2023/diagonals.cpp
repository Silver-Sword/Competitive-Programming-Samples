#pragma GCC optimize("O3,unroll-loops")
#pragma GCC target("avx2,bmi,bmi2,lzcnt,popcnt")
#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef pair<int, int> pii;
typedef vector<vi> vvi;

#define nl '\n'
#define sz(x) (int)(x).size()
#define all(x) begin(x), end(x)

const int DEBUG = false;

struct RollbackUF {
    vi e; vector<pii> st;
    RollbackUF(int n) : e(n, -1) {}
    int size(int x) { return -e[find(x)]; }
    int find(int x) { return e[x] < 0 ? x : find(e[x]); }
    int time() { return sz(st); }
    void rollback(int t) {
        for (int i = time(); i --> t;)
            e[st[i].first] = st[i].second;
        st.resize(t);
    }
    bool join(int a, int b) {
        a = find(a), b = find(b);
        if (a == b) return false;
        if (e[a] > e[b]) swap(a, b);
        st.push_back({a, e[a]});
        st.push_back({b, e[b]});
        e[a] += e[b]; e[b] = a;
        return true;
    }
};

vector<string> ans;
vector<string> grid;
vector<vi> cand;
int n;

int key(int r, int c)
{
    if(DEBUG) cout << "\t\t(r=" << r << ", c=" << c << ")=>" << (r * (n+1) + c) << endl;
    return (r * (n+1) + c);
}

bool check()
{
    for(string &str : grid)
        for(char c : str)
            if(c != '0' && c != '+')
                return false;
    return true;
}

vvi order;

bool recurse(int idx, RollbackUF &uf)
{
    if(idx >= sz(order)) return true;
    
    int r = order[idx][1];
    int c = order[idx][2];

    auto canLeave = [&] (int y, int x) -> bool
    {
        if(grid[y][x] == '+') return true;
        return grid[y][x] - '0' < cand[y][x];
    };

    auto update = [&] (int r1, int c1, int r2, int c2) -> void
    {
        if(grid[r1][c1] != '+') grid[r1][c1]--;
        if(grid[r2][c2] != '+') grid[r2][c2]--;
        cand[r][c]--, cand[r][c+1]--, cand[r+1][c]--, cand[r+1][c+1]--;
        uf.join(key(r1, c1), key(r2, c2));
    };

    auto undo = [&] (int r1, int c1, int r2, int c2, int time) -> void
    {
        if(grid[r1][c1] != '+') grid[r1][c1]++;
        if(grid[r2][c2] != '+') grid[r2][c2]++;
        cand[r][c]++, cand[r][c+1]++, cand[r+1][c]++, cand[r+1][c+1]++;
        if(DEBUG) cout << "\t\trollback to " << time << endl;
        uf.rollback(time);
    };

    auto process = [&] (int r1, int c1, int r2, int c2, char ch) -> bool
    {
        if(DEBUG) cout << "\t\tr1=" << r1 << ", c1=" << c1 << ", r2=" << r2 << ", c2=" << c2 << ", ch=" << ch << endl;
        if(grid[r1][c1] != '0' && grid[r2][c2] != '0' && uf.find(key(r1, c1)) != uf.find(key(r2, c2)))
        {
            int time = uf.time();
            if(DEBUG) cout << "\tupdate r=" << r << " c=" << c << " with " << ch << " (" << r1 << ", " << c1 <<") and (" << r2 << ", " << c2 << ") " << endl;
            update(r1, c1, r2, c2);
            ans[r][c] = ch;
            if(recurse(idx + 1, uf))
                return true;
            ans[r][c] = 'x';
            undo(r1, c1, r2, c2, time);
        }

        return false;
    };

    // try forward
    if(DEBUG) cout << "recurse r=" << r << ", c=" << c << endl;
    if(canLeave(r, c+1) && canLeave(r+1, c) && process(r, c, r+1, c+1, '\\')) return true;
    if(DEBUG) cout << "recurse r=" << r << ", c=" << c << endl;

    // // try backward
    if(canLeave(r, c) && canLeave(r+1, c+1) && process(r, c+1, r+1, c, '/')) return true;
    if(DEBUG) cout << "recurse r=" << r << ", c=" << c << endl;

    // none
    return false;
}

void solve()
{
    cin >> n;
    ans = vector<string> (n, string(n, 'x'));
    grid = vector<string> (n+1);
    cand = vector<vi> (n+1, vi (n+1));
    for(int i = 0; i <= n; i++) 
    {
        cin >> grid[i];
    }

    vi dy = {0, 0, 1, 1};
    vi dx = {0, 1, 0, 1};
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
        {
            order.push_back({0, i, j});
            for(int d = 0; d < 4; d++)
            {
                cand[i+dy[d]][j+dx[d]]++;
                if(grid[i+dy[d]][j+dx[d]] != '+') order.back().front()++;
            }
        }
    sort(all(order));
    reverse(all(order));
    RollbackUF uf ((n+1)*(n+1));
    if(DEBUG) cout << "rollback size=" << (n+1)*(n+1) << endl;

    recurse(0, uf);

    for(int i = 0; i < n; i++)
    {
        cout << ans[i] << nl;
        for(char c : ans[i]) assert(c != 'x');
    }
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    solve();
    return 0;
}