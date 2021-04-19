import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class lighting {
	static int n, target;
	static long MOD = (long) 1e9 + 7;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		target = parse(in[1]);
		
		int[] ray = new int[n];
		char[] input = scan.readLine().toCharArray();
		for(int i = 0; i < n; i++)
			ray[i] = input[i] - '0';
		
		long[][][] dp = new long[n][n+1][2];
		boolean[][][] calc = new boolean[n][n+1][2];
			
		long ans = dp(n-1, 0, 0, ray, dp, calc);
		
		System.out.println(ans%MOD);
		
	}

	private static long dp(int index, int on, int carry, int[] ray, long[][][] dp, boolean[][][] calc) 
	{
		if(index < 0)
			return on + carry == target ? 1 : 0;
		
		if(Math.abs(on - target) > index + 2)
			return 0;
		
		
		if(calc[index][on][carry])
			return dp[index][on][carry];
		
		int bit = (ray[index] + carry) % 2;
		
		if(bit == 0 && (ray[index] == 1))
		{
			dp[index][on][carry] = (dp(index - 1, on, 1, ray, dp, calc) + dp(index - 1, on + 1, 1, ray, dp, calc)) % MOD;
		}
		
		else if(bit == 1)
		{
			dp[index][on][carry] = (dp(index - 1, on + 1, 0, ray, dp, calc) + dp(index - 1, on, 1, ray, dp, calc)) % MOD;
		}
		
		else
		{
			dp[index][on][carry] = (dp(index - 1, on, 0, ray, dp, calc) + dp(index - 1, on + 1, 0, ray, dp, calc)) % MOD;
		}
		
		calc[index][on][carry] = true;
		return dp[index][on][carry];
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
