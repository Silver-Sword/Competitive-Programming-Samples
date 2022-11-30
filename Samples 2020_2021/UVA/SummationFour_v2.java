import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class SummationFour_v2 {
	static int max = 10000000;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		
		//generate primes
		TreeSet<Integer> primes = new TreeSet<Integer>();
		boolean[] prime = new boolean[max+1];
		Arrays.fill(prime, true);
		prime[0] = false;
		prime[1] = false;
		
		generatePrimes(prime, primes);
		
		//generate sums
		int num;
		while(scan.hasNext())
		{
			num = scan.nextInt();
			
			boolean ans = search(3, num, primes);
			
			if(!ans)
				System.out.println("Impossible.");
			else
				System.out.println();
		}
		
	}
	
	public static boolean search(int count, int num, TreeSet<Integer> primes) 
	{
		if(count == 0)
		{
			if(primes.contains(num))
			{
				System.out.print(num);
				return true;
			}
			else
				return false;
		}
		
		if(num < 2*count)
			return false;
		
		boolean con = true;
		int val = primes.floor(num);
		while(con)
		{
			if(search(count-1, num-val, primes))
			{
				System.out.print(" " + val);
				return true;
			}
			
			if(val == 2)
				return false;
			
			val = primes.floor(val - 1);
		}
		
		return false;
	}

	public static void generatePrimes(boolean[] prime, TreeSet<Integer> primes)
	{
		int p;
		for(p = 2; p * p <= max; p++)
		{
			if(prime[p])
			{
				primes.add(p);
				
				for(int i = p * p; i <= max; i+=p)
					prime[i] = false;
			}
		}
		
		for( ; p <= max; p++)
			if(prime[p])
				primes.add(p);
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}