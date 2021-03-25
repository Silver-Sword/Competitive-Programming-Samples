import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class K_bruteforce 
{
	static boolean debug = true;
	static boolean tooManyWords = false;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		// only works for values that can fit in longs
		// use this to find a pattern, not solve
		long a = Long.parseLong(scan.readLine());
		long b = Long.parseLong(scan.readLine());
		
		long result = Math.min(solve(a, b, out), solve(b, a, out));
		
		if(debug)
			out.print("Answer is ");

		out.println(result);

		out.flush();
	}

	private static long solve(long a, long b, PrintWriter out) 
	{
		if(debug) out.println("Adding to " + a + " and subtracting from " + b);
		long count = 0;
		
		while(b > 0 && hasCarry(a, b))
		{
			if(tooManyWords && debug) out.println("\t\tOperation " + count + ": " + a + " + " + b + " requires carry");
			a ++;
			b --;
			count ++;
		}
		
		if(debug) out.println("\t" + a + " + " + b + " takes " + count + " operation(s)");
		return count;
	}

	private static boolean hasCarry(long a, long b) 
	{
		while(a > 0 && b > 0)
		{
			if((a % 10) + (b % 10) >= 10)
				return true;
			
			a /= 10;
			b /= 10;
		}
		
		return false;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
