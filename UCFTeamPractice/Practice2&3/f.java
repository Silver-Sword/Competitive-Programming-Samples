import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.TreeSet;

public class f {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		int tt = Integer.parseInt(scan.readLine());
		LinkedList<Integer> index = new LinkedList<Integer>();
		
		for(int t = 0; t < tt; t++)
		{
			n = parse(scan.readLine());
			in = scan.readLine().split(" ");
			
			if(possible(in))
			{
				index.add(t+1);
			}
		}
		
		out.println(index.size());
		for(int i : index)
			out.println(i);
		
		out.flush();
	}

	private static boolean possible(String[] in) 
	{
		TreeSet<Integer> alice = new TreeSet<Integer>();
		int you = Integer.MAX_VALUE;
		int temp;
		
		alice.add(parse(in[0]));
		
		for(int i = 1; i < n; i++)
		{
			temp  = parse(in[i]);
			if(temp > you)
				return true;
			if(temp < alice.last())
				you = Math.min(alice.higher(temp), you);
			alice.add(parse(in[i]));
		}
		
		return false;

	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
