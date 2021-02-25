import java.util.Arrays;
import java.util.Scanner;

public class MartianGame {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int teams = scan.nextInt();
		long[] strength = new long[teams];
		
		for(int i  = 0; i < teams; i++)
			strength[i] = scan.nextLong();
		
		Arrays.sort(strength);
		long total = 0;
		long current;
		long temp = 0;
		for(int first = 0; first < teams - 1; first++)
		{
			
			current = strength[first];
			
			if(first != 0 && current == strength[first - 1])
			{
				total += temp;
			}
			
			else {
				temp = 0;
				for(int second = first + 1; second < teams; second++)
				{
					temp += Math.abs(current - strength[second]);
				}
				
				total += temp;
			}
		}
		
		System.out.println(total);
	}
}
