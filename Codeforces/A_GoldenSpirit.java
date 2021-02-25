import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class A_GoldenSpirit {
	static long n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			n = Integer.parseInt(in[0]);
			long x = parse(in[1]);
			long time = parse(in[2]);
			
			long ans = 4 * n * time;
			
			if((2 * n - 1) * time < x)
			{
				ans += Math.min(Math.max((x+time+time) - ( (2 * n) * time), 0), time + Math.max((x+time) - ( (2 * n + 1) * time ), 0));
			}
			out.println(ans);
		}
		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
