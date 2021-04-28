import java.io.IOException;
import java.util.Scanner;

public class SumPoly {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		
		while(scan.hasNext())
		{
			long ans = 0;
			
			long n = scan.nextLong();
			
			while(n > 0)
			{
				ans += (n * n * n);
				n--;
			}
			
			System.out.println(ans);
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}

}
