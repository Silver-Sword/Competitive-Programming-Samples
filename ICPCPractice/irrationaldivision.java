import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class irrationaldivision {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");

		int row = parse(in[0]);
		int col = parse(in[1]);
		
		// 1 by something
		if(row == 1)
		{
			if(col % 2 == 0)
			{
				out.println(2);
			}
			
			else
				out.println(1);
		}
		
		// row is odd, col is even
		else if(row % 2 == 1 && col % 2 == 0)
		{
			if(col > row)
			{
				out.println(2);
			}
			
			else
				out.println(0);
		}
		
		// row is even
		else if(row % 2 == 0)
		{
			out.println(0);
		}
		
		// row is odd, col is odd
		else
			out.println(1);
		
		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
