// Agile Permutation
// Problem A
// 2019 BSUIR Open Programming Championship Semifinal

// Problem Description
/*
    You are given a permutation of 1 to n, and 2 integers, a and b.
    For the price of b, you can randomly shuffle the permutation.
    For the price of a, you can swap any two elements in the permutation.

    Output the expected cost of ordering the permutation.

    n <= 20
    a, b <= 1000
*/

// Solution Description
/*
    Calculate the distribution of total necessary swaps for any permutation. 
    This will give the probability distribution of randomly arriving at any permutation.
    The optimal strategy will be shuffle the permutation until you reach one with less than or equal to
    some target number of needed swaps.
    Brute force this target swaps.  Represent the expected costs with a matrix (series of equations) 
    and run matrix exponentiation to get an infinite simulation.
*/
#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef long long ll;
typedef vector<ll> vl;
typedef pair<int, int> pii;
typedef vector<double> vd;
typedef vector<vector<double>> Matrix;

double INF = 1e18;

vl invDist(int n)
{
    vl arr (n);

    arr[0] = 1;

    for(int last = 1; last < n; last++)
    {
        for(int i = last; i > 0; i--)
            arr[i] = arr[i] + arr[i-1] * last;
    }

    return arr;
}

Matrix identity(int n)
{
    Matrix mat (n, vd (n));
    for(int i = 0; i < n; i++)
        mat[i][i] = 1;
    return mat;
}

Matrix multiply(Matrix &A, Matrix &B)
{
    Matrix res (A.size(), vd (B[0].size()));

    for(int i = 0; i < A.size(); i++)
    {
        for(int j = 0; j < B[0].size(); j++)
        {
            for(int k = 0; k < A[0].size(); k++)
                res[i][j] = (res[i][j] + A[i][k] * B[k][j]);
        }
    }

    return res;
}

Matrix matExpo(Matrix &mat, int exp)
{
    if(exp <= 0) return identity(mat.size());
    if(exp % 2 == 0)
    {
        Matrix temp = matExpo(mat, exp / 2);
        return multiply(temp, temp);
    }

    Matrix temp = matExpo(mat, exp - 1);
    return multiply(temp, mat);
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int n, a, b; cin >> n >> a >> b;

    vi arr (n); for(int i = 0; i < n; i++) cin >> arr[i];
    
    int start = 0;
    for(int i = 0; i < n; i++)
        while(arr[i] != i + 1)
        {
            swap(arr[i], arr[arr[i] - 1]);
            start++;
        }

    vl counts = invDist(n);
    ll nFact = 1;
    for(int i = 2; i <= n; i++) nFact *= i;


    auto initMatrix = [&] (int row, int constant)
    {
        Matrix mat (n + 1, vd (n + 1));

        for(int i = 0; i <= row; i++)
            mat[i][i] = 1;
        
        for(int i = row + 1; i < n; i++)
        {
            for(int j = 0; j < n; j++)
                mat[i][j] = counts[j] / (double) nFact;
            mat[i][n] = 1;
        }

        mat[n][n] = 1;
        return mat;
    };

    double best = INF;
    for(int row = 0; row <= start; row++)
    {
        Matrix mat = initMatrix(row, b);

        vector<vd> inits (n + 1, vd (1));
        for(int i = 0; i <= row; i++) inits[i][0] = i * a;
        inits[n][0] = b;

        mat = matExpo(mat, 100000);
        vector<vd> res = multiply(mat, inits);
        best = min(best, res[start][0]);
    }

    cout << fixed << setprecision(11) << best << endl;

    return 0;
}