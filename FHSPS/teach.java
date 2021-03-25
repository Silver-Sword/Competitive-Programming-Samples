import java.util.Scanner;

public class teach {
	static int n;
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			int min = scan.nextInt();
			
			int[] weight = new int[n];
			int[] points = new int[n];
			for(int i = 0; i < n; i++)
			{
				weight[i] = scan.nextInt();
				points[i] = scan.nextInt();
			}
			
			long max = dp(0, min, new long[n][min+1], weight, points);
			System.out.println(max);
		}
	}

	public static long dp(int index, int left, long[][] dp, int[] weight, int[] points) {
		if(index >= n || left == 0)
			return 0;
		
		if(dp[index][left] != 0)
			return dp[index][left];
		
		long without = dp(index+1, left, dp, weight, points);
		long with = 0;
		if(left >= weight[index])
			with = dp(index+1, left - weight[index], dp, weight, points) + points[index];
		
		dp[index][left] = Math.max(without, with);
		return dp[index][left];
	}
}
