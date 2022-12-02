import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class g {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		double money = Double.parseDouble(scan.readLine());
		int events = Integer.parseInt(scan.readLine());
		String[] in = scan.readLine().split(" ");
		
		double buy = money / Double.parseDouble(in[0]);
		double max = 0 - money;
		double val;
		for(int e = 1; e < events; e++)
		{
			val = Double.parseDouble(in[e]);
			
			max = Math.max( max , val * buy - money );
			buy = Math.max(money / val, buy);
		}
		
		System.out.printf("%.2f\n", max);
	}
}
