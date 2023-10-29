import java.util.Scanner;

public class T22_v2 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int size = scan.nextInt();
			int[] ray = new int[size];
			boolean one = false;
			int minPrime = -1;
			
			for(int i = 0; i < size; i++)
			{
				ray[i] = scan.nextInt();
				
				if(ray[i] == 1)
				{
					one = true;
				}
				else if(isPrime(ray[i]))
				{
					if(minPrime == -1)
						minPrime = ray[i];
					else if(minPrime > ray[i])
						minPrime = ray[i];
				}
			}
			
			if(one && minPrime != -1)
				System.out.println(minPrime);
			else
				System.out.println(-1);
		}
	}
	
	public static boolean isPrime(int num)
	{
		
		double max = Math.sqrt(num);
		
		for(int i = 2; i <= max; i++)
		{
			if(num % i == 0)
				return false;
		}
		
		return true;
	}
}
