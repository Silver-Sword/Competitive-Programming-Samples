import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class applesack {
	static int n, k;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		k = parse(in[1]);
		
		int count = 1;
		
		while(n > 0)
		{
			n -= (n / k) + (n % k == 0 ? 0 : 1);
			count++;
		}
		
		System.out.println(count);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
