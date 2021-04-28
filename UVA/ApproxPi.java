import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ApproxPi {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		n = parse(scan.readLine());
		
		while(n != 0)
		{
			int count = 0;
			int total = 0;
			
			int[] ray = new int[n];
			for(int i = 0;i < n; i++)
				ray[i] = parse(scan.readLine());
			
			Arrays.sort(ray);
			
			for(int i = 0; i < n; i++)
			{
				for(int j = i+1; j < n; j++)
				{
					if(gcd(ray[j], ray[i]) == 1)
						count++;
					
					total++;
				}
			}
			
			if(count == 0)
			{
				System.out.println("No estimate for this data set.");
			}
			
			else
			{
				double ans = (total * 6.0) / count;
				System.out.printf("%.6f\n", Math.sqrt(ans));
			}
			
			n = parse(scan.readLine());
		}
	}
	
	public static int gcd(int a, int b)
	{
		if(a == b || b == 0) return a;
		
		return gcd(b, a%b);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
