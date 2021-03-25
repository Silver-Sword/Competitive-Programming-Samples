import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class cake {

	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			n = Integer.parseInt(scan.readLine());
			
			System.out.println(minPerm(n));
		}
	}
	
	public static int minPerm(int n)
	{
		double max = Math.sqrt(n);
		
		for(int i = (int) max; i >= 1; i--)
		{
			if(n % i == 0)
			{
				return 2 * i + (2 * (n / i));
			}
		}
		
		return n + n + 2;
	}
}


