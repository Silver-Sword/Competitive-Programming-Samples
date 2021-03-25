import java.util.*;
import java.io.*;

public class k {
  static boolean debug = false;
  public static void main(String[] args) {
    FS in = new FS(System.in);
    PrintWriter out = new PrintWriter(System.out);
    
    int n = 100;
    int[] arr = new int[n]; 
    int[] raw = new int[n];
    
    for(int loop = 0; loop < 100; loop++)
    {
    
    // get random array of size 100
    ArrayList<Integer> list = new ArrayList<Integer>();
    for(int i = 1; i <= 100; i++)
    	list.add(i);
    Random rand = new Random();
    
    int[] track = new int[100];
    for(int i = 0; i < n; i++)
    {
    	track[i] = list.remove(rand.nextInt(list.size()));
    	arr[i] = track[i];
    }
    	
    
    
    for (int i=0; i<n; i++) {
      raw[i] = arr[i];
    }

    // try doing insertion sort. Hopefully under 2n :/
    ArrayList<Integer> lefts = new ArrayList<>();
    ArrayList<Integer> rights = new ArrayList<>();
    while (true) {
      // Find how long is currently sorted
      int furthestAsc = 0;
      for (int i=0; i<n-1; i++) {
        if (arr[i] > arr[i+1]) break; 
        furthestAsc = i+1;
      }
      if (furthestAsc == n-1) break;  // we done!

      int leftMostBigger = 0;
      int rightMostSmaller = -1;
      for (int i=0; i<=furthestAsc; i++) {
        if (arr[i] > arr[furthestAsc+1]) break;
        rightMostSmaller = i;
        leftMostBigger = i+1;
      }
      // if(debug) System.out.printf("LeftmostBigger=%d rightMostSmaller=%d furthAsc=%d\n", leftMostBigger, rightMostSmaller, furthestAsc);

      if (furthestAsc != leftMostBigger) {
        // Print to swap from leftmostBigger to the furthest
        lefts.add(leftMostBigger);
        rights.add(furthestAsc);
        // Then actually swap
        swap(arr, leftMostBigger, furthestAsc);
        if (debug) print(arr);
      }

      int furthestDesc = leftMostBigger;
      for (int i=leftMostBigger; i<n-1; i++) {
        if (arr[i] < arr[i+1]) break; 
        if (rightMostSmaller != -1 && arr[i+1] < arr[rightMostSmaller]) break;
        furthestDesc = i + 1;
      }
      // if (debug) System.out.printf("leftmostBigger=%d furthDes=%d\n", leftMostBigger, furthestDesc);

      lefts.add(leftMostBigger);
      rights.add(furthestDesc); 
      swap(arr, leftMostBigger, furthestDesc);
      if (debug) print(arr);
      // if (lefts.size()==4) break;
    }
    
    for(int i = 0; i < 100; i++)
    	System.out.print(track[i] + " ");
    System.out.println("\nSize of list is " + lefts.size());
    System.out.println(lefts.size() <= 192 ? "Hooray!!! It works!" : "OH NOOOO!!!!");
    
    if(lefts.size() > 191)
    {
    	System.out.println("CHECK THIS!!!");
    	System.exit(0);
    }

    /*System.out.println(lefts.size());
    for (int i=0; i<lefts.size(); i++) {
      int l = lefts.get(i); 
      int r = rights.get(i);
      System.out.printf("%d %d\n", l+1, r+1);
      if(debug) {
        swap(raw, l, r);
        print(raw);
      }
    }*/
    }

  }

  static void swap(int[] arr, int l, int r) {  // r inclusive
    int len = r-l + 1;
    for (int i=0; i<len/2; i++) {
      int temp = arr[l+i];
      arr[l+i] = arr[r-i];
      arr[r-i] = temp;
    }
  }

  static void print(int[] arr) {
    for (int i=0; i<arr.length; i++) {
      System.out.printf("%d ", arr[i]);
    }
    System.out.println();
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

3
3 1 2

2
1 2
2 3


6
4 3 5 2 6 1


10
5 8 2 9 3 1 4 6 7 10

12
1 2
1 3
2 4
2 5
1 5
1 6
4 6
4 7
6 7
6 8
7 8
7 9

12
1 2
8 5 2 9 3 1 4 6 7 10
1 3
2 5 8 9 3 1 4 6 7 10
2 4
2 9 8 5 3 1 4 6 7 10
2 5
2 3 5 8 9 1 4 6 7 10
1 5
9 8 5 3 2 1 4 6 7 10
1 6
1 2 3 5 8 9 4 6 7 10
4 6
1 2 3 9 8 5 4 6 7 10
4 7
1 2 3 4 5 8 9 6 7 10
6 7
1 2 3 4 5 9 8 6 7 10
6 8
1 2 3 4 5 6 8 9 7 10
7 8
1 2 3 4 5 6 9 8 7 10
7 9
1 2 3 4 5 6 7 8 9 10


*/