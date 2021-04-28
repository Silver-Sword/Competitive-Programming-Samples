import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Feb29 {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		int tt = parse(scan.readLine());
		int year1, year2;
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			
			year1 = parse(in[2]);
			if(! (in[0].equals("January") || in[0].equals("February")))
				year1++;
			
			in = scan.readLine().split(" ");
			year2 = parse(in[2]);
			
			if( in[0].equals("January") || (in[0].equals("February") && parse(in[1].substring(0, in[1].length() - 1)) != 29))
				year2 --;
			
			long count = (year2 / 400 ) - (year2 / 100) + (year2 / 4);
			count -= (year1 - 1) / 400 - (year1 - 1) / 100 + (year1 - 1) / 4;
			
			out.print("Case " + (t+1) + ": ");
			out.println(count);
		}
		
		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
