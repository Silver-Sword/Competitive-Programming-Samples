import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ComingHome {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		int tt = parse(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			n = parse(in[0]);
			int start = getTime(in[1]);
			
			int[] cost = new int[n];
			for(int i = 0; i < n; i++)
			{
				in = scan.readLine().split(" ");
				cost[i] = ((getTime(in[0]) + 1440) - start) % 1440 + parse(in[1]);
			}
			
			Arrays.sort(cost);
			System.out.println("Case " + (t+1) + ": " + cost[0]);
		}
	}
	
	public static int getTime(String time)
	{
		String[] temp = time.split(":");
		
		int ans = parse(temp[0]) * 60 + parse(temp[1]);
		
		return ans;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}

}
