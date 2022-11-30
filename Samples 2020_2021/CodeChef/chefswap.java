import java.util.Scanner;

public class chefswap {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			long n = scan.nextLong();
			
			long sum = (n * (n+1)) / 2;
			
			if(sum % 2 == 1)
				System.out.println("0");
			else if(n == 3)
				System.out.println("2");
			else
			{
				System.out.println(find(n, sum));
			}
		}
		
		scan.close();
	}

	public static long find(long n, long sum) {
		long midpoint = split(n, sum);
		long net = (sum - 2 * ((midpoint * (midpoint+1)) / 2)) / 2;
		
		return Math.min(net, Math.min(n - midpoint, midpoint));
	}

	public static long split(long n, long sum) {
		long target = sum / 2;
		
		long low = 1, high = n;
		
		long mid = (low + high) / 2;
		
		while(low + 1 < high)
		{
			if((mid * (mid+1)) / 2 < target)
			{
				low = mid;
			}
			
			else
			{
				high = mid - 1;
			}
			
			mid = (low+high) / 2;
		}
		
		if((high * (high+1)) / 2 < target)
		{
			return high;
		}
		
		else
		{
			return low;
		}
	}
}
