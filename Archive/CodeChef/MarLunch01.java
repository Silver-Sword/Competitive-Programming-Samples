import java.util.Scanner;

public class MarLunch01 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int n = scan.nextInt();
			int k = scan.nextInt();
			
			int[] ray = new int[n];
			for(int i = 0; i < n; i++)
				ray[i] = scan.nextInt();
			
			int max = Integer.MIN_VALUE;
			int total;
			for(int i = 0; i < n; i++)
			{
				if(i - k >= 0 && ray[i - k] > 0)
					continue;
				
				if(i + k < n && ray[i] < 0)
					continue;
				
				total = 0;
				for(int j = i; j < n; j += k)
					total += ray[j];
				
				if(total > max)
					max = total;
			}
			
			System.out.println(max);
		}
	}
}
