import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class spaced 
{
	static int n;
	public static void main(String[] args) throws IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		String[] in;
		n = Integer.parseInt(scan.readLine());
		
		int[][] grid = new int[n][n];
		for(int i = 0; i < n; i++)
		{
			in = scan.readLine().split(" ");
			
			for(int j = 0; j < n; j++)
			{
				grid[i][j] = Integer.parseInt(in[j]);
			}
		}
		
		long max = alternateMax(grid);
		
		long[][] dp = new long[n][4];
		init(dp);
		
		long ans = solve(0, -1, compressGrid(grid), dp);
		max = Math.max(max, ans);
		out.println(max);
		out.flush();
	}
	
	

	private static long solve(int index, int prev, long[][] grid, long[][] dp) 
	{
		if(index >= n)
			return 0;
		
		// try all combos
		if(prev == -1)
		{
			long max = 0;
			
			max = Math.max( solve(index + 1, 0, grid, dp) , solve(index + 1, 3, grid, dp) + grid[0][index] + grid[1][index] );
			max = Math.max( max , Math.max( solve(index + 1, 1, grid, dp) + grid[0][index] , solve(index + 1, 2, grid, dp) + grid[1][index]) );
			
			return max;
		}
		
		if(dp[index][prev] != -1)
			return dp[index][prev];
		
		// 0 is . and . || 3 is C and C
		if(prev == 0 || prev == 3)
		{
			dp[index][prev] = solve(index + 1, prev == 0 ? 3 : 0, grid, dp) + (prev == 0 ? grid[0][index] + grid[1][index] : 0);
		}
		
		// 1 is . and C | 2 is C and .
		else
		{
			dp[index][prev] = Math.max( solve(index + 1, 1, grid, dp) + grid[0][index] , solve(index + 1, 2, grid, dp) + grid[1][index] );
		}
		
		return dp[index][prev];
	}

	private static long[][] compressGrid(int[][] grid) 
	{
		long[][] result = new long[2][n];
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				result[i%2][j] += grid[i][j];
			}
		}
		
		return result;
	}

	private static long alternateMax(int[][] grid) 
	{
		long sum = 0;
		
		for(int i = 0; i < n; i++)
		{
			long[] half = {0, 0};
			
			for(int j = 0; j < n; j++)
			{
				half[j%2] += grid[i][j];
			}
			
			sum += Math.max(half[0], half[1]);
		}
		
		return sum;
	}
	
	private static void init(long[][] dp) 
	{
		for(int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], -1);
	}
}
