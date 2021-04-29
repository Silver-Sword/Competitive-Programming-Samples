import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class latinsquare 
{
	static int n;
	static int k;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		k = parse(in[1]);
		
		int[][] grid = new int[n][n];
		for(int i = 0; i < n; i++)
		{
			in = scan.readLine().split(" ");
			for(int j = 0; j < n; j++)
				grid[i][j] = parse(in[j]);
		}
		
		boolean[][] colVal = new boolean[n][n];
		boolean[] preset = new boolean[n];
		for(int i = 0; i < n; i++)
			if(grid[0][i] != 0)
				preset[grid[0][i]-1] = true;
		
		Dinics din = setUpDinics(grid);
		for(int i = 0; i < n; i++)
		{
			if(preset[i]) continue;
			if(!runDinics(i+1, grid, din))
			{
				System.out.println("NO");
				return;
			}
		}
		
		out.println("YES");
		for(int i = 0; i < n; i++)
		{
			out.print(grid[i][0]);
			
			for(int j = 1; j < n; j++)
				out.print(" " + grid[i][j]);
			
			out.println();
		}
		
		out.flush();
	}

	private static Dinics setUpDinics(int[][] grid) 
	{
		Dinics din = new Dinics(n + n);
		
		// set up source and sink
		// r is 0 through n-1, c is n through 2n - 1
		
		for(int i = 0; i < n; i++)
		{
			// source to row
			din.add(din.source, i, 1, 0);
			
			// col to sink
			din.add(i + n, din.sink, 1, 0);
		}
		
		// set up valid edges
		for(int r = 0; r < n; r++)
			for(int c = 0; c < n; c++)
				if(grid[r][c] == 0)
					din.add(r, c + n, 1, 0);
		
		return din;
	}

	private static boolean runDinics(int value, int[][] grid, Dinics din) 
	{
		// run flow
		int ans = din.flow(n);
		
		if(ans != n)
			return false;
		
		// update grid and remove used edges
		ArrayDeque<Edge> toRemove = new ArrayDeque<Edge>();
		for(int r = 0; r < n; r++)
		{
			for(Edge e : din.adj[r])
			{
				// this edge was used
				if(e.v2 != din.source && e.flow > 0)
				{
					// update grid
					grid[e.v1][e.v2 - n] = value;
					
					// mark as remove
					toRemove.add(e);
					break;
				}
			}
		}
		
		// actually remove the necessary edges
		for(Edge e : toRemove)
		{
			din.adj[e.v1].remove(e);
			din.adj[e.v2].remove(e.rev);
		}
		
		return true;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Dinics 
	{
		// queue for top level bfs
		public ArrayDeque<Integer> queue;
		
		// stores the graph
		public ArrayList<Edge>[] adj;
		public int n;
		
		public int source, sink;
		
		// for bfs
		public boolean[] blocked;
		public int[] dist;
		
		final public static int oo = (int) 1E9;
		
		public Dinics(int N)
		{
			n = N;
			source = n++;
			sink = n++;
			
			// everything else is empty
			blocked = new boolean[n];
			dist = new int[n];
			queue = new ArrayDeque<Integer>();
			adj = new ArrayList[n];
			Arrays.setAll(adj, x->new ArrayList<Edge>());
		}
		
		public void add(int v1, int v2, int cap, int flow)
		{
			Edge e = new Edge(v1, v2, cap, flow);
			Edge rev = new Edge(v2, v1, 0, 0);
			
			e.rev = rev;
			rev.rev = e;
			
			adj[v1].add(e);
			adj[v2].add(rev);
		}
		
		// runs the other level bfs
		public boolean bfs()
		{
			// set up bfs
			queue.clear();
			Arrays.fill(dist, -1);
			dist[sink] = 0;
			queue.add(sink);
			
			// go backwards from sink looking for source
			// we just care to mark distances left to the sink
			while(!queue.isEmpty())
			{
				int node = queue.poll();
				if(node == source)
					return true;
				
				for(Edge e : adj[node])
				{
					if(e.rev.cap > e.rev.flow && dist[e.v2] == -1)
					{
						dist[e.v2] = dist[node] + 1;
						queue.add(e.v2);
					}
				}
			}
			
			// augmenting path exists iff we made it back to source
			return dist[source] != -1;
		}
		
		// runs inner dfs in Dinic's, from node pos with a flow of min
		public int dfs(int pos, int min)
		{
			// made it to sink, return this as our max flow for the augmenting path
			if(pos == sink) 
				return min;
				
			int flow = 0;
			
			// try ea edge from here
			for(Edge e : adj[pos])
			{
				int cur = 0;
				
				// if our destination isn't blocked and it's 1 closer to the sink adn there's flow, we can got this way
				if(!blocked[e.v2] && dist[e.v2] == dist[pos] - 1 && e.cap - e.flow > 0)
				{
					// recursively run dfs from here - limiting flow based on current and what's left on this edge
					cur = dfs(e.v2, Math.min(min - flow, e.cap - e.flow));
					
					// add the flow through this edge and subtract it from the reverse flow
					e.flow += cur;
					e.rev.flow = -e.flow;
					
					// add to the total flow
					flow += cur;
				}
				
				// no more can go through, we're good
				if(flow == min)
					return flow;
			}
			
			// mark if this node is now blocked
			blocked[pos] = flow != min;
			
			// this is the flow
			return flow;
		}
		
		public int flow(int flow)
		{
			clear();
			int ret = 0;
			int push = flow;
			
			// run a top level bfs
			while(bfs())
			{
				// reset this
				Arrays.fill(blocked, false);
				
				// run multiple dfs's until there is no flow left to push through
				ret += dfs(source, push);
				push = flow - ret;
				if(push == 0)
					return flow;
			}
			
			return ret;
		}
		
		// resets flow through all edges to be 0
		public void clear()
		{
			for(ArrayList<Edge> edges : adj)
				for(Edge e : edges)
					e.flow = 0;
		}
	}

	static class Edge
	{
		int v1, v2, cap, flow;
		Edge rev;
		Edge(int V1, int V2, int Cap, int Flow)
		{
			v1 = V1;
			v2 = V2;
			cap = Cap;
			flow = Flow;
		}
	}
	
}
