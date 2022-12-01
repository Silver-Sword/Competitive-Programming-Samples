// Tri-Color Puzzle
// Problem B
// 2021 ICPC Greater NY Regional Contest

/*
Problem Description:
"A Tri-Color Puzzle is a triangular array of hexagon cells with S cells on each side for a
total of N = (S*(S+1))/2 cells."

"To solve the puzzle, each cell must be colored red, green or blue so that for each triplet
of cells with one cell above and two cells below, either all of the three cells are the same
color or each of the three cells is a different color."

Given a set of cells with some cells (or no cells) filled in, output the total 
number of ways to color all the cells.

The number of cells is at most 190.
*/

// Solution Idea:
/*
Each color can be represented as a 0, 1, or 2.  The resulting valid triplets will always be a multiple of 3 
and invalid triplets will not be a mutliple of 3 (regardless of color assignment). Use gaussian to determine
which values depend on each other and how many values are not forced to be a specific color.
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

bool DEBUG = false;
int MOD = 3;

// assumes only have 1 and -1
void subtract(int target, int subber, vector<vi> &matrix, int c)
{
    int fact = ((matrix[target][c] < 0) ^ (matrix[subber][c] < 0)) ? 1 : -1;
    for(int j = c; j < matrix[0].size(); j++)
    {
        matrix[target][j] = ((((matrix[target][j] + fact * matrix[subber][j]) % MOD) + MOD) % MOD);
        if(matrix[target][j] == 2) matrix[target][j] = -1;
    }

}

vi gaussian(vector<vi> &matrix)
{
    int rows = matrix.size(), cols = matrix[0].size();
    int r = 0;
    vi where (matrix.size(), -1);
    for(int c = 0; r < rows && c < cols - 1; c++)
    {
        for(int i = r; i < rows; i++)
            if(matrix[i][c])
            {
                swap(matrix[r], matrix[i]);
                break;
            }

        if(!matrix[r][c]) continue;
        where[r] = c;

        for(int i = r + 1; i < rows; i++)
            if(matrix[i][c])
                subtract(i, r, matrix, c);

        r++;
    }

    for(int i = rows - 1; i > 0; i--)
    {
        if(where[i] == -1) continue;
        for(int j = i - 1; j >= 0; j--)
        {
            if(matrix[j][where[i]]) subtract(j, i, matrix, where[i]);
        }
    }

    return where;

}

int ROWS, COLS;

vector<vi> initId(int n)
{
    int counter = 0;
    vector<vi> id (n);

    for(int i = 0; i < n;i++)
    {
        id[i] = vi (i + 1);

        for(int j = 0;j < i + 1;j++)
            id[i][j] = counter++;
    }

    return id;
}


int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int n, k; cin >> n >> k;

    int numRows = 0, numCells = (n * (n + 1)) / 2 + 1;
    for(int i = 1; i < n; i++)
        numRows += i;

    ROWS = numRows; COLS = numCells;

    vector<vi> matrix (numRows, vi (numCells));
    vector<vi> id = initId(n);

    int r = 0;
    for(int i = 0; i + 1 < n; i++)
    {
        for(int j = 0; j < i + 1;j ++)
        {
            matrix[r][id[i][j]] = 1;
            matrix[r][id[i+1][j]] = 1;
            matrix[r][id[i+1][j+1]] = 1;

            r++;
        }
    }

    while(k--)
    {
        int i, j, val; cin >> i >> j >> val; i--; j--;
        matrix.push_back(vi (numCells));
        matrix.back()[id[i][j]] = 1;
        matrix.back()[numCells - 1] = val;
    }

    // make the matrix into a compatible format
    for(int i = 0; i < matrix.size(); i++)
    {
        if(matrix[i].back() > 1) matrix[i].back() -= MOD;
    }

    vi where = gaussian(matrix);

    // count ons
    ll ans = 1;
    vi any (numCells);
    for(int i = 0; i < matrix.size(); i++)
    {
        if(where[i] == -1 && matrix[i].back() != 0)
        {
            ans = 0;
            break;
        }

        int on = 0;
        for(int j = where[i] + 1; j < matrix[i].size() - 1; j++)
            if(matrix[i][j])
                any[j] = 1;
    }

    for(int i = 0; i < any.size(); i++)
        if(any[i])
            ans *= 3;

    cout << ans << endl;

    return 0;
}