import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class PowerCrypt {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		
		while(scan.hasNext())
		{
			n = parse(scan.nextLine());
			BigInteger p = new BigInteger(scan.nextLine());
			
			System.out.println(binSearch(p, n));
		}
	}
	
	public static long binSearch(BigInteger ans, int exp)
	{
		long low = 1, high = (long) 1e9;
		long mid = (low + high) / 2;
		
		while (low < high)
		{
			int compare = ans.compareTo(fastExpo(mid, exp));
			
			if(compare == 0)
			{
				return mid;
			}
			
			else if(compare < 0)
				high = mid - 1;
			
			else
				low = mid + 1;
					
			mid = (low + high) / 2;
		}
		
		if(low != high)
			if(ans.compareTo(fastExpo(low, exp)) == 0) return low;
		return high;
	}

	public static BigInteger fastExpo(long a, int exp) 
	{
		BigInteger ans = new BigInteger("1");
		BigInteger base = BigInteger.valueOf(a);
		
		while(exp > 0)
		{
			if(exp % 2 == 1)
				ans = ans.multiply(base);
			
			base = base.multiply(base);
			exp /= 2;
		}

		return ans;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
