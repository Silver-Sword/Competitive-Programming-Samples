// Grid
// Problem G
// ACM ICPC 2012–2013, NEERC, Northern Subregional Contest

/*
The Problem:
Given integer intersection points between a line and a xy grid, find the original parameters of the grid.
The parameters of the grid are the starting and ending x/y 
and the distance between the grid lines (x and y are treated separately).

Coordinate points are between positive and negative 10^9.
The number of points does not exceed 10^5.
*/

// Solution Overview: 
// Each point must lie on either an x line or a y line
// Any 2 points will be sufficient to determine the line parameters of either the x or y grid
// Find the answer by brute forcing 2 points and trying to put them into either the x or y grid
// the first point is the first point overall and brute force the second point by checking them all
// determine if the y or x group (the other group) is possible after greedily visiting all points from the first group
// print the solution as soon as you find it

#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef long long ll;
typedef pair<int, int> pii;
typedef vector<ll> vl;
typedef pair<ll, ll> pll;
typedef vector<pii> vii;

typedef pair<pll, ll> Line;

#define space << " " <<
#define all(x) begin(x), end(x)

const ll BLANK = -1, INF = 2e9;
Line INVALID = {{-INF, -INF}, -INF};

void printSolution(Line xLine, Line yLine)
{
    cout << xLine.first.first << " " << xLine.first.second << " " << xLine.second << " " << yLine.first.first <<  " " << yLine.first.second << " " << yLine.second << endl;
}

// assumption: all point locations are distinct
Line forceSet1(int force, vi &one, vector<pii> &pt)
{
    ll inc = pt[force].first - pt[0].first;
    ll cur = pt[0].first, last = -1;

    for(int i = 0; i < pt.size(); i++)
    {
        if(pt[i].first == cur)
        {
            one[pt[i].second] = 1;
            cur += inc;
            last = pt[i].first;
        }

        else if(pt[i].first > cur)
        {
            break;
        }
    }

    return Line {{pt[0].first, last}, inc};
}

// assumption: (pt is the opposite of that was given in forceSet1)
Line forceSet2(vi &one, vector<pii> &pt)
{
    ll jumpSize = -1, start = -1, last = -1;

    for(pii &p : pt)
    {
        if(one[p.second]) continue;
        if(start == -1)
        {
            start = p.first;
        }

        else if(jumpSize == -1)
        {
            jumpSize = p.first - start;
        }

        else
        {
            if(jumpSize > 1) jumpSize = __gcd(jumpSize, p.first - start);
        }

        last = p.first;
    }

    if(start == -1) return {{pt[0].first, pt[0].first}, 1};   // no points in set 2
    if(jumpSize == -1) return {{start, start}, 1};    // 1 point in set 2

    ll cur = start;
    for(int i = 0; i < pt.size(); i++)
    {
        if(pt[i].first == cur)
        {
            cur += jumpSize;
        }

        else if(!one[pt[i].second]) // needed to be in the set
        {  
            return INVALID;
        }
    }

    return {{start, last}, jumpSize};
}

// assumes x and y are sorted with pairs {pt, idx}
// and the scratch isn't a vertical or horizontal line
void solve(vii &x, vii &y)
{
    // put first in x
    vi one (x.size());
    for(int i = 1; i < x.size(); i++)
    {
        fill(all(one), 0);
        Line xLine = forceSet1(i, one, x);
        Line yLine = forceSet2(one, y);
        // can speed up by combing these two methods

        if(yLine != INVALID)
        {
            printSolution(xLine, yLine);
            return;
        }

    }

    // put first in y
    for(int i = 1; i < y.size(); i++)
    {
        fill(all(one), 0);
        Line yLine = forceSet1(i, one, y);
        Line xLine = forceSet2(one, x);

        if(xLine != INVALID)
        {
            printSolution(xLine, yLine);
            return;
        }
    }
    
    // do not reach here
    assert(false);
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int n;
    cin >> n;

    vii x (n), y (n);

    for(int i = 0; i < n; i++)
    {
        cin >> x[i].first >> y[i].first;
        x[i].second = y[i].second = i;
    }

    sort(all(x));
    sort(all(y));

    if(x[0].first == x[1].first)
    {
        vi def (n);
        Line l = forceSet1(1, def, y);
        printSolution({{x[0].first ? 0 : 1, x[0].first ? 0 : 1}, 1}, l);
    }

    else if(y[0].first == y[1].first)
    {
        vi def (n);
        Line l = forceSet1(1, def, x);
        printSolution(l, Line {{y[0].first ? 0 : 1, y[0].first ? 0 : 1}, 1});
    }

    else
    {
        solve(x, y);
    }
}
