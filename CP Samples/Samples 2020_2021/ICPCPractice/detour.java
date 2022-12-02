import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class detour {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		int tt = parse(in[1]);
		
		HashMap<Intersection, Long>[] path = new HashMap[n];
		Arrays.setAll(path, x->new HashMap<Intersection, Long>());
		Intersection[] ray = new Intersection[n];
		for(int i = 0; i < n; i++)
			ray[i] = new Intersection(i);
		
		int u, v; 
		long cost;
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			u = parse(in[0]);
			v = parse(in[1]);
			cost = parse(in[2]);
			
			path[u].put(ray[v], cost);
			path[v].put(ray[u], cost);
		}
		
		for(int i = 0; i < n; i++)
			ray[i].addConnections(path[i]);
		
		bfs(path, ray);
		
		ArrayDeque<Integer> validPath = new ArrayDeque<Integer>();
		ray[0].traverse(1, validPath, new boolean[n]);
		
		if(validPath.isEmpty())
		{
			out.println("impossible");
		}
		
		else
		{
			out.print(validPath.size());
			for(int i : validPath)
				out.print(" " + i);
			out.println();
		}
		
		out.flush();
	}

	private static void bfs(HashMap<Intersection, Long>[] path, Intersection[] ray) 
	{
		PriorityQueue<Weighted> toVisit = new PriorityQueue<Weighted>();
		toVisit.add(new Weighted(1, 0, -1));
		boolean[] visited = new boolean[n];
		
		Weighted current;
		while(!toVisit.isEmpty())
		{
			current = toVisit.poll();
			
			if(visited[current.id]) continue;
			
			visited[current.id] = true;
			ray[current.id].shortest = current.parent;
			
			for(Entry<Intersection, Long> entry : path[current.id].entrySet())
			{
				toVisit.add(new Weighted(entry.getKey().id, current.dist + entry.getValue(), current.id));
			}
			
		}
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Intersection
	{
		int id;
		HashMap<Intersection, Long> connections;
		int shortest;
		
		public Intersection(int i)
		{
			id = i;
			shortest = -1;
		}
		
		public boolean traverse(int target, ArrayDeque<Integer> validPath, boolean[] visited) 
		{
			visited[id] = true;
			
			if(id == target)
			{
				validPath.push(id);
				return true;
			}
			
			for(Intersection child : connections.keySet())
			{
				if(child.id == shortest || visited[child.id]) continue;
				
				if(child.traverse(target, validPath, visited))
				{
					validPath.push(id);
					return true;
				}
			}
			
			return false;
			
		}

		public void addConnections(HashMap<Intersection, Long> connect)
		{
			connections = connect;
		}
		
	}
	
	static class Weighted implements Comparable<Weighted>
	{
		long dist;
		int id;
		int parent;
		
		public Weighted(int id, long dist, int parent)
		{
			this.id = id;
			this.dist = dist;
			this.parent = parent;
		}

		@Override
		public int compareTo(Weighted w) {
			return Long.compare(dist, w.dist);
		}
	}
}
