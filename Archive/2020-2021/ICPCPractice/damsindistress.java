import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;

public class damsindistress 
{
	static int n;
	static long min = Long.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		int flood = parse(in[1]);
		
		ArrayDeque<Integer>[] path = new ArrayDeque[n+1];
		Arrays.setAll(path, x->new ArrayDeque<Integer>());

		long[] cap = new long[n+1];
		cap[0] = flood;
		long[] current = new long[n+1];
		
		for(int i = 1; i <= n; i++)
		{
			in = scan.readLine().split(" ");
			path[parse(in[0])].add(i);
			cap[i] = parse(in[1]);
			current[i] = parse(in[2]);
		}
		
		solve(0, 0, cap, current, path);
		out.println(min);
		
		out.flush();
	}

	private static long solve(int node, long send, long[] cap, long[] current, ArrayDeque<Integer>[] path) 
	{
		// update necessary send amount
		send = Math.max(send, cap[node]);
		
		if(current[node] > send)
		{
			min = 0;
			return 0;
		}
		
		send -= current[node];
		
		if(send < min)
			min = send;
		
		long min = send;
		// traverse child paths
		for(Integer v : path[node])
		{
			min = Math.min(min, solve(v, send, cap, current, path));
		}
		return min;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
