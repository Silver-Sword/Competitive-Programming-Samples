import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.TreeSet;

public class cowntagion {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		n = parse(scan.readLine());
		String[] in;
		TreeSet<Integer>[] path = new TreeSet[n];
		Arrays.setAll(path, x->new TreeSet<Integer>());
		int u, v;
		
		for(int t = 1; t < n; t++)
		{
			in = scan.readLine().split(" ");
			u = parse(in[0]) - 1;
			v = parse(in[1]) - 1;
			
			path[u].add(v);
			path[v].add(u);
		}
		
		path[0].add(0);
		long count = bfs(0, path, 0);
		System.out.println(count);
	}

	private static long bfs(int index, TreeSet<Integer>[] path, int parent) 
	{
		int children = path[index].size() - 1;
		
		if(children == 0) return 0;
		
		int split = 0;
		int size = children + 1;
		while(size > 0)
		{
			split ++;
			size /= 2;
		}
		if(Integer.bitCount(children + 1) == 1)
			split--;
		
		long ans = split + children;
		
		for(int i : path[index])
		{
			if(i == parent) continue;
			
			ans += bfs(i, path, index);
		}
		
		return ans;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
