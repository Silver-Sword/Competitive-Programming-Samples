import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class toys {
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		int n = parse(in[0]);
		int k = parse(in[1]);
		
		System.out.println(solve(n, k));
	}
	
	public static int solve(int n, int k)
	{
		int ans = 0;
		
		for(int i = 2; i <= n; i++)
		{
			ans = (ans + k) % i;
		}
		
		return ans;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
