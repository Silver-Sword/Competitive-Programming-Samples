import java.util.Scanner;

public class Lazies {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int deliveries = scan.nextInt();
			int maxCost = scan.nextInt();
			int num = 0;
			
			int[] costs = new int[deliveries];
			for(int i = 0; i < deliveries; i++)
			{
				costs[i] = scan.nextInt();
				if (costs[i] <= maxCost)
					num++;
				
			}
			
			System.out.println(deliveries + " " + num + " " + num);
		}
	}
}
