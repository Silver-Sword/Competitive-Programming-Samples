import java.util.Scanner;

public class subtraction {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int nums = scan.nextInt();
			int[] ray = new int[nums];
			for(int i = 0; i < nums; i++)
				ray[i] = scan.nextInt();
			
			int gcd = gcd(Math.max(ray[0], ray[1]), Math.min(ray[0], ray[1]));
			int temp; 
			
			for(int outer = 2; outer < nums - 1; outer+= 2)
			{
				int inner = outer + 1;
				temp = gcd(Math.max(ray[inner], ray[outer]), Math.min(ray[inner], ray[outer]));
				
				if(temp == 1)
				{
					gcd = 1; 
					break;
				}
				
				else
				{
					gcd = gcd(Math.max(gcd, temp), Math.min(gcd, temp));
					if(gcd == 1)
						break;
				}
			}
			
			if(nums % 2 != 0 && gcd != 1)
			{
				temp = gcd(Math.max(ray[0], ray[nums - 1]), Math.min(ray[0], ray[nums - 1]));
				if(temp == 1)
					gcd = 1;
				else
					gcd = gcd(Math.max(gcd, temp), Math.min(gcd, temp));
			}
			
			
			System.out.println(gcd);
		}
	}
	
	public static int gcd(int a, int b)
	{
		if(b == 0)
			return a;
		
		return gcd(b, a%b);
	}
}
