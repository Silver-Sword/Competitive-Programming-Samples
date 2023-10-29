import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Feb21_2 
{
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in;
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			
			n = parse(scan.readLine());
			in = scan.readLine().split(" ");
			
			long[] ray = new long[n];
			for(int i = 0; i < n; i++)
				ray[i] = parse(in[i]);
			
			Arrays.sort(ray);
			long ans = ray[n-1] - ray[0];
			long max = 0, temp;
			
			for(int i = 1; i < n-1; i++)
			{
				temp = (ray[n-1] - ray[i]) + (ray[i] - ray[0]);
				
				if(temp > max)
					max = temp;
			}
			
			ans += max;
			out.println(ans);
		}

		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
