import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class frozen {
	static int n;
	static double pi = 3.141592653590;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		String[] in;
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			double vol = (double) (parse(in[0]));
			int a = parse(in[1]), b = parse(in[2]), c = parse(in[3]);
			
			double ans = (vol * 3.0) / (pi * 4.0 * (a * a * a + b * b * b + c * c * c));
			ans = Math.pow(ans, 1.0 / 3.0) * a;
			
			System.out.printf("%.2f\n", ans);
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
