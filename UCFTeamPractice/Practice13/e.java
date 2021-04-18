import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class e {
	static int n;
	static long MOD = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		n = parse(scan.readLine()) + 1;
		in = scan.readLine().split(" ");
		
		int[] ray = new int[n];
		for(int i = 0; i < n; i++)
			ray[i] = parse(in[i]);
		
		// index, starts or ends on the prev value
		long[][] dp = new long[n+1][n+1];
		
		out.println(solve(1, ray[0], ray, dp));
		out.flush();
	}

	private static long solve(int index, int prevPoint, int[] ray, long[][] dp) 
	{
		if(index >= n)
			return 1;
		
		if(dp[index][prevPoint] != 0)
			return dp[index][prevPoint];
		
		// take end
		if(ray[index] < Math.max(prevPoint, ray[index-1]))
			dp[index][prevPoint] = solve(index + 1, Math.max(ray[index-1], prevPoint), ray, dp);
		
		// take start
		if(ray[index] > Math.min(prevPoint, ray[index-1]))
			dp[index][prevPoint] += solve(index + 1, Math.min(ray[index-1], prevPoint), ray, dp);
		
		
		return dp[index][prevPoint] % MOD;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
