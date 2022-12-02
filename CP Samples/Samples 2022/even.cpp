// Even Substrings
// Problem E
// 2021 ICPC Mid-Atlantic USA Regional Contest

/*
Problem Description
You are given a string consisting of lowercase letters between a and f (inclusive).
Then, you are given queries of two types:
1. Given an index i and a letter x, change the character at index i to x
2. Given a range from l to r, print the the number of even substrings between index l and r (inclusive)

A substring is even if every letter appears an even number of times within that substring.

The string can have up to 2*10^5 characters and there are up to 2*10^5 queries.
*/

// Solution Idea:
// Use a segment tree to keep track of updates and calculations.
// To determine if a substring is even, you can use prefix hashes:
// if two prefix hashes are the same, the substring between them must be even

#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef long long ll;
typedef vector<ll> vl;
typedef pair<int, int> pii;

const int MAX = 1 << 6;

vi add(vi &a, vi &b)
{
    vi ret (MAX);
    for(int i = 0; i < MAX; i++) ret[i] = a[i] + b[i];
    return ret;
}

struct Tree
{
    Tree *left, *right;
    int L, R, mid, lazy;
    vi bits;

    Tree(int l, int r, vi &ray) : bits(MAX), L(l), R (r), lazy (0)
    {
        mid = (L + R) / 2; 

        if(L == R)
        {
            bits[ray[l]] ++;
            return;
        }

        left = new Tree(l, mid, ray);
        right = new Tree(mid + 1, r, ray);

        bits = add(left->bits, right->bits);
    }

    void updateLazy(int dif)
    {
        auto updateLazy = [&] ()
        {
            vi ret (MAX);
            for(int i = 0; i < MAX; i++) ret[i] = bits[i^dif];
            return ret;
        };

        bits = updateLazy();
        lazy ^= dif;
    }

    void prop()
    {
        if(!lazy) return;
        left->updateLazy(lazy);
        right->updateLazy(lazy);
        lazy = 0;
    }

    vi query(int l, int r)
    {
        if(L > r || l > R) return vi (MAX);
        if(L >= l && R <= r) return bits;

        prop();
        vi a = left->query(l, r), b = right->query(l, r);
        return add(a, b); 
    }

    void update(int l, int r, int lazy)
    {
        if(L > r || l > R) return;
        if(L >= l && R <= r)
        {
            updateLazy(lazy);
            return;
        }

        assert(L != R);
        prop();
        left->update(l, r, lazy);
        right->update(l, r, lazy);
        bits = add(left->bits, right->bits);
    }
};

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    string str; 
    cin >> str;
    vi prefix(str.size() + 1);

    auto getBit = [&] (char c)
    {
        return (1 << (c - 'a'));
    };

    for(int i = 0; i < str.size(); i++)
    {
        prefix[i+1] = prefix[i] ^ getBit(str[i]);
    }

    Tree tree (0, str.size(), prefix);
    int query;
    cin >> query;

    auto countBits = [&] (vi &bits)
    {
        ll count = 0;
        for(int i = 0; i < bits.size(); i++)
            if(bits[i] > 1)
                count += (bits[i] * ((ll)bits[i] - 1)) / 2;
        return count;
    };

    int type, s, e; char ch;
    while(query--)
    {
        cin >> type;

        if(type == 1)
        {
            cin >> s >> e;
            vi ranges = tree.query(s-1, e);
            cout << countBits(ranges) << '\n';
        }

        else
        {
            cin >> s >> ch;
            tree.update(s, str.size(), getBit(ch) ^ getBit(str[s-1]));
            str[s-1] = ch;
        }
    }

    return 0;
}