import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class cow 
{
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			n = parse(scan.readLine());
			ArrayDeque<Line> vertical = new ArrayDeque<Line>();
			ArrayDeque<Line> horiz = new ArrayDeque<Line>();
			//ArrayDeque<Intersection> intersect = new ArrayDeque<Intersection>();
			
			// add lines
			for(int i = 0; i < n; i++)
			{
				Line l = new Line(i, scan.readLine().split(" "));
				if(l.isVertical())
					vertical.add(l);
				else
					horiz.add(l);
			}
			
			Dinics din = new Dinics(n);
			// add intersections
			for(Line v : vertical)
				for(Line h : horiz)
					if(v.intersects(v, h))
					{
						//intersect.add(new Intersection(v.id, h.id, intersect.size()));
						din.add(v.id, h.id);
						//v.intersects = true;
						//h.intersects = true;
					}
			
			
			
			// add to source
			for(Line v : vertical)
				din.add(din.source, v.id);
			
			// add edges to sink
			for(Line h : horiz)
				din.add(h.id, din.sink);
			
			// add intersections edges
			/*for(Intersection i : intersect)
			{
				din.add(i.line1, n + i.id);
				din.add(i.line2, n + i.id);
			}
			*/
			
			// run flow
			int count = horiz.size() + vertical.size() - din.flow();
			
			/*// add in lines that didn't intersect
			for(Line v : vertical)
				if(!v.intersects)
					count++;
			for(Line h : horiz)
				if(!h.intersects)
					count++;
			*/
			
			out.println(count);
		}

		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Line
	{
		int[] start;
		int[] end;
		
		int id;
		boolean intersects;
		
		public Line(int id, String[] in)
		{
			start = new int[] {parse(in[0]), parse(in[1])};
			end = new int[] {parse(in[2]), parse(in[3])};
			
			if(start[0] > end[0])
				swap(0);
			
			if(start[1] > end[1])
				swap(1);
			
			this.id = id;
		}
		
		private void swap(int i) 
		{
			int temp = start[i];
			start[i] = end[i];
			end[i] = temp;
		}

		
		public String toString()
		{
			return "(" + start[0] + ", " + start[1] + ") to (" + end[0] + ", " + end[1] + ")";
		}
		
		public boolean isVertical()
		{
			return start[0] == end[0];
		}
		
		public boolean intersects(Line l)
		{
			if(isVertical())
				return intersects(this, l);
			else
				return intersects(l, this);
		}
		
		public boolean intersects(Line vert, Line horiz)
		{
			return (vert.start[0] >= horiz.start[0] && vert.start[0] <= horiz.end[0]) && (horiz.start[1] >= vert.start[1] && horiz.start[1] <= vert.end[1]);
		}
	}
	
	static class Intersection
	{
		int line1, line2;
		int id;
		
		public Intersection(int i, int j, int index)
		{
			line1 = i;
			line2 = j;
			id = index;
		}
		
		public String toString()
		{
			return line1 + " & " + line2;
		}
	}
	
	static class Dinics
	{
		// class variables
		int n, source, sink;
		int oo = (int) 1e9;
		
		// for the bfs
		ArrayDeque<Integer> queue;
		
		// for the dfs
		ArrayList<Edge>[] adj;
		int[] dist;
		boolean[] blocked;
		
		public Dinics(int N)
		{
			n = N;
			source = n++;
			sink = n++;
			
			queue = new ArrayDeque<Integer>();
			adj = new ArrayList[n];
			Arrays.setAll(adj, x->new ArrayList<Edge>());
			dist = new int[n];
			blocked = new boolean[n];
		}
		
		public void add(int v1, int v2)
		{
			Edge e = new Edge(v1, v2, 1);
			Edge rev = new Edge(v2, v1, 0);
			
			e.rev = rev;
			rev.rev = e;
			
			adj[v1].add(e);
			adj[v2].add(rev);
		}
		
		public void clear()
		{
			for(ArrayList<Edge> edges : adj)
				for(Edge e : edges)
					e.flow = 0;
		}
		
		public int flow()
		{
			int flow = 0;
			clear();
			
			while(bfs())
			{
				Arrays.fill(blocked, false);
				flow += dfs(source, oo);
			}
			
			return flow;
		}

		private boolean bfs() 
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
		

		private int dfs(int pos, int min) 
		{
			if(pos == sink)
				return min;
			
			int flow = 0;
			
			for(Edge e : adj[pos])
			{
				int cur = 0;
				// if not blocked, one closer to sink, and cap is greater than flow
				if(!blocked[e.v2] && dist[pos] - 1 == dist[e.v2] && e.cap > e.flow)
				{
					cur = dfs(e.v2, Math.min(min - flow, e.cap - e.flow));
					
					e.flow += cur;
					e.rev.flow = -e.flow;
					
					flow += cur;
				}
				
				if(flow == min)
					return flow;
			}
			
			blocked[pos] = flow != min;
			return flow;
		}

	}
	
	static class Edge
	{
		int v1, v2, flow, cap;
		
		Edge rev;
		
		public Edge(int V1, int V2, int cap)
		{
			v1 = V1;
			v2 = V2;
			flow = 0;
			this.cap = cap;
		}
		
		public String toString()
		{
			return v1 + " -> " + v2;
		}
	}
}
