import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

public class freefood_C 
{
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in;
		n = parse(scan.readLine());
		
		boolean[] free = new boolean[366];
		
		for(int t = 0; t < n; t++)
		{
			in = scan.readLine().split(" ");
			int[] ray = new int[] {parse(in[0]), parse(in[1])};
			for(int i = ray[0]; i <= ray[1]; i++)
				free[i] = true;
		}
		
		int count = 0;
		
		for(int i = 1; i < free.length; i++)
			if(free[i])
				count++;
		
		out.println(count);

		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
