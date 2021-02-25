import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class nile {
	static int n;
	static int cells;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in;
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++) // 19, 26
		{
			in = scan.readLine().split(" ");
			int W = parse(in[0]);
			int H = parse(in[1]);
			int b = parse(in[2]);
			
			cells = W * H;
			
			// set up river blocks (just sim)
			boolean[][] blocked = new boolean[H][W];
			while (b --  > 0)
			{
				in = scan.readLine().split(" ");
				int xEnd = parse(in[2]);
				int yEnd = parse(in[3]);
				
				for(int i = parse(in[1]); i <= yEnd; i++)
					for(int j = parse(in[0]); j <= xEnd; j++)
						blocked[i][j] = true;
			}
			
			Dinics din = init(W, H, blocked);
			out.print("Case #" + (t+1) + ": ");
			out.println(din.flow());
			
		}

		out.flush();
	}
	
	private static Dinics init(int w, int h, boolean[][] blocked) 
	{
		Dinics din = new Dinics(cells * 2);
		
		// set up beginning and end of river
		for(int x = 0; x < w; x++)
		{
			if(!blocked[0][x])
				din.add(din.source, x);
			if(!blocked[h-1][x])
				din.add(send(h-1, x, w), din.sink);
		}
		
		// set up receive and send edges
		for(int y = 0; y < h; y++)
			for(int x = 0; x < w; x++)
				if(!blocked[y][x])
					din.add(receive(y, x, w), send(y, x, w));
		
		// set up horizontal edges
		for(int y = 0; y < h; y++)
			for(int x = 1; x < w; x++)
			{
				if(!blocked[y][x] && !blocked[y][x-1])
				{
					din.add(send(y, x, w), receive(y, x-1, w));
					din.add(send(y, x-1, w), receive(y, x, w));
				}
			}
		
		// set up vertical edges
		for(int x = 0; x < w; x++)
			for(int y = 0; y < h - 1; y++)
				if(!blocked[y][x] && !blocked[y+1][x])
				{
					din.add(send(y, x, w), receive(y+1, x, w));
					din.add(receive(y+1, x, w), send(y, x, w));
				}
		
		return din;
	}

	public static int receive(int y, int x, int W)
	{
		return y * W + x;
	}
	
	public static int send(int y, int x, int W)
	{
		return cells + y * W + x;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Dinics
	{
		int n, source, sink;
		int oo = (int) 1e9;
		
		ArrayDeque<Integer> queue;
		ArrayDeque<Edge>[] adj;
		int[] dist;
		boolean[] blocked;
		
		public Dinics(int N)
		{
			n = N;
			source = n++;
			sink = n++;
			
			queue = new ArrayDeque<Integer>();
			adj = new ArrayDeque[n];
			Arrays.setAll(adj, x->new ArrayDeque<Edge>());
			dist = new int[n];
			blocked = new boolean[n];
		}
		
		public void add(int v1, int v2)
		{
			Edge e = new Edge(v1, v2, 1);
			e.rev = new Edge(v2, v1, 0);
			
			e.rev.rev = e;
			adj[v1].add(e);
			adj[v2].add(e.rev);
		}
		
		public int flow()
		{
			int flow = 0;
			
			while(bfs())
			{
				Arrays.fill(blocked, false);
				flow += dfs(source, oo);
			}
			
			return flow;
		}

		public boolean bfs() 
		{
			queue.clear();
			queue.add(sink);
			Arrays.fill(dist, -1);
			dist[sink] = 0;
			
			while(!queue.isEmpty())
			{
				int node = queue.poll();
				
				if(node == source)
					return true;
				
				for(Edge e : adj[node])
				{
					if(dist[e.v2] == -1 && e.rev.cap > e.rev.flow)
					{
						dist[e.v2] = dist[node] + 1;
						queue.add(e.v2);
					}
				}
			}
			
			return dist[source] != -1;
		}
		
		public int dfs(int node, int min)
		{
			if(node == sink)
				return min;
			
			int flow = 0;
			for(Edge e : adj[node])
			{
				int cur = 0;
				
				// not blocked, one closer, flow < cap
				if(dist[e.v2] + 1 == dist[node] && e.flow < e.cap && !blocked[e.v2])
				{
					cur = dfs(e.v2, Math.min(min - flow, e.cap - e.flow));
					
					e.flow = cur;
					e.rev.flow = -e.flow;
					
					flow += cur;
					
					if(flow == min)
						return flow;
				}
				
				
			}
			
			blocked[node] = flow != min;
			return flow;
		}
	}
	
	static class Edge
	{
		int v1, v2, flow, cap;
		Edge rev;
		
		public Edge(int V1, int V2, int Cap)
		{
			v1 = V1;
			v2 = V2;
			flow = 0;
			cap = Cap;
		}
		
		public String toString()
		{
			return v1 + " -> " + v2;
		}
	}
}

