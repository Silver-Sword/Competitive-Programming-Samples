import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class lemonadetrade {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			n = Integer.parseInt(scan.readLine());
			
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
}
