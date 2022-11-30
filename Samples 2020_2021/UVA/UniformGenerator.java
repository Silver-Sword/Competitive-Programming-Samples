import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UniformGenerator {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		int a, b;
		while(scan.hasNext())
		{
			a = scan.nextInt();
			b = scan.nextInt();
			
			String ans;
			if(gcd(Math.max(a, b), Math.min(a, b)) == 1)
				ans = "Good Choice";
			else
				ans = "Bad Choice";
			
			System.out.printf("%10d%10d    %-10s\n\n", a, b, ans);
		}
	}
	
	public static int gcd(int a, int b)
	{
		if(a == b || b == 0)
			return a;
		
		return gcd(b, a%b);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
