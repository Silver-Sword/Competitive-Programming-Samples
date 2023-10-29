// Railroad Sort
// ITMO Programming Contest Summer School 2014

// Problem Description
/*
    There are n stacks, numbered 1 to n.  There is also a permutation of 1 to 2^n
    waiting to enter the first stack.  A value can be moved from a stack (or the
    start) to the next stack.  Only the top value of a stack (or the front of the starting
    list) may be moved.  If the value is moved from the last stack, it reaches
    the end.  Determine a way to move the 2^n values across the stacks in such a way
    to end with an ordered permutation.
    
    1 <= n <= 13

    O(n * 2^n)
*/

// Solution Description
/*
    The main idea of this solution is to use the stack farthest from the exit to hold
    the biggest half of the elements.  For the remaining elements, use this approach
    on the remaining stacks.  Send the minimum element to the exit.
*/

/* Template Code and Program Setup */
#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef pair<int, int> pii;
typedef vector<vi> vvi;

#define sz(x) (int)(x).size()
#define all(x) begin(x), end(x)
#define nl '\n'

/* Solution Code */
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

// driver function
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