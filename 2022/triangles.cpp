// Three Triangles
// Problem G
// 2020 ICPC Greater NY Regionals

// Problem Description
/*
    The problem gives you the points of a triangle and asks you to find two other
    triangles based on the original.  The problem gives exact specifications and
    many of the new points are based on line extensions and intersections.

    The problem wants you to output the area of each of the three triangles.

    Visit this link for the full problem: http://acmgnyr.org/year2020/judge-data/G-ThreeTriangles/G-ThreeTriangles.pdf 
*/

// Solution Description
/*
    The problem statement is essentially a list of instructions.  Follow them
    using triangle properties, cross product, dot product, and kactl's very
    helpful Point class.
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
pair<int, P> lineInter(P s1, P e1, P s2, P e2) {
	auto d = (e1 - s1).cross(e2 - s2);
	if (d == 0) // if parallel
		return {-(s1.cross(e1, s2) == 0), P(0, 0)};
	auto p = s2.cross(e1, e2), q = s2.cross(e2, s1);
	return {1, (s1 * p + e1 * q) / d};
}

typedef Point<double> Pd;
typedef pair<Pd, Pd> Line;

Pd reflect(Pd &start, Line &line)
{
    Pd L;
    // vertical bisector
    if(line.first.y == line.second.y)
    {
        L = Pd(start.x, line.first.y);
    }

    else
    {
        // need to actually calc intersection
        double m = - (line.first.x - line.second.x) / (line.first.y - line.second.y);
        L = Pd(100.0, m * (100.0 - start.x) + start.y);

        L = lineInter(line.first, line.second, start, L).second;
    }

    return Pd(L.x + (L.x - start.x), L.y + (L.y - start.y));
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);
    cout << fixed << setprecision(4);

    double bx, cx, cy;
    cin >> bx >> cx >> cy;

    Point<double> A (0, 0), B (bx, 0), C(cx, cy);

    // area of original
    cout << abs(A.cross(B, C)) / 2.0 << " ";

    pair<Pd, Pd> hb = make_pair(Pd(0, (cx * bx) / cy), Pd(bx, 0));
    pair<Pd, Pd> pa = make_pair(Pd(0.5 * (bx + cx), 0.5*cy), Pd(0, (cx - bx) * (0.5 * (bx + cx)) / cy + 0.5 * cy ));

    Point<double> A2 (cx, 0.5 * (cy - cx * cx / cy)), B2 (bx / 2.0, -0.5 * (cx - bx) * bx / cy), C2 = lineInter(hb.first, hb.second, pa.first, pa.second).second;
    cout << abs(A2.cross(B2, C2)) / 2.0 << " ";

    Line lineA = make_pair(Pd(bx, 0), Pd(cx, cy)), lineB = make_pair(Pd(0, 0), Pd(cx, cy)), lineC = make_pair(Pd(0, 0), Pd(bx, 0));

    Pd A3 = reflect(A2, lineB), B3 = reflect(B2, lineC), C3 = reflect(C2, lineA);

    cout << abs(A3.cross(B3, C3)) / 2.0 << endl;
    return 0;
}