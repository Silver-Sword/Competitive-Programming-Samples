import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BigMod {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		
		while(scan.hasNext())
		{
			long base = scan.nextLong();
			long exp = scan.nextLong();
			long mod = scan.nextLong();
			scan.nextLine();
			
			
			long output = fastExpo(base, exp, mod);
			
			System.out.println(output);
			
			if(scan.hasNext()) scan.nextLine();
		}
	}
	
	public static long fastExpo(long base, long exp, long mod)
	{
		long result = 1;
		
		while(exp > 0)
		{
			if(exp % 2 == 1)
				result = (result * base) % mod;
			
			exp /= 2;
			base = (base * base) % mod;
		}
		
		return result;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
