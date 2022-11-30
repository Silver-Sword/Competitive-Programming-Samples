import java.util.Scanner;

public class OctChal2019 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int days = scan.nextInt();
			
			int min = scan.nextInt();
			int index = 0;
			int min2 = Integer.MAX_VALUE;
			int index2 = -1;
			
			int count = 1;
			
			for(int i = 1; i < days; i++)
			{
				int temp = scan.nextInt();
				
				if(i - index >= 6)
				{
					min = min2;
					index = index2;
					min2 = Integer.MAX_VALUE;
				}
				
				if(temp < min)
				{
					count++;
					min = temp;
					index = i;
					min2 = Integer.MAX_VALUE;
				}
				
				else if(temp <= min2)
				{
					min2 = temp;
					index2 = i;
				}
			}
			
			System.out.println(count);
			
		}
	}
}
