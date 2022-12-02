// Electricity
// Problem E
// 2012 NEERC, Northern Subregional Contest

// Problem Description
/*
You start with some number of A to B power strips and some number of B to A power strips.
In other words, A to B power strips plug into A sockets and then provide some number of B sockets.
B to A plugs are the opposite.  Each of these power strips have an output number of sockets that
are given in the input.  You are allowed to chain power strips.

You start with an A socket.  Output the maximum number of powered A sockets you can have.
*/

// Solution Description
/*
    as long as you can, plug in an A to B power strip if there are any B to A power strips left
    and plug in as many B to A power strips as possible.
*/

#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef long long ll;
typedef pair<int, int> pii;

#define all(x) begin(x), end(x)

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int n, m; cin >> n >> m;
    deque<int> AtoB (n), BtoA (m);

    for(int i = 0; i < n; i++) cin >> AtoB[i];
    for(int i = 0; i < m;i ++) cin >> BtoA[i];

    sort(all(AtoB));
    sort(all(BtoA));

    reverse(all(AtoB));
    reverse(all(BtoA));

    int ans = 1;
    
    while(!BtoA.empty() && !AtoB.empty())
    {
        ans --;
        int add = AtoB.front(); AtoB.pop_front();

        while(add-- && !BtoA.empty())
        {
            ans += BtoA.front();
            BtoA.pop_front();
        }
    }

    cout << ans << endl;
}