import java.util.Arrays;
import java.util.Scanner;

public class SmartPhone {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int total = scan.nextInt();
		
		long[] budget = new long[total];
		for(int i = 0; i < total; i++)
			budget[i] = scan.nextLong();
		
		Arrays.sort(budget);
		
		long maxSpending = -1;
		long current = 0;
		
		for(int lowest = 0; lowest < total; lowest++)
		{
			current = budget[lowest] * (total - lowest);
			if(current > maxSpending)
				maxSpending = current;
		}
		
		System.out.println(maxSpending);
	}
}
