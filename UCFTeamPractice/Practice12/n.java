import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class n {
	static int n;
	static long MOD = 1000000;
	static long[] ans = new long[1001];
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		n = parse(scan.readLine());
		
		while(n != 0)
		{
			solve(out);
			n = parse(scan.readLine());
		}
		
		out.flush();
	}
	
	public static void solve(PrintWriter out)
	{
		long[][] dp = new long[n+1][n+1];
		boolean[][] seen = new boolean[n+1][n+1];
		long sum = 0;
		for(int i = 0; i < n; i++)
			sum = (sum + dp(i, 0, dp, seen)) % MOD;
			
		out.println(sum);
	}
	
	private static long dp(int smallerThanSmallest, int index, long[][] dp, boolean[][] seen) 
	{
		if(index >= n - 1)
			return 1;
		
		if(seen[smallerThanSmallest][index])
			return dp[smallerThanSmallest][index];
		
		for(int i = 0; i < smallerThanSmallest; i++)
		{
			dp[smallerThanSmallest][index] += dp(i, index + 1, dp, seen);
		}
		
		// choosing larger number
		if(smallerThanSmallest < n - index - 1)
		{
			dp[smallerThanSmallest][index] += dp(smallerThanSmallest, index + 1, dp, seen);
		}
	
		dp[smallerThanSmallest][index] %= MOD;
		seen[smallerThanSmallest][index] = true;
		return dp[smallerThanSmallest][index];
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
