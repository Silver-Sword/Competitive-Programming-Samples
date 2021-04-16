import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class g {
	static boolean debug = false;
	static char[][] ray;
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0}; // Clockwise, top left start
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1}; 
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		
		int row = parse(in[0]);
		int col = parse(in[1]);
		long k = parse(in[2]);
		
		long xMin = -1, xMax = -1, yMin = -1, yMax = -1;
		char[][] arr = new char[row][col];
		for(int i = 0; i < row; i++)
		{
			arr[i] = scan.readLine().toCharArray();
		}
		
		row += 40;
		col += 40;
		// Simulate up to k floodfills for small cases
		// Also to connect them
		ray = new char[row][col];
		for(int i=0; i<row; i++) Arrays.fill(ray[i], '.');
		for(int r=0; r<row-40; r++) {
			for(int c=0; c<col-40; c++) {
				ray[20+r][20+c] = arr[r][c];
			}
		}
		if(debug) { printArray(ray); System.out.println(); }
		for(int i=0; i<Math.min(20, k); i++) {
			ray = floodfill(ray);
		}
		k -= Math.min(20, k);

		if(debug) printArray(ray);
		
		long ans = 0;
		boolean hash = false;
		if(k != 0) {
			//figure out bounded rectangle
			for(int i = 0; i < row; i++)
			{
				for(int j = 0; j < col;j ++)
				{
					if(ray[i][j] == '#')
					{
						yMax = i;
						hash = true;
						
						if(yMin == -1)
						{
							yMin = i;
						}
						
						if(xMin == -1 || j < xMin)
							xMin = j;
						if(xMax == -1 || j > xMax)
							xMax = j;
					}
				}
			}
			
			if(!hash)
				ans = 0;
			
			else
			{
			
				ans = ( (xMax - xMin + 1) + (2 * k) ) * ( (yMax - yMin + 1) + (2 * k) );
					
				//find outer edges to subtract
				ans -= subtract(k, ray, xMin, xMax, yMin, yMax);
			}

		} else {
			for(int i=0; i<row; i++) {
				for(int j=0; j<col; j++) {
					if(ray[i][j] == '#') {
						ans++;
					}
				}
			}
		}
		
		
		System.out.println(ans);
		
	}

	static void printArray(char[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}

	static boolean inBounds(int r, int c, int n, int m) {
		return (r >= 0 && r < n) && (c>=0 && c<m);
	}

	private static char[][] floodfill(char[][] arr) {  // 20 * (60*60*8)
		int n = arr.length;
		int m = arr[0].length;
		char[][] ret = new char[n][m];
		for(int i=0; i<n; i++) {
			Arrays.fill(ret[i], '.');
		}
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				if(arr[r][c] == '#') {
					ret[r][c] = '#';
					for(int k=0; k<8; k++) {
						int nr = r + dr[k];
						int nc = c + dc[k];
						if(inBounds(nr, nc, n, m)) {
							ret[nr][nc] = '#';
						}
					}
				}
			}
		}
		return ret;
	}
	
	private static long subtract(long k, char[][] ray, long xMin, long xMax, long yMin, long yMax) 
	{
		long ans = 0;
			
		for(int i = (int) yMin; i <= yMax; i++)
		{
			boolean hash = false;
			for(int j = (int) xMin; j <= xMax; j++) {
				if(ray[i][j] == '.')
					ans ++;
				else
				{
					hash = true;
					break;
				}
			}
			
			if(!hash)
			{
				ans += 1;
			}
			
			else
			{
				for(int j = (int) xMax; j >= xMin; j--) {
					if(ray[i][j] == '.')
						ans ++;
					else
						break;
				}
			}
		}
		return ans;
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}

/* 

5 5 3
.....
.###.
.#.#.
.###.
.....

81


3 3 1
#..
.#.
..#

19


4 6 3
..##..
.#..#.
.#..#.
..##..

96



3 3 1
#..
...
..#

17


1 1 1000000
#

4000004000001


5 5 2
.#...
.....
.....
.....
...#.

47



3 3 2
#..
...
..#

41


3 3 0
..#
...
#..

2


20 20 10
####################
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
#..................#
####################

1600?


20 20 2
####################
#...................
#...................
#...................
#...................
#...................
#...................
#...................
#...................
#...................
#...................
#...................
#...................
#...................
#...................
#...................
#...................
#...................
#...................
####################


*/
