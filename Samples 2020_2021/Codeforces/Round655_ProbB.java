import java.util.Scanner;
import java.util.TreeSet;

public class Round655_ProbB {
	
	static TreeSet<Integer> primes = new TreeSet<Integer>();
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		primes.add(2);
		primes.add(3);
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			
			if(n % 2 == 0)
			{
				System.out.println(n / 2 + " " + (n / 2));
				continue;
			}
			
			else if(n % 3 == 0)
			{
				System.out.println((n /  3) + " " + ((n*2) / 3));
				continue;
			}
			
			while(primes.last() < n)
			{
				nextPrime();
			}
			
			int c = ans(n);
			
			System.out.println((n / c) + " " + ((n * (c - 1)) / c));
		}

	}
	
	public static int ans(int num)
	{
		
		if(primes.contains(num))
			return num; 
		
		for(int i : primes)
		{
			if(num % i == 0)
				return i;
		}
		
		return -1;
	}
	
	public static void nextPrime()
	{
		int i = primes.last() + 2;
		
		while(!isPrime(i))
			i += 2;
		
		primes.add(i);
			
	}
	
	public static boolean isPrime(int num)
	{
		for(int i : primes)
		{
			if(num % i == 0)
				return false;
		}
		
		return true;
	}

}

//test 10^9