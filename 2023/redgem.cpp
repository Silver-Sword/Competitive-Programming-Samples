#include <bits/stdc++.h>
using namespace std;

#define sz(x) (int)(x).size()
#define nl '\n'
#define all(x) begin(x), end(x)

const int DEBUG = false;
const double EPS = 1e-8;

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
vector<pair<P, P>> tangents(P c1, double r1, P c2, double r2) {
        P d = c2 - c1;
        double dr = r1 - r2, d2 = d.dist2(), h2 = d2 - dr * dr;
        if (d2 == 0 || h2 < 0)  return {};
        vector<pair<P, P>> out;
        for (double sign : {-1, 1}) {
                P v = (d * dr + d.perp() * sqrt(h2) * sign) / d2;
                out.push_back({c1 + v * r1, c2 + v * r2});
        }
        if (h2 == 0) out.pop_back();
        return out;
}

template<class P>
vector<P> circleLine(P c, double r, P a, P b) {
        P ab = b - a, p = a + ab * (c-a).dot(ab) / ab.dist2();
        double s = a.cross(b, c), h2 = r*r - s*s / ab.dist2();
        if (h2 < 0) return {};
        if (h2 == 0) return {p};
        P h = ab.unit() * sqrt(h2);
        return {p - h, p + h};
}

int n, p, x, y, r;
typedef Point<long double> P;
P unit (0, 0);
const long double PI = acos(-1);
void solve()
{
    // take in input
    P red (x, y);
    vector<pair<int, P>> orange;
    int xo, yo, ro;
    for(int i = 0; i < n; i++)
    {
        cin >> xo >> yo >> ro;
        orange.push_back({ro, P (xo, yo)});
    }
    if(DEBUG) cout << "Red Gem is " << red << ", r=" << r << nl;

    auto getCirclePoint = [&] (P e, P s) // note the backwards s and e
    {
        if(DEBUG && circleLine(unit, p, s, e).empty())
        {
            cout << "GOT EMPTY: " << "[Circle: " << unit << ", r=" << p << "], [Line: s=" << s << ", e=" << e<< "]" << endl;
            assert(false);
        }
        return circleLine(unit, p, s, e)[0];
    };

    deque<pair<long double, int>> q;
    // test all possible tangents
    for(int i = 0; i < n; i++)
    {
        vector<pair<P, P>> tang;
        double rd = r+orange[i].first;

        if(((red-orange[i].second).dist2() - rd*rd) <= EPS) // check tangent case
        {
            if(DEBUG) cout << "Tangent Circles! Oh no!" << nl;

            pair<P, P> tmp = tangents(red, r, orange[i].second, -orange[i].first)[0];
            P a = tmp.first, b;
            P slope = red-orange[i].second;

            if(DEBUG)
            {
                cout << "\tslope=" << slope << nl;
            }

            if(fabs(slope.x) <= EPS)
                b = P(a.x-slope.y, a.y);
            else if(fabs(slope.y) <= EPS)
                b = P(a.x, a.y-slope.x);
            else
                b = P(a.x + 1, a.y + -(slope.x / slope.y));

            // touching, line is perp to 2 centers
            tang = {{a, b}, {b, a}};
        }

        else
            tang = tangents(red, -r, orange[i].second, orange[i].first);

        if(DEBUG)
        {
            cout << "\tTangents to " << orange[i].second << ", r=" << orange[i].first << nl;
            for(int j = 0; j < sz(tang); j++)
            {
                cout << "\t\t" << tang[j].first << ", " << tang[j].second << "| inter: " << getCirclePoint(tang[j].first, tang[j].second) << ", theta=" << getCirclePoint(tang[j].first, tang[j].second).angle() << nl;
            }
        }

        P a = getCirclePoint(tang[0].first, tang[0].second);
        P b = getCirclePoint(tang[1].first, tang[1].second);

        double aTheta = a.angle(), bTheta = b.angle();

        if(aTheta > bTheta) bTheta += 2 * PI;
        else if(aTheta < 0 && bTheta < 0)
        {
            aTheta += 2 * PI;
            bTheta += 2 * PI;
        }
        // if(bTheta < 0) bTheta += 2 * PI;

        q.push_back({aTheta, -1});
        q.push_back({bTheta , 1});
    }

    // sort(all(q), compare);
    sort(all(q));
    if(DEBUG)
    {
        cout << "Angles: " << nl;
        for(int i = 0; i < sz(q); i++)
        {
            // cout << "\t" << q[i].second << "|" << q[i].first << "|" << q[i].first.angle() << nl;
            cout << "\t" << q[i].second << "|" << q[i].first << nl;
        }
    }

    long double e = q[0].first + 2 * PI;
    long double total = 0;
    long double cur = q[0].first;
    int count = 0;

    if(DEBUG) cout << "e=" << e << endl;

    // go to next one
    for(int i = 0; i < sz(q); i++)
    {
        if(q[i].first > e) break;
        if(count == 0) total += q[i].first - cur;
        cur = q[i].first;
        count += q[i].second;
        assert(count <= 0);
        if(DEBUG) cout << "\t\tafter angle " << q[i].first << ", total=" << total << ", count=" << count << endl;
    }

    // go to e
    if(cur < e && count >= 0)
    {
        total += e - cur;
    }
    if(DEBUG)
    {
        cout << "total=" << total << nl;
        cout << "PI=" << PI << nl;
        cout << total / PI << nl;
    }
    cout << fixed << setprecision(4) << (total / (2 * PI)) << nl;
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);


    while(cin >> n >> p >> x >> y >> r, n)
        solve();
    return 0;
}