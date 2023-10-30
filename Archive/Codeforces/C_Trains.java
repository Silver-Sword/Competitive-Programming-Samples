import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C_Trains {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		
		long a = parse(in[0]), b = parse(in[1]);
		
		long lcm = lcm(a, b);
		
		String ans;
		if(Math.abs((lcm / a) - (lcm / b)) == 1)
			ans = "Equal";
		else if(a < b)
			ans = "Dasha";
		else
			ans = "Masha";
		
		System.out.println(ans);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	public static long lcm(long a, long b)
	{
		return (a * b) / gcd(Math.max(a, b), Math.min(a, b));
	}
	
	public static long gcd(long a, long b)
	{
		if(a == b || b == 0) return a;
		return gcd(b, a%b);
	}
}
