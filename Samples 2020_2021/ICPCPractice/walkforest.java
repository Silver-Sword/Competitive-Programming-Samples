import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;

public class walkforest 
{
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String s = scan.readLine();
		
		while(!s.equals("0"))
		{
			String[] in = s.split(" ");
			n = parse(in[0]);
			int m = parse(in[1]);
			
			ArrayDeque<Edge>[] adj = new ArrayDeque[n];
			Arrays.setAll(adj, x->new ArrayDeque<Integer>());
			
			while(m -- > 0)
			{
				Edge e = new Edge(scan.readLine().split(" "));
				adj[e.u].add(e);
				adj[e.v].add(e);
			}
			
			long[] dist = new long[n];
			Arrays.fill(dist, -1);
			
			bfs(1, adj, dist);
			
			long[] paths = new long[n];
			Arrays.fill(paths, -1);
			long numPaths = dfs(0, paths, adj, dist, 1);
			
			out.println(numPaths);
			s = scan.readLine();
		}
		
		out.flush();
	}

	private static long dfs(int node, long[] paths, ArrayDeque<Edge>[] adj, long[] dist, int target) 
	{
		if(node == target)
			 return 1;
		
		if(paths[node] != -1)
			return paths[node];
		
		long path = 0;
		for(Edge e : adj[node])
		{
			int u = e.other(node);
			
			if(dist[u] < dist[node])
				path += dfs(u, paths, adj, dist, target);
		}
		
		paths[node] = path;
		return path;
	}

	private static void bfs(int start, ArrayDeque<Edge>[] adj, long[] dist) 
	{
		PriorityQueue<WeightedNode> q = new PriorityQueue<WeightedNode>();
		q.add(new WeightedNode(start, 0));
		
		while(!q.isEmpty())
		{
			WeightedNode node = q.poll();
			
			if(dist[node.node] != -1) continue;
			
			dist[node.node] = node.weight;
			
			for(Edge e : adj[node.node])
			{
				if(dist[e.other(node.node)] == -1)
					q.add(new WeightedNode(e.other(node.node), node.weight + e.weight));
			}
		}
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Edge
	{
		int u, v, weight;
		
		public Edge(String[] in)
		{
			u = parse(in[0]) - 1;
			v = parse(in[1]) - 1;
			weight = parse(in[2]);
		}
		
		public int other(int node)
		{
			return node == u ? v : u;
		}
	}
	
	static class WeightedNode implements Comparable<WeightedNode>
	{
		long weight;
		int node;
		
		public WeightedNode(int v, long w)
		{
			node = v;
			weight = w;
		}

		@Override
		public int compareTo(WeightedNode w) {
			return Long.compare(weight, w.weight);
		}
	}
}
