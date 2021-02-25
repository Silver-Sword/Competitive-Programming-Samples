import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;

public class DanceMooves 
{
	static int n;
	static int k;
	public static void main(String[] args) throws IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		String[] in = scan.readLine().split(" ");
		n = Integer.parseInt(in[0]);
		k = Integer.parseInt(in[1]);
		
		HashSet<Integer>[] visited = new HashSet[n];
		int[] at = new int[n];
		int[] location = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			visited[i] = new HashSet<Integer>();
			visited[i].add(i);
			at[i] = i;
		}
		
		// perform all first iteration swaps
		while(k -- > 0)
		{
			in = scan.readLine().split(" ");
			swap(Integer.parseInt(in[0]) - 1, Integer.parseInt(in[1]) - 1, visited, at);
		}
		
		// end location
		for(int i = 0; i < n; i++)
		{
			location[at[i]] = i;
		}
		
		// create loops
		int[] ans = new int[n];
		for(int i = 0; i < n; i++)
		{
			if(ans[i] != 0)
				continue;
			
			LinkedList<Integer> loop = new LinkedList<Integer>();
			traverse(i, i, visited, location, ans, new HashSet<Integer>());
		}
		
		// print results
		for(int i = 0; i < n; i++)
			out.println(ans[i]);
		
		out.flush();
	}
	
	private static int traverse(int index, int target, HashSet<Integer>[] visited, int[] location, int[] ans, HashSet<Integer> record) 
	{
		for(int i : visited[index])
			record.add(i);
		
		if(location[index] == target)
		{
			ans[index] = record.size();
			return ans[index];
		}
		
		ans[index] = traverse(location[index], target, visited, location, ans, record);
		return ans[index];
	}

	private static void swap(int i, int j, HashSet<Integer>[] visited, int[] at) 
	{
		int a = at[i];
		int b = at[j];
		
		visited[a].add(j);
		visited[b].add(i);
		
		at[i] = b;
		at[j] = a;
	}
}
