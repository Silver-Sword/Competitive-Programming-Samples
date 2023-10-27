#include <bits/stdc++.h>
using namespace std;

typedef vector<int> vi;
typedef long double ld;
typedef vector<vi> vvi;
typedef long long ll;
typedef vector<ll> vl;

#define sz(x) (int)(x).size()
#define nl '\n'
#define rep(i,a,b) for(int i = a; i < (b); i++)
#define all(x) begin(x), end(x)

template <class T> int sgn(T x) { return (x > 0) - (x < 0); }
template<class T>
struct Point {
        typedef Point P;
        T x, y;
        explicit Point(T x=0, T y=0) : x(x), y(y) {}
        bool operator<(P p) const { return tie(x,y) < tie(p.x,p.y); }
        bool operator==(P p) const { return tie(x,y)==tie(p.x,p.y); }
        P operator+(P p) const { return P(x+p.x, y+p.y); }
        P operator-(P p) const { return P(x-p.x, y-p.y); }
        P operator*(T d) const { return P(x*d, y*d); }
        P operator/(T d) const { return P(x/d, y/d); }
        T dot(P p) const { return x*p.x + y*p.y; }
        T cross(P p) const { return x*p.y - y*p.x; }
        T cross(P a, P b) const { return (a-*this).cross(b-*this); }
        T dist2() const { return x*x + y*y; }
        double dist() const { return sqrt((double)dist2()); }
        // angle to x-axis in interval [-pi, pi]
        double angle() const { return atan2(y, x); }
        P unit() const { return *this/dist(); } // makes dist()=1
        P perp() const { return P(-y, x); } // rotates +90 degrees
        P normal() const { return perp().unit(); }
        // returns point rotated 'a' radians ccw around the origin
        P rotate(double a) const {
                return P(x*cos(a)-y*sin(a),x*sin(a)+y*cos(a)); }
        friend ostream& operator<<(ostream& os, P p) {
                return os << "(" << p.x << "," << p.y << ")"; }
};

template<class P>
int sideOf(P s, P e, P p) { return sgn(s.cross(e, p)); }

template<class P>
int sideOf(const P& s, const P& e, const P& p, double eps) {
        auto a = (e-s).cross(p-s);
        double l = (e-s).dist()*eps;
        return (a > l) - (a < -l);
}

template<class P> bool onSegment(P s, P e, P p) {
        return p.cross(s, e) == 0 && (s - p).dot(e - p) <= 0;
}

typedef Point<ll> P;
bool inHull(const vector<P>& l, P p, bool strict = true) {
        int a = 1, b = sz(l) - 1, r = !strict;
        if (sz(l) < 3) return r && onSegment(l[0], l.back(), p);
        if (sideOf(l[0], l[a], l[b]) > 0) swap(a, b);
        if (sideOf(l[0], l[a], p) >= r || sideOf(l[0], l[b], p)<= -r)
                return false;
        while (abs(a - b) > 1) {
                int c = (a + b) / 2;
                (sideOf(l[0], l[c], p) > 0 ? b : a) = c;
        }
        return sgn(l[a].cross(l[b], p)) < r;
}

typedef Point<ll> Pl;
const int DEBUG = false;

Pl addVectors(vector<Pl> &poly, ll &f, vector<Pl> &vects)
{
    Pl lo = poly[0] * f;
    for(int i = 1; i < sz(poly); i++)
    {
        Pl tmp = poly[i] * f;
        if(tmp.x < lo.x || (tmp.x == lo.x && tmp.y > lo.y))
            lo = tmp;
    }

    for(int i = 0; i < sz(poly); i++)
    {
        vects.push_back((poly[(i+1)%sz(poly)] - poly[i]) * f);
    }

    if(DEBUG) cout << "\t\tlo=" << lo << nl;
    return lo;
}

vector<Pl> makeHull(vector<Pl> &vects, Pl &start)
{
    auto compare = [&] (Pl a, Pl b)
    {
        if((a.x > 0 || (a.x == 0 && a.y <= 0)) ^ (b.x > 0 || (b.x == 0 && b.y <= 0)))
        {
            return (a.x > 0 || (a.x == 0 && a.y <= 0));
        }
        return sgn(a.cross(b)) > 0;
    };

    sort(all(vects), compare);

    // remove colinear vectors
    vector<Pl> v;
    for(int i = 0; i < sz(vects); i++)
    {
        if(v.empty() || v.back().cross(vects[i]) != 0)
            v.push_back(vects[i]);
        else
            v.back() = v.back() + vects[i];
    }
    swap(vects, v);

    // convert to points?
    vector<Pl> hull; // (1, Pl(0, 0));
    ll x = start.x, y = start.y;
    for(const Pl &p : vects)
    {
        hull.push_back(Pl(x, y));
        x += p.x; y += p.y;
    }

    if(DEBUG)
    {
        cout << "\tVectors: ";
        for(Pl p : vects) cout << p << " ";
        cout << " ==> Hull: ";
        for(Pl p : hull) cout << p << " ";
        cout << nl;
    }
    return hull;
}

void solve()
{
    // set up vars
    vector<Pl> poly1, poly2;
    ll x, y;
    int m, n;

    // take input
    cin >> m;
    for(int i = 0; i < m; i++)
    {
        cin >> x >> y;
        poly1.push_back(Pl(x, y));
    }

    cin >> m;
    for(int i = 0; i < m;i ++)
    {
        cin >> x >> y;
        poly2.push_back(Pl(x, y));
    }

    if(DEBUG)
    {
        cout << "Poly1: "; for(Pl p : poly1) cout << p << " "; cout << nl;
        cout << "Poly2: "; for(Pl p : poly2) cout << p << " "; cout << nl;      
    }

    cin >> n;
    vector<Pl> cand;
    for(int i = 0; i < n; i++)
    {
        cin >> x >> y;
        cand.push_back(Pl(x, y));
    }

    vi ans (n);
    auto addValid = [&] (vector<Pl> &hull)
    {
        for(int i = 0; i < n;i++)
        {
            if(ans[i]) continue; // already known valid

            // point in hull checks
            if(DEBUG) cout << "\t\tchecking for point " << cand[i] << " in hull (res=" << inHull(hull, cand[i], false) << ")" << nl;
            if(inHull(hull, cand[i], false))
                ans[i] = true;
        }
    };

    auto mink = [&] (ll f1, ll f2)
    {
        vector<Pl> vects;
        Pl offset;
        offset = offset + addVectors(poly1, f1, vects);
        offset = offset + addVectors(poly2, f2, vects);
        if(DEBUG) cout << "\t\toffset=" << offset << nl;
        // Pl offset = poly1[0] + poly2[0];
        vector<Pl> hull = makeHull(vects, offset);
        return hull;
    };

    vector<Pl> hull;
    // shape type 1
    // make minkowski sum
    hull = mink(2, -1);
    addValid(hull);

    // shape type 2
    hull = mink(-1, 2);
    addValid(hull);

    // shape type 3
    hull = mink(1, 1);
    for(int i = 0; i < n; i++) cand[i] = cand[i] * 2LL;
    addValid(hull);

    // output
    for(int i = 0; i < n; i++) cout << (ans[i] ? "Y" : "N");
    cout << nl;
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    solve();
    return 0;
}