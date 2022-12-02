import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FlyingSaucer {
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().trim().split(" ");
		
		long n = Long.parseLong(in[0]), m = Long.parseLong(in[1]);
		
		long ans = getAns(n, m);
		System.out.println(ans);
	}
	
	public static long getAns(long n, long mod)
	{
		long ans = 2;
		
		for(int i = 1; i < n; i++)
		{
			ans = (2 + ans * 3) % mod;
		}
		
		return ans;
	}
}
