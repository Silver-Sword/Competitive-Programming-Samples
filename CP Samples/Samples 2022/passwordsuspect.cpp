// Password Suspects
// Problem I
// 2008 World Finals ACM ICPC

/*
Problem Description
You are given a set of strings and a target length.  Determine the number of strings of
the target length that contain all of the given set of strings as substrings.  The given
strings may be overlapping in the resulting string.

The target length is up to 25 and the number of substrings is at most 10.
*/

// Solution Description:
// Use Aho Corasick to create a finite automaton which will determine the
// current state in each of the pattern strings at any point.  Then perform a 
// bitmask dp on the automaton, such
// that the dp state is
// dp(bitmask of satisfied patterns, index of the target string, aho corasick automaton state id) = number of ways to make a target string from here

#include <bits/stdc++.h>
using namespace std;

#define rep(i, a, b) for(int i = a; i < b; i++)
#define all(x) begin(x), end(x)
#define sz(x) (int) (x).size()

typedef long long ll;
typedef vector<int> vi;
typedef vector<ll> vl;
typedef vector<vl> vvl;
typedef pair<int, int> pii;

int BLANK = -1, NONE;

vector<vvl> dp;
int targetMask, globalId;


struct AhoCorasick
{
    enum {alpha=26, first = 'a'};
    struct Node
    {
        int back, next[alpha], start = -1, end = -1, nmatches = 0, maskMatches = 0, id;
        Node(int v)  { id = globalId ++; memset(next, v, sizeof(next));}
    };
    vector<Node> N;
    vi backp;
    void insert(string &s, int j)
    {
        assert(!s.empty());
        int n = 0;
        for(char c : s)
        {
            int &m = N[n].next[c - first];
            if(m == -1) {n = m = N.size(); N.emplace_back(-1);}
            else n = m;
        }
        if(N[n].end == -1) N[n].start = j;
        backp.push_back(N[n].end);
        N[n].end = j;
        N[n].nmatches++;
        N[n].maskMatches |= (1 << j);
    }

    AhoCorasick(vector<string> &pat) : N(1, -1) {
        rep(i,0,sz(pat)) insert(pat[i], i);
        N[0].back = sz(N);
        N.emplace_back(0);

        queue<int> q;
        for(q.push(0); !q.empty(); q.pop())
        {
            int n = q.front(), prev = N[n].back;
            rep(i,0,alpha)
            {
                int &ed = N[n].next[i], y = N[prev].next[i];
                if(ed == -1) ed = y;
                else{
                    N[ed].back = y;
                    (N[ed].end == -1 ? N[ed].end : backp[N[ed].start]) = N[y].end;
                    N[ed].nmatches += N[y].nmatches;
                    N[ed].maskMatches |= N[y].maskMatches;
                    q.push(ed);
                }
            }
        }
    }
};

ll solve(int mask, int lenLeft, int id, AhoCorasick &aho)
{
    if(dp[mask][lenLeft][id] != BLANK) 
    {
        return dp[mask][lenLeft][id];
    }
    if(lenLeft == 0)
    {
        return  dp[mask][lenLeft][id] = (mask == targetMask ? 1 : 0);
    } 

    // choose the next char
    ll ans = 0;
    for(int c = 0; c < 26; c++)
    {
        int next = aho.N[id].next[c];
        ans += solve(mask | aho.N[next].maskMatches, lenLeft - 1, next, aho);
    }

    return dp[mask][lenLeft][id] = ans;
}

void buildback(int mask, int lenLeft, int id, AhoCorasick &aho, string &cur, vector<string> &res)
{
    if(lenLeft == 0)
    {
        res.push_back(cur);
        return;
    }

    for(int c = 0; c < 26; c++)
    {
        int next = aho.N[id].next[c];
        if(solve(mask | aho.N[next].maskMatches, lenLeft - 1, next, aho) > 0)
        {
            cur += (char) (c + 'a');
            buildback(mask | aho.N[next].maskMatches, lenLeft - 1, next, aho, cur, res);
            cur.pop_back();
        }
    }
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int n, m, counter = 1;
    ll ans;
    cin >> n >> m;

    while(!(n == 0 && m == 0))
    {
        vector<string> str (m);
        vector<string> res;
        targetMask = (1 << (m)) - 1;
        NONE = m;
        int len = 0;

        for(int i = 0; i < m; i++)
        {
            cin >> str[i];
            len += str.size();
        }

        AhoCorasick aho (str);
        dp = vector<vvl> (1 << m, vvl (n + 1, vl (aho.N.size(), BLANK)));

        ans = solve(0, n, 0, aho);

        if(ans > 0 &&  ans <= 42)
        {
            string init = "";
            buildback(0, n, 0, aho, init, res);
        }

        cout << "Case " << counter << ": " << ans << " suspects" << endl;
        counter++;

        if(counter > 0 && counter <= 42)
        {
            sort(all(res));
            for(int i = 0; i < res.size(); i++)
                cout << res[i] << endl;
        }

        cin >> n >> m;
    }

    return 0;
}