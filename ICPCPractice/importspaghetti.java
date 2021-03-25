import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class importspaghetti {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		n = Integer.parseInt(scan.readLine());
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] key = new String[n];
		HashSet<Integer>[] path = new HashSet[n];
		Arrays.setAll(path, x->new HashSet<Integer>());
		
		in = scan.readLine().split(" ");
		for(int i = 0; i < n; i++)
		{
			key[i] = in[i];
			map.put(in[i], i);
		}
		
		// parse import statements
		int k;
		for(int i = 0; i < n; i++)
		{
			in = scan.readLine().split(" ");
			k = parse(in[1]);
			
			while(k-- > 0)
			{
				parseImport(scan.readLine().split(" "), i, path, map);
			}
		}
		
		// determine cycles
		LinkedList<Integer> list = null;
		LinkedList<Integer> temp = null;
		int size = 0;
		
		for(int i = 0; i < n; i++)
		{
			temp = bfs(i, new boolean[n], path);
			
			if(temp != null && (list == null || temp.size() < size))
			{
				list = temp;
				size = temp.size();
			}
		}
		
		if(size == 0)
		{
			out.println("SHIP IT");
		}
		
		else
		{
			out.print(key[list.poll()]);
			for(int l : list)
				out.print(" " + key[l]);
			out.println();
		}
		out.flush();
	}

	private static LinkedList<Integer> bfs(int target, boolean[] visited, HashSet<Integer>[] path) 
	{
		visited[target] = true;
		LinkedList<Integer> next = new LinkedList<Integer>();
		next.add(target);
		
		int[] last = new int[n];
		int temp;
		
		LinkedList<Integer> ans = new LinkedList<Integer>();
		
		while(!next.isEmpty())
		{
			temp = next.pop();
			
			for(int node : path[temp])
			{
				if(node == target)
				{
					last[node] = temp;
					int i = target;
					
					while( (i = last[i]) != target)
						ans.addFirst(i);
					
					ans.addFirst(target);
					return ans;
				}
			
				
				if(visited[node]) continue;
				
				last[node] = temp;
				next.add(node);
				visited[node] = true;
			}
			
		}
		
		return null;
	}

	private static void parseImport(String[] in, int base, HashSet<Integer>[] path, HashMap<String, Integer> map) 
	{
		for(int i = 1; i < in.length; i++)
		{
			if(i == in.length - 1)
				path[base].add(map.get(in[i]));
			
			else
				path[base].add(map.get(in[i].substring(0, in[i].length() - 1)));
		}
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
