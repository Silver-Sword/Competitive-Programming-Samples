// Word Puzzle
// Problem I
// 2021 ICPC Mid-Atlantic USA Regional Contest

// Problem Excerpt
/*
    "Young Anna recently indulges in a word puzzle app on her phone. 
    A word puzzle is a single English word with several blanks. Each 
    blank represents a letter to be filled. For example, the word 
    “programming” may appear as a puzzle p_o_rammin_. When solving a 
    puzzle, Anna first clicks on a blank and then begins typing letters. The
    app automatically advances to the next blank once Anna types a letter. 
    When there are no more blanks to the right of the filled letter, the app 
    jumps back to the beginning of the word and advances from there. Anna keeps
    typing until all blanks are filled. To solve the puzzle
    p_o_rammin_, Anna may click on the first blank and type rgg. Alternatively, she may click
    on the second blank and then type ggr.

    One day Anna shows you a puzzle that she solved along with the sequence of letters she typed.
    Could you tell how many different puzzles can be the one that Anna solved? Two puzzles are
    different if they have blanks at different positions, e.g. if the puzzle word is programming and
    Anna typed rgg, there can be two possible puzzles: p_o_rammin_ and pro__ammin_. As the
    answer can be large, output the answer modulo 1,000,000,007."

    The puzzle string can be up to 10^5 characters in length.  The typed sequence can be up to 50 characters long.
*/

// Solution Description
/*
    Dynamic programming where the state space is 
    dp(index in typed string, index in puzzle string) = number of ways to fill the rest of the string
    The rotations can be handled independently by rotating the typed string while making sure
    there are no duplicates.
*/

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef vector<ll> vl;
typedef vector<int> vi;

ll MOD = 1'000'000'007;

deque<char> pat;
string str, pattern;

ll solve(int p, int s, vector<vl> &dp)
{
    if(p >= pattern.size()) return 1;
    if(s >= str.size()) return 0;

    if(dp[p][s] != -1) return dp[p][s];

    ll ans = solve(p, s + 1, dp);
    if(pat[p] == str[s]) ans = (ans + solve(p + 1, s + 1, dp)) % MOD;

    return dp[p][s] = ans;
}

string getString()
{
    string s = "";
    for(char c : pat) s += c;
    return s;
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    cin >> str >> pattern;

    int n = str.size();

    ll ans = 0;
    unordered_map<string, int> map;

    for(char c : pattern) pat.push_back(c);

    for(int i = 0; i < pattern.size(); i++)
    {
        string key = getString();
        if(map[key] != 1)
        {
            map[key] = 1;
            vector<vl> dp (pattern.size(), vl (str.size(), -1));
            ll temp =  solve(0, 0, dp);
            ans = (ans + temp) % MOD;
        }

            char first = pat.front();
            pat.pop_front();
            pat.push_back(first);
        
    }

    cout << ans << endl;
}