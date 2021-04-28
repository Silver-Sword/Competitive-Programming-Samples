import java.io.IOException;
import java.util.Scanner;

public class Lottery {
	static int n;
	static long count = 0;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		
		while(scan.hasNext())
		{
			String[] in = scan.nextLine().split(" ");
			
			long n = Long.parseLong(in[0]);
			int m = Integer.parseInt(in[1]);
			
			long[] num = new long[m];
			in = scan.nextLine().split(" ");
			for(int i = 0; i < m; i++)
				num[i] = Long.parseLong(in[i]);
			
			//go through
			//bit counts >> odd is +, even is -
			//inclusion exclusion
			//make sure to break when the number gets too big
			/*long max = 1 << m;
			long count = 0;
			long temp;
			for(int i = 1; i < max; i++)
			{
				temp = Integer.bitCount(i) % 2 == 0 ? -1 : 1;
				count += (temp * getVal(i, num, n));
			}*/
			count = 0;
			recur(0, num, m, n, 0, 0);
			
			System.out.println((n-count));
		}
		
		scan.close();
	}
	
	public static void recur(int index, long[] num, long n, long max, int counter, long lcm)
	{
		if(index >= n)
		{
			if(counter == 0) return;
			
			count += (counter % 2 == 1 ? 1 : -1) * (max / lcm);
			
			return;
		}
		
		recur(index+1, num, n, max, counter, lcm);
		
		lcm = lcm == 0 ? num[index] : lcm(lcm, num[index]);
		
		if(lcm > max) return;
		recur(index+1, num, n, max, counter+1, lcm);
		
		return;
	}
	
	public static long getVal(int i, long[] num, long max) 
	{
		long lcm = 0;
		int index;
		while(i > 0)
		{
			index = (Integer.numberOfTrailingZeros(i)) + 1;
			i ^= (i & -i);
			lcm = lcm == 0 ? num[index] : lcm(lcm, num[index]);
			
			if(lcm > max)
				return 0;
		}
		
		return max / lcm;
		
		
	}

	public static long lcm(long a, long b)
	{
		return (a * b) / gcd(Math.max(a, b), Math.min(a, b));
	}
	
	public static long gcd(long a, long b)
	{
		if(b == 0 || a == b) return a;
		return gcd(b, a%b);
	}
}
