import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class e {
	static int n;
	static int MAX_PRIME = (int) 1e7;
	static boolean[] primes = new boolean[MAX_PRIME];
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		Arrays.fill(primes, true);
		sieve();
		String[] in = scan.readLine().split(" ");
		
		System.out.println(solve(parse(in[0]), parse(in[1]), parse(in[2]), parse(in[3])));
	}

	public static long solve(int a, int b, int c, int d) 
	{
		long sum = 0;
		int[] factors = new int[(int)1e6];
		// looping through [a, b] and using inclusion/exclusion on [c, d]
		
		fillArray(Math.max(b, d), factors);
		sum = ( ( d - c + 1) * (b - a + 1) ) - getSumInRange(b, factors, c, d, a, b);
		
		return sum;
	}

	private static void fillArray(int max, int[] factors) 
	{
		int i = 3;
		int index = 1;
		factors[0] = 2;
		
		while(i <= max)
		{
			if(primes[i])
			{
				factors[index] = i;
				index++;
			}
			i+=2;
		}
		return;
	}

	private static long getSumInRange(int num, int[] factors, int min, int max, int countMin, int countMax) 
	{
		if(min == 1)
			return count(0, max, factors, 0, 1, countMin, countMax);
		
		return count(0, max, factors, 0, 1, countMin, countMax) - count(0, min - 1, factors, 0, 1, countMin, countMax);
	}
	
	public static long count(int index, int num, int[] factors, int count, long product, int min, int max) 
	{
		if(product > max) return 0;
		// complete
		if(factors[index] == 0)
		{
			if(count == 0) return 0;
			
			long result = factorsInRange(product, min, max);
			if(count % 2 == 1)
				result *= ( num / product );
					
			else
				result *= -1 * (num / product);
			
			return result;
		}
				
		return count(index + 1, num, factors, count, product, min, max) + count(index + 1, num, factors, count + 1, product * factors[index], min, max);
	}
	
	private static long factorsInRange(long product, int min, int max) {
		if(min == 1)
			return max / product;
		return ( max / product ) - ( (min - 1)  / product );
	}

	public static void sieve()
	{
		for(int i = 2; i * i < MAX_PRIME; i++)
		{
			if(primes[i])
				for(int j = i * i; j < MAX_PRIME; j+= i)
					primes[j] = false;
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
