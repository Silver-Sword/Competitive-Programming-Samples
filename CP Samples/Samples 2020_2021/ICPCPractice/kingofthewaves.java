import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;

public class kingofthewaves {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		n = parse(scan.readLine());
		
		HashSet<Integer>[] path = new HashSet[n];
		Arrays.setAll(path, x->new HashSet<Integer>());

		char[] in;
		for(int i = 0; i < n; i++)
		{
			in = scan.readLine().toCharArray();
			
			for(int j = i + 1; j < n; j++)
			{
				// i wins
				if(in[j] == '0')
				{
					path[j].add(i);
				}
				
				// j wins
				else
					path[i].add(j);
					
			}
		}
		
		ArrayDeque<Integer> validPath = new ArrayDeque<Integer>();
		search(0, path, validPath, new boolean[n]);
		
		if(validPath.size() < n)
		{
			out.println("impossible");
		}
		
		else
		{
			out.print(validPath.pop());
			for(int i : validPath)
				out.print(" " + i);
			out.println();
		}
		
		out.flush();
	}

	private static void search(int index, HashSet<Integer>[] path, ArrayDeque<Integer> validPath, boolean[] visited) 
	{
		visited[index] = true;
		
		for(int next : path[index])
		{
			if(visited[next]) continue;
			
			search(next, path, validPath, visited);
		}
		
		validPath.add(index);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
