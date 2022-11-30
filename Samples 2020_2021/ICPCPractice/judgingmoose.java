import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class judgingmoose {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		int l = parse(in[0]);
		int r = parse(in[1]);
		
		if(l + r == 0)
			out.println("Not a moose");
		else if(l == r)
		{
			out.println("Even " + (2 * l));
		}
		
		else
			out.println("Odd " + (2 * Math.max(l, r)));
		
		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
