import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class SummationFour {
	static int max = 10000000;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		
		//generate primes
		ArrayList<Integer> primes = new ArrayList<Integer>();
		boolean[] prime = new boolean[max+1];
		Arrays.fill(prime, true);
		prime[0] = false;
		prime[1] = false;
		
		generatePrimes(prime, primes);
		
		//generate sums
		int size = primes.size();
		int val = 0;
		TreeMap<Integer, String> sums = new TreeMap<Integer, String>();
		int a;
		for(int i = 0; i < size; i++)
		{
			a = primes.get(i);
			for(int j = i; j < size; j++)
			{
				val = a + primes.get(j);
				
				if(val > max)
					break;
				
				sums.put(val, a + " " + primes.get(j));
			}
			
		}
		
		int num;
		while(scan.hasNext())
		{
			num = scan.nextInt();
			
			boolean ans = search(num, sums);
			
			if(!ans)
				System.out.println("Impossible");
		}
		
	}
	
	public static boolean search(int num, TreeMap<Integer, String> sums)
	{
		for(Entry<Integer, String> e : sums.entrySet())
		{
			if(e.getKey() > num - 2)
				break;
			
			if(sums.containsKey(num - e.getKey()))
			{
				System.out.println(e.getValue() + " " + sums.get(num - e.getKey()));
				return true;
			}
		}
		
		return false;
	}

	public static void generatePrimes(boolean[] prime, ArrayList<Integer> primes)
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
