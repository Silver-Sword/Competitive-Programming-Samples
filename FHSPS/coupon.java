import java.util.Scanner;

public class coupon {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			double cost = scan.nextDouble();
			
			int n = scan.nextInt();
			double[] subtract = new double[n];
			for(int i = 0; i < n; i++)
				subtract[i] = scan.nextDouble();
			
			int m = scan.nextInt();
			for(int i = 0; i < m; i++)
				cost *= (1 - (scan.nextDouble() / 100.0));
			
			for(int i = 0; i < n; i++)
				cost -= subtract[i];
			
			System.out.printf("%.2f\n", Math.max(0, cost));
		}
	}
}
