import java.util.Scanner;
import java.lang.Math;

public class T22 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		int prime = -1;
		
		for(int k = 0; k < cases; k++)
		{
			prime = -1;
			int numNums = scan.nextInt();
			int[] ray = new int[numNums];
			for(int i = 0; i < numNums; i++)
				ray[i] = scan.nextInt();
			
			for(int first = 0, num1 = ray[first]; first < ray.length - 1; first++, num1 = ray[first])
			{
				for(int second = first + 1, num2 = ray[second]; second < ray.length; second++)
				{
					num2 = ray[second];
					if(isPrime(num1 * num2))
					{
						if(prime == -1)
							prime = num1 * num2;
						else
							prime = prime > num1 * num2 ? num1 * num2 : prime;
					}
				}
			}
			
			System.out.println(prime);
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
