import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class telephone 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		
		int n = parse(in[0]);
		int k = parse(in[1]);
		
		in = scan.readLine().split(" ");
		
		int[] ray = new int[n];
		HashSet<Integer>[] breeds = new HashSet[k];
		Arrays.setAll(breeds, x->new HashSet<Integer>());
		
		for(int i = 0; i < n; i++)
		{
			ray[i] = parse(in[i]) - 1;
			breeds[ray[i]].add(i);
		}
		
		HashSet<Integer>[] path = new HashSet[k];
		Arrays.setAll(path, x->new HashSet<Integer>());
		char[] talks;
		for(int i = 0; i < k; i++)
		{
			talks = scan.readLine().toCharArray();
			for(int j = 0; j < k; j++)
				if(talks[j] == '1')
					path[i].add(j);
		}
		
		long ans;
		if(path[ray[0]].contains(ray[n-1]))
			ans = n-1;
		
		else
			ans = bfs(n, k, ray, breeds, path);
		
		System.out.println(ans);
	}
	
	public static boolean initForward(int[] ray, boolean[] visited, HashSet<Integer>[] path, PriorityQueue<Cow> list)
	{
		boolean[] validBreeds = new boolean[path.length];
		boolean[] visitedBreed = new boolean[path.length];
		
		for(int i = 0; i < ray.length; i++)
		{
			if(i == 0 || validBreeds[ray[i]])
			{
				visited[i] = true;
				list.add(new Cow(i, ray[i], i));
				
				// if not visited breed, add to valid
				if(!visitedBreed[ray[i]])
				{
					visitedBreed[ray[i]] = true;
					for(int p : path[ray[i]])
						validBreeds[p] = true;
					
 				}
			}
		}
		
		list.poll();
		return visited[visited.length-1];
	}
	
	private static long bfs(int n, int k, int[] ray, HashSet<Integer>[] breeds, HashSet<Integer>[] path) 
	{
		PriorityQueue<Cow> list = new PriorityQueue<Cow>();
		
		boolean[] visited = new boolean[n];
		boolean[] forward = new boolean[n];
		visited[0] = true;
		
		boolean reach = initForward(ray, forward, path, list);
		if(reach)
			return n-1;
		
		Cow next;
		
		while(!list.isEmpty())
		{
			next = list.poll();
			
			if(visited[next.index]) continue;
			if(next.index == n-1) return next.dist;
			
			visited[next.index] = true;
			
			// check each valid location
			for(int validBreed : path[next.breed])
			{
				for(int index : breeds[validBreed])
				{
					if(visited[index] || forward[index]) continue;
					
					list.add(new Cow(index, validBreed, next.dist + Math.abs(next.index - index)));
				}
			}
			
		}
		
		return -1;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Cow implements Comparable<Cow>
	{
		int index, breed;
		int dist;
		
		public Cow(int i, int b, int d)
		{
			index = i;
			breed = b;
			dist = d;
		}

		@Override
		public int compareTo(Cow c) 
		{
			if(dist != c.dist) return dist - c.dist;
			return index - c.index;
		}
	}
}
