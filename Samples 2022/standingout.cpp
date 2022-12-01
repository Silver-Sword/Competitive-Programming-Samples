// Standing Out From the Herd
// Problem credits: Matt Fontaine

/*
Problem Description
You are given a set of up to 10^5 lowercase English strings.  The total characters across
all strings is at most 10^5.  You need to output the "uniqueness factor" of each of the strings.
The uniqueness-factor is defined as the number of unique substrings in the string that are not
shared with any of the other strings.
*/

// Solution Overview:
// Combine all the strings into one string, using $ between the strings to differentiate.
// Use a suffix array of the combined string to cacluate the overlapping substrings between
// each string.  Keep track of the overlap between same and unique strings as well to 
// update the calculation when sweeping across overlaps within the same string.

#include <bits/stdc++.h>
using namespace std;

#define rep(i, a, b) for(int i = a; i < b; i++)
#define all(x) begin(x), end(x)
#define sz(x) (int) x.size()

typedef vector<int> vi;
typedef long long ll;
typedef pair<int, int> pii;
typedef vector<ll> vl;
typedef vector<pii> vii;

struct SuffixArray
{
    vi sa, lcp;
    SuffixArray(string &s, int lim=256)
    {
        int n = sz(s) + 1, k = 0, a, b;
        vi x(all(s) + 1), y(n), ws(max(n, lim)), rank(n);
        sa = lcp = y, iota(all(sa), 0);
        for(int j = 0, p = 0; p < n; j = max(1, j * 2), lim = p)
        {
            p = j, iota(all(y), n - j);
            rep(i,0,n) if (sa[i] >= j) y[p++] = sa[i] - j;
            fill(all(ws), 0);
            rep(i,0,n) ws[x[i]]++;
            rep(i,1,lim) ws[i] += ws[i-1];
            for(int i = n; i--;) sa[--ws[x[y[i]]]] = y[i];
            swap(x,y), p = 1, x[sa[0]] = 0;
            rep(i,1,n) a = sa[i-1], b = sa[i], x[b] = (y[a] == y[b] && y[a+j] == y[b+j] ? p - 1 : p++);
        }

        rep(i,1,n) rank[sa[i]] = i;
        for(int i = 0, j; i < n - 1; lcp[rank[i++]] = k)
        for(k && k--, j = sa[rank[i] - 1]; s[i+k] == s[j+k]; k++);
    }
};

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int n;
    cin >> n;

    vi splits (n);
    vii range (n);
    vl ans (n);

    string str, val;

    for(int i = 0; i < n; i++)
    {
        range[i].first = str.size();
        cin >> val;
        str += val;
        splits.push_back(str.size());
        range[i].second = str.size();
        str += '$';
        ans[i] = (val.size() * (val.size() + 1)) / 2;
    }

    vi wordIdx (str.size());
    vi otherOverlap (str.size()), thisOverlap (str.size());

    for(int i = 1; i < str.size(); i++)
        wordIdx[i] = wordIdx[i-1] + (str[i-1] == '$' ? 1 : 0);

    SuffixArray sa (str);

    vi prev (str.size());
    for(int i = 1; i < sa.sa.size() - 1; i++)
    {
        if(str[sa.sa[i]] == '$') continue;

        int word1 = wordIdx[sa.sa[i]], word2 = wordIdx[sa.sa[i+1]];
        // if(DEBUG) cout << "\tword of idx " << sa.sa[i] << " is " << word1 << " | word of idx " << sa.sa[i+1] << " is " << word2 << endl;
        int overlap = sa.lcp[i+1];
        int len1 = abs(sa.sa[i] - range[word1].second), len2 = abs(sa.sa[i+1] - range[word2].second);
        // if(DEBUG) cout << "\tremaining length of suffix 1: " << len1 << " | remaining len of suffix 2: " << len2 << endl;

        overlap = min(overlap, min(len1, len2));

        if(word1 != word2)
        {
            // otherOverlap[word1]
            ans[word1] -= (max(overlap, prev[i-1]) - prev[i-1]);
            ans[word2] -= overlap;
        }

        else
        {
            ans[word1] -= overlap;
            prev[i] = min(overlap, prev[i-1]);
        }

        if(word1 != word2)
        {
            prev[i] = overlap;
        }
    }

    for(int i = 0; i < n; i ++)
        cout << ans[i] << endl;
}