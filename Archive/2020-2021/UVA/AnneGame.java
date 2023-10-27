import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnneGame {
	static long mod = 2000000011;
	static long[] ans;
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		long tt = parse(scan.readLine());
		
		ans = new long[101];
		ans[1] = 1;
		ans[2] = 1;
		ans[3] = 3;
		
		for(long t = 0; t < tt; t++)
		{
			n = parse(scan.readLine());
			
			System.out.println("Case #" + (t+1) + ": " + recur(n));			
		}
	}

	public static long recur(int i) 
	{
		if(ans[i] != 0)
			return ans[i];
		
		long res = (recur(i-1) * i + i) % mod;
		
		
		if(i % 2 == 0)
		{
			long temp = 1;
			for(int j = i; j > 2; j--)
				temp = (temp * j) % mod;
			res += temp;
		}
		
		ans[i] = (res%mod);
		return ans[i];
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
