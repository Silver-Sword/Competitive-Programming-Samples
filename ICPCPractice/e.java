import java.util.*;
import java.io.*;

public class e {
  public static void main(String[] args) {
    FS in = new FS(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int n = in.nextInt();

    // Going to remove duplicate points
    ArrayList<VecL> pointsal = new ArrayList<>();
    HashSet<VecL> seen = new HashSet<>();
    for (int i=0; i<n; i++) {
      VecL point = new VecL(in.nextInt(), in.nextInt());
      if (seen.add(point))
        pointsal.add(point);
    }

    n = pointsal.size();
    VecL[] points = new VecL[n];
    for (int i=0; i<n; i++)
      points[i] = pointsal.get(i);
    Arrays.sort(points);

    points = getHull(points);
    n = points.length;

    // http://isolvedaproblem.blogspot.com/2011/08/maximum-triangle-area-part-3-and-final.html
    double ans = 0;
    if (n >= 3) {
      int i = 0;
      int j = (i + 1) % n;
      int k = (j + 1) % n;
      // find best i
      while (true) {
        double area = getTriangleArea(points[i], points[j], points[k]);
        HashSet<Integer> js = new HashSet<>();
        // Find best j
        while (true) {
          HashSet<Integer> ks = new HashSet<>();
          // Find best k
          while (true) {
            int nk = k%n;
            if (ks.contains(nk)) break;  // In case all colinear
            ks.add(nk);
            double narea = getTriangleArea(points[i], points[j], points[nk]);
            if (narea >= area) {
              k = nk;
              area = narea;
            }

            else {
              break;
            }
          }

          int nj = j%n;
          if (js.contains(nj)) break;  // In case all colinear
          js.add(nj);
          double narea = getTriangleArea(points[i], points[nj], points[k]);
          if (narea >= area) {
            j = nj;
            area = narea;
          } else {
            break;
          }
        }
        if (area > ans) ans = area;
        i++;
        if (i == j) j = (j+1)%n;
        if (j == k) k = (k+1)%n;
        if (i == n) break;
      }
      System.out.printf("%.5f\n", ans);

    } else {
      System.out.println("0.000000");
    }

  }

  static double getTriangleArea(VecL a, VecL b, VecL c) {
  // static double getArea(Vec[] pts) {
    // double area = 0;
    // for(int i=0; i<pts.length; i++) {
    //   area += pts[i].cross(pts[(i+1)%pts.length]);
    // }
    double area = 0;
    area += a.cross(b);
    area += b.cross(c);
    area += c.cross(a);
    return Math.abs(area/2);
  }
  static VecL[] upperHull, lowerHull;

  public static VecL[] removeDupes(VecL[] points) {
    HashSet<VecL> set=new HashSet<>();
    for (VecL v:points)
      set.add(v);
    int counter=0;
    points=new VecL[set.size()];
    for (VecL v:set) points[counter++]=v;
    return points;
  }

  // returns the hull in CCW order
  public static VecL[] getHull(VecL[] points) {
    points=points.clone();
    Arrays.sort(points);
    if (points.length<3)
      return upperHull=lowerHull=points;
    int n=points.length, j=2, k=2;
    VecL[] lo=new VecL[n], up=new VecL[n];
    lo[0]=points[0];
    lo[1]=points[1];
    for (int i=2; i<n; i++) {
      VecL p=points[i];
      while (j>1&&!right(lo[j-2], lo[j-1], p)) j--;
      lo[j++]=p;
    }
    up[0]=points[n-1];
    up[1]=points[n-2];
    for (int i=n-3; i>=0; i--) {
      VecL p=points[i];
      while (k>1&&!right(up[k-2], up[k-1], p)) k--;
      up[k++]=p;
    }

    VecL[] res=new VecL[j+k-2];
    for (int i=0; i<k; i++) res[i]=up[i];
    for (int i=1; i<j-1; i++) res[k+i-1]=lo[i];

    // if you need upper and lower hulls
    upperHull=new VecL[k];
    for (int i=0; i<k; i++) upperHull[i]=up[k-i-1];
    lowerHull=new VecL[j];
    for (int i=0; i<j; i++) lowerHull[i]=lo[i];

    return res;
  }

  // check if a->b->c are in the right order
  static boolean right(VecL a, VecL b, VecL c) {
    return b.sub(a).cross(c.sub(a))>0;
  }

  static boolean left(VecL a, VecL b, VecL c) {
    return b.sub(a).cross(c.sub(a))<0;
  }

  static class VecL implements Comparable<VecL> {
    long x, y;
    public VecL(long x, long y) {this.x=x;this.y=y;}
    public VecL add(VecL o) {return new VecL(x+o.x, y+o.y);}
    public VecL sub(VecL o) {return new VecL(x-o.x, y-o.y);}
    public VecL scale(long s) {return new VecL(x*s, y*s);}
    public long dot(VecL o) {return x*o.x+y*o.y;}
    public long cross(VecL o) {return x*o.y-y*o.x;}
    public long mag2() {return dot(this);}
    public VecL rot90() {return new VecL(-y, x);}
    public VecL rot270() {return new VecL(y, -x);}
    public String toString() {return "("+x+", "+y+")";}
    public int hashCode() {return Long.hashCode(x<<13^(y*57));}

    public boolean equals(Object oo) {
      VecL o=(VecL)oo;
      return x==o.x&&y==o.y;
    }

    public int compareTo(VecL o) {
      if (x!=o.x) return Long.compare(x, o.x);
      return Long.compare(y, o.y);
    }

    //origin->q1, axes-> quadrant in ccw direction
    public int quadrant() {
      if (x==0||y==0)
        if (y==0) return x>=0 ? 1 : 3;
        else return y>=0 ? 2 : 4;
      if (x>0) return y>0 ? 1 : 4;
      else return y>0 ? 2 : 3;
    }

    public int radialCompare(VecL o) {
      if (quadrant()==o.quadrant())
        return -Long.signum(cross(o));
      return Integer.compare(quadrant(), o.quadrant());
    }
  }

  static class FS {
    BufferedReader in;
    StringTokenizer token;
    public FS(InputStream stream) {
      in = new BufferedReader(new InputStreamReader(stream));
    }
    public String next() {
      if(token == null || !token.hasMoreElements()) {
        try{
          token = new StringTokenizer(in.readLine());
        } catch (Exception e) {}
        return next();
      }
      return token.nextToken();
    }
    public int nextInt() {
      return Integer.parseInt(next());
    }
  }
}

/*

7
0 0
0 5
7 7
0 10
0 0
20 0
10 10

100.000000

3
1 1
2 2
3 3

0


3
0 0
0 0
0 0

0


3
0 0
40000000 0
40000000 1

20000000.00000


3
0 0
40000001 0
40000000 1



*/

