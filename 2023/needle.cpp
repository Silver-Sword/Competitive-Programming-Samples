// Needle
// CTU Open 2022-2023

// Problem Description
/*
    The input includes a starting and ending point, as well as a number of 
    clouds, each containing its own set of points.

    Cloud boundaries are the convex hull of the set of cloud points.

    Determine the minimum distance between the  starting and ending point.  
    Cloud boundaries may not be crossed, but may be touched.

    There are at most 502 specified points, at most 200 clouds, and the 
    maximum absolute value of any coordinate is 2000.
*/

// Solution Description
/*
    Compute the convex hull of each cloud.  Each point of the convex hulls, as well
    as the starting and ending point will be a node in the graph.  Build the graph 
    where each edge represents that the two nodes/points can reach each other without
    passing through any clouds.  Do this with a naive O(n^3) check for each 
    pair of points and each line.  Then run bfs on the graph to get the minimum 
    distance.

    O(N^2) where N is the number of points
*/

/* Template Code and Program Setup */
#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef long long ll;
typedef vector<ll> vl;
typedef vector<vl> vvl;
typedef pair<int, int> pii;
typedef vector<vi> vvi;
typedef vector<pii> vii;
typedef vector<vii> vvii;

#define nl '\n'
#define sz(x) (int)(x).size()
#define all(x) begin(x), end(x)

/* Hackpack */
template<class T> int sgn(T x) {return (x > 0) - (x < 0);}
template<class T>
struct Point
{
    typedef Point P;
    T x, y;
    explicit Point(T x = 0, T y = 0) : x(x), y(y) {}
    bool operator< (P p) const {return tie(x, y) < tie(p.x, p.y);}
    bool operator== (P p) const {return tie(x, y)==tie(p.x, p.y);}
    P operator -(P p) const {return P(x-p.x, y-p.y);}
    P operator +(P p) const {return P(x+p.x, y+p.y);}
    P operator *(T d) const {return P(x*d, y*d);}
    P operator /(T d) const {return P(x/d, y/d);}

    T dot(P p) const {return x*p.x+y*p.y;}
    T cross(P p) const {return x*p.y-y*p.x;}
    T cross(P a, P b) const {return (a-*this).cross(b-*this);}
    T dist2() const {return x*x+y*y;}
    double dist() const {return sqrt(dist2());} 
    friend ostream& operator<<(ostream &os, P p){
        return os << "(" << p.x << ", " << p.y << ")";
    }
};

template<class P> bool onSegment(P s, P e, P p)
{
    return p.cross(s, e) == 0 && (s - p).dot(e - p) <= 0;
}

template<class P> vector<P> segInter(P a, P b, P c, P d)
{
    auto oa = c.cross(d, a), ob = c.cross(d, b),
         oc = a.cross(b, c), od = a.cross(b, d);
    
    if(sgn(oa) * sgn(ob) < 0 && sgn(oc) * sgn(od) < 0)
        return {(a * ob - b * oa) / (ob - oa)};
    set<P> s;
    if(onSegment(c, d, a)) s.insert(a);
    if(onSegment(c, d, b)) s.insert(b);
    if(onSegment(a, b, c)) s.insert(c);
    if(onSegment(a, b, d)) s.insert(d);
    return {all(s)};
}

typedef Point<ll> P;
vector<P> convexHull(vector<P> pts)
{
    if(sz(pts) <= 1) return pts;
    sort(all(pts));
    vector<P> h(sz(pts) + 1);
    int s = 0, t = 0;
    for(int it = 2; it--; s = --t, reverse(all(pts)))
        for(P p : pts)
        {
            while(t >= s + 2 &&  h[t-2].cross(h[t-1], p) <= 0) t--;
            h[t++] = p;
        }
    return {h.begin(), h.begin() + t - (t == 2 && h[0] == h[1])};
}

/* Solution Code */
typedef pair<int, double> pid;
typedef vector<pid> vid;
void solve()
{
    // take in input
    int n, x, y; cin >> n;
    P s, t;
    cin >> x >> y; s = P (x, y);
    cin >> x >> y; t = P (x, y);
    vector<vector<P>> cloud;
    for(int i = 0; i < n; i++)
    {
        int m; cin >> m;
        vector<P> pts;
        for(int i = 0; i < m; i++)
        {
            cin >> x >> y;
            pts.push_back(P(x, y));
        }

        cloud.push_back(convexHull(pts));
    }

    // graph ids
    map<P, int> key;
    key[s] = 0;
    key[t] = 1;
    for(int i = 0; i < sz(cloud); i++)
        for(int j = 0; j < sz(cloud[i]); j++)
        {
            key[cloud[i][j]] = sz(key);
        }
    
    // make graph
    vector<vid> adj (sz(key));

    auto addEdge = [&] (P &a, P &b)
    {
        int u = key[a], v = key[b];
        double d = (a-b).dist();
        adj[u].push_back({v, d});
        adj[v].push_back({u, d});
    };

    auto good = [&] (P &a, P &b) -> bool
    {
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < sz(cloud[i]); j++)
            {
                P u = cloud[i][j], v = cloud[i][(j+1)%sz(cloud[i])];
                int uid = key[u], vid = key[v], aid = key[a], bid = key[b];

                if(uid == aid || uid == bid || vid == aid || vid == bid) continue;
                
                if(!segInter(a, b, u, v).empty()) 
                {
                    return false;
                }
            }
        }
        return true;
    };

    // edges between adjacent points in a cloud
    for(int i = 0; i < sz(cloud); i++)
        for(int j = 0; j < sz(cloud[i]); j++)
        {
            addEdge(cloud[i][j], cloud[i][(j+1)%sz(cloud[i])]);
        }
    
    // add edge btwn start and end
    if(good(s, t))
        addEdge(s, t);
    
    // add edges between the start/end points and a cloud
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < sz(cloud[i]); j++)
        {
            if(good(s, cloud[i][j]))
            {
                addEdge(s, cloud[i][j]);
            }
            if(good(t, cloud[i][j]))
            {
                addEdge(t, cloud[i][j]);
            }
        }
    }

    // add edge between clouds
    for(int i = 0; i < n; i++)
    {
        for(P a : cloud[i])
        {
            for(int j = 0; j < n; j++)
            {
                if(i == j) continue;
                for(P b : cloud[j])
                {
                    if(good(a, b))
                    {
                        addEdge(a, b);
                    }
                }
            }
        }
    }

    const int BLANK = -1;
    priority_queue<pair<double, int>, vector<pair<double, int>>, greater<pair<double, int>>> q;
    vector<double> dist (sz(key), BLANK);
    q.push({0, 0});

    while(!q.empty())
    {
        double weight = q.top().first;
        int idx = q.top().second;
        q.pop();

        if(dist[idx] != BLANK) continue;
        dist[idx] = weight;

        for(pid next : adj[idx])
        {
            if(dist[next.first] != BLANK) continue;
            q.push({weight + next.second, next.first});
        }
    }

    cout << fixed << setprecision(15) << dist[1] << nl;
}

// driver function
int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    solve();
    return 0;
}