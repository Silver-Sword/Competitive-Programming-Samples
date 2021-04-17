import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class k {
	static int n;
	static int t;
	static long mod = (long) (1e9 + 7);
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		t = parse(in[1]);
		
		int[][] kebab = new int[n][2];
		long[][][] dp = new long[n][][];
		for(int i = 0; i < n; i++)
		{
			in = scan.readLine().split(" ");
			kebab[i][0] = parse(in[0]);
			kebab[i][1] = parse(in[1]);
			dp[i] = new long[kebab[i][0]][kebab[i][0]];
		}
		
		long ans = go(0, 0, 0, dp, kebab) % mod;
		System.out.println(ans);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	public static long go(int index, int time, int dreams , long[][][] dp, int[][] kebabs)
	{
		if(index >= n)
			return 1;
		
		if(dreams > kebabs[index][0] - kebabs[index][1])
			return 0;
		
		if(time >= dp[index].length)
		{
			return go(index + 1, Math.max( 0 , (time - dp[index].length) ), 0, dp, kebabs);
		}
			
		if(dp[index][time][dreams] != 0)
			return dp[index][time][dreams];
		
		// can take another dream
		if(dreams < kebabs[index][0] - kebabs[index][1])
			dp[index][time][dreams] = go( index, time + t + 1, dreams + 1, dp, kebabs );
		
		//take another ingredient
		dp[index][time][dreams] += go( index, time + 1, dreams, dp, kebabs );
		dp[index][time][dreams] %= mod;
		
		return dp[index][time][dreams];
	}
}
