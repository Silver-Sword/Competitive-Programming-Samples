import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class game {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		int n = parse(scan.readLine());
		in = scan.readLine().split(" ");
		
		long total = 0;
		for(String s : in)
			total += parse(s) - 1;
		
		System.out.println(total % 2 == 0? "Second" : "First");
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
