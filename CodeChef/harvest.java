import java.util.Scanner;

public class harvest {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541};
		
		for(int c = 0; c < cases; c++)
		{
			int x = scan.nextInt();
			int y = scan.nextInt();
			
			if(x + y + 1 > primes[primes.length - 1])
			{
				int num = x + y + 1;
				if(num % 2 == 0)
					num++;
				
				while(!isPrime(num))
					num += 2;
				
				System.out.println(num - x - y);
			}
			else
			{
				int index = 0;
				while(x + y + 1 > primes[index + 1])
					index++;
				
				System.out.println(primes[index + 1] - x - y);
			}
		}
	}
	
	public static boolean isPrime(int num)
	{
		double max = Math.sqrt(num);
		
		if(num == 2)
			return true;
		if(num % 2 == 0 || num == 1)
			return false;
		
		for(int i = 3; i <= max; i++)
			if(num % i == 0)
				return false;
		return true;
	}
}
