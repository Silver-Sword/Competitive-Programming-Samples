import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class CarSell {
	
	static int n;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			long[] p = new long[n];
			for(int i = 0; i < n; i++)
				p[i] = scan.nextLong();
			
			Arrays.sort(p);
			long sum = 0;
			//int i = 0;
			int day = 0;
			
			/*while(i >= 0 && i < n)
			{
				i = next(i, day + 1, p);
				sum += p[i] - day;
				
				day++;
				i++;
				
			}*/
			
			for(int i = n - 1; i >= 0; i--)
			{
				if(p[i] - day <= 0)
					break;
				sum += p[i] - day;
				day++;
			}
			
			System.out.println(sum % 1000000007);
		}

	}
	
	public static int next(int startIndex, int targetMin, long[] ray)
	{
		for(int i = startIndex; i < n; i++)
		{
			if(ray[i] >= targetMin)
				return i;
		}
		
		return -1;
	}

}
