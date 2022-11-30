import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class MiddleClass {
	
	static int n, x;
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			x = scan.nextInt();
			
			int[] ray = new int[n];
			long sum = 0;
			for(int i = 0; i < n; i++)
			{
				ray[i] = scan.nextInt();
				sum += ray[i];
			}
			
			Arrays.sort(ray);
			
			if(sum / ((double) n) >= x)
			{
				System.out.println(n);
				continue;
			}
			
			for(int i = 1; i <= n; i++)
			{
				sum -= ray[i-1];
				
				if(i == n)
				{
					System.out.println(0);
					break;
				}
				
				if(sum / ((double) n - i) >= x)
				{
					System.out.println(n - i);
					break;
				}
			}
			
		}
		

	}

}
