import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EuclidGame {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		
		while(!(in[0].equals("0") && in[1].equals("0")))
		{
			int a = parse(in[0]);
			int b = parse(in[1]);
			
			if(a < b)
			{
				int temp = a;
				a = b;
				b = temp;
			}
			
			boolean stan = recur(a, b);
			if(stan)
				System.out.println("Stan wins");
			else
				System.out.println("Ollie wins");
			
			in = scan.readLine().split(" ");
		}
	}

	public static boolean recur(long a, long b) 
	{
		if(a < b)
		{
			long temp = b;
			b = a;
			a = temp;
		}
		
		if(a % b == 0 || b == 0) return true;
		
		if(a < b + b)
		{
			return !recur(b, a%b);
		}
		
		else
		{
			return (!recur((a%b) + b, b) || !recur(b, a%b));
			
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
