#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef pair<int, int> pii;
typedef vector<vi> vvi;

#define sz(x) (int)(x).size()
#define all(x) begin(x), end(x)
#define nl '\n'

vi stack_id (1 << 20);

vector<deque<int>> stacks;
vi ans;

void move(int id, int from, int to)
{
    while(from != to)
    {
        assert(stacks[from].front() == id);

        ans.push_back(id);
        if(from != sz(stacks)-1 && from - 1 != 0) ans.push_back(id);

        stacks[from].pop_front();
        stacks[from-1].push_front(id);
        from--;
    }
}

void recurse(int offset, int n)
{
    if(n == 0) return;
    for(int i : stacks[n])
    {
        int id = (i - offset);
        int stck = stack_id[id];
        
        // move to stack #x
        move(i, n, stck);
    }

    for(int i = 0, off = 1; i < n; i++)
    {
        int tmp = sz(stacks[i]);
        recurse(off, i);
        off += tmp;
    }
}

void solve()
{
    int n; cin >> n;
    stacks = vector<deque<int>> (n+2);

    int v, nums = 1 << n;
    for(int i = 0; i < nums; i++)
    {
        cin >> v;
        stacks[n+1].push_back(v);
    }

    recurse(1, n+1);
    for(int a : ans)
        cout << a << " ";
    cout << nl;

    // cout << "num moves: " << sz(ans) << nl;
}   

int main(){
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    for(int d = 1, i = 1; d < sz(stack_id); i++, d <<= 1) stack_id[d] = i;
    for(int i = 1; i < sz(stack_id); i++) 
        if(stack_id[i] == 0) 
            stack_id[i] = stack_id[i-1];
    
    solve();
    return 0;
}