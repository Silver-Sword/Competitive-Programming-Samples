import java.util.Arrays;
import java.util.Scanner;

public class wolf {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			int[] dollars = new int[n];
			double[] prob = new double[n];
			
			for(int i = 0; i < n; i++)
				dollars[i] = scan.nextInt();
			
			for(int i = 0; i < n; i++)
				prob[i] = scan.nextDouble();
			
			Arrays.sort(dollars);
			Arrays.sort(prob);
			
			double ans = 0;
			for(int i = 0; i < n; i++)
			{
				ans += dollars[i] * prob[i];
				ans -= dollars[i] * (1.0 - prob[i]);
			}
			
			System.out.printf("%.2f\n", ans);
			
		}
	}
}
