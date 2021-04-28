import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Combinations {
	static long n, k;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		
		n = scan.nextInt();
		k = scan.nextInt();
		
		
		while(n != 0 && k != 0)
		{
			//test 100 5
			long ans = choose(n, k);
			
			System.out.printf("%d things taken %d at a time is %d exactly.\n", n, k, ans);
			
			n = scan.nextLong();
			k = scan.nextLong();
		}
		
		scan.close();
		
	}

	public static long choose(long n, long k) 
	{
		if(n == k) return 1;
		k = Math.min(n-k, k);
		
		if(k == 1) return n;
		
		BigInteger ans = BigInteger.valueOf(n).multiply(BigInteger.valueOf(n-1));
		int j = 2;
		for(BigInteger i = BigInteger.valueOf(n-2); j < k; i = i.subtract(BigInteger.ONE), j++)
			ans = (ans.multiply(i)).divide(BigInteger.valueOf(j));
		
		ans = (ans.divide(BigInteger.valueOf(k)));
		
		return ans.longValue();
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
