import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class e {
	static int n;
	static long runTime, printf;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		runTime = parse(in[1]);
		printf = parse(in[2]);
		
		long[] dp = new long[n + 1];
		long ans = solve(n, dp);
		out.println(ans);
		
		out.flush();
	}

	private static long solve(int left, long[] dp) 
	{
		if(left <= 1)
			return 0;
		
		if(dp[left] != 0)
			return dp[left];
		
		long min = Long.MAX_VALUE;
		
		for(long p = 1; p < left; p++)
		{
			min = Math.min(min, solve((int) (left / (p+1) + (left % (p+1) == 0 ? 0 : 1)), dp) + (p * printf) + runTime); 
		}
		
		dp[left] = min;
		return min;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
