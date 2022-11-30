import java.util.Scanner;

public class city {
	static int n;
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		String[] in;
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			scan.nextLine();
			String[] city = new String[n];
			int[] people = new int[n];
			int[] hotel = new int[n];
			int[][] map = new int[n][n];
			
			long[] cost = new long[n];
			
			for(int i = 0; i < n; i++)
			{
				in = scan.nextLine().split(" ");
				city[i] = in[0];
				hotel[i] = Integer.parseInt(in[1]);
				people[i] = Integer.parseInt(in[2]);
			}
			
			for(int i = 0; i < n; i++)
			{
				in = scan.nextLine().split(" ");
				for(int j = 0; j < n; j++)
				{
					map[i][j] = Integer.parseInt(in[j]);
				}
			}
			
			
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n; j++)
				{
					if(j == i) continue;
					
					cost[i] += people[j] * hotel[i] + people[j] * map[j][i];
				}
			}
			
			long min = cost[0];
			String output = city[0];
			
			for(int i = 1; i < n; i++)
			{
				if(cost[i] < min || (cost[i] == min && city[i].compareTo(output) < 0))
				{
					min = cost[i];
					output = city[i];
				}
				
			}
			
			System.out.println(output);
		}
	}
}
