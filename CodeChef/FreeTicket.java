import java.util.Scanner;

public class FreeTicket {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cities = scan.nextInt();
		int pairs = scan.nextInt();
		
		int[] city1 = new int[pairs];
		int[] city2 = new int[pairs];
		int[] cost = new int[pairs];
		for(int i = 0; i < pairs; i++)
		{
			city1[i] = scan.nextInt();
			city2[i] = scan.nextInt();
			cost[i] = scan.nextInt();
		}
	}
}
