import java.util.Scanner;

public class Factorial {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int num = scan.nextInt();
			long totalFives = 0;
			
			for(int i = 5, count = 1; i <= num; i += 5, count++)
			{
				if(count % 5 == 0)
					totalFives += findFives(i / 25) + 2;
				else
					totalFives++;
				
			}
			
			System.out.println(totalFives);
		}
		
	}
	
	public static long findFives(int num)
	{
		long ans = 0;
		
		while(num % 3125 == 0)
		{
			num /= 3125;
			ans += 5;
		}
		
		while(num % 125 == 0)
		{
			num /= 125;
			ans += 3;
		}
		
		while(num % 5 == 0)
		{
			num /= 5;
			ans++;
		}
		
		return ans;
	}
}
