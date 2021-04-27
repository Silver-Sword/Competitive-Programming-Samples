import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class rut {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		n = parse(scan.readLine());

		Cow[] cow = new Cow[n];
		for(int i = 0; i < n; i++)
			cow[i] = new Cow(scan.readLine().split(" "), i);
		
		PriorityQueue<Intersection> list = new PriorityQueue<Intersection>();
		long[] grass = new long[n];
		Arrays.fill(grass, Long.MAX_VALUE);
		
		for(int i = 0; i < n; i++)
		{
			for(int j = i + 1; j < n; j++)
			{
				if(intersects(cow[i], cow[j]))
					list.add(new Intersection(cow[i], cow[j]));
			}
		}
		
		Intersection point;
		while(!list.isEmpty())
		{
			point = list.poll();
			if(point.shares) continue;
			if(grass[point.index1] != Long.MAX_VALUE && stopsBefore(point.index1, point, grass, cow[point.index1])) continue;
			if(grass[point.index2] != Long.MAX_VALUE && stopsBefore(point.index2, point, grass, cow[point.index2])) continue;
			
			grass[point.loserIndex] = point.len;
		}
		
		for(long num : grass)
		{
			if(num == Long.MAX_VALUE)
				out.println("Infinity");
			else
				out.println(num);
		}
		
		out.flush();
	}
	
	private static boolean stopsBefore(int index, Intersection inter, long[] grass, Cow c) 
	{
		if(c.x + (c.dx * grass[index]) < inter.x) return true;
		if(c.y + (c.dy * grass[index]) < inter.y) return true;
		
		return false;
	}

	public static boolean intersects(Cow a, Cow b)
	{
		// if same direction
		if(a.dx == b.dx)
		{
			if(a.dx == 1)
			{
				return a.y == b.y;
			}
			
			else
				return a.x == b.x;
		}
		
		// if not same direction and a is going east
		else if(a.dx == 1)
		{
			return a.x <= b.x && b.y <= a.y;
		}
		
		// different directions and a is going north
		else
		{
			return a.y <= b.y && b.x <= a.x;
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Intersection implements Comparable<Intersection>
	{
		int x, y;
		int len;
		boolean shares;
		
		int index1, index2;
		int loserIndex;
		
		public Intersection(Cow a, Cow b)
		{
			if(a.dx == 1)
			{
				x = b.x;
				y = a.y;
				
				len = Math.max(y - b.y, x - a.x);
				shares = y - b.y == x - a.x;
				
				if(y - b.y < x - a.x)
					loserIndex = a.index;
				else
					loserIndex = b.index;
			}
			
			else
			{
				x = a.x;
				y = b.y;
				
				len = Math.max(y - a.y, x - b.x);
				shares = y - a.y == x - b.x;
				
				if(y - a.y < x - b.x)
					loserIndex = b.index;
				else
					loserIndex = a.index;
			}
			
			index1 = a.index;
			index2 = b.index;
		}
		
		@Override
		public int compareTo(Intersection i) 
		{
			return len - i.len;
		}
		
	}
	
	static class Cow
	{
		int x, y;
		int dx, dy;
		int index;
		
		public Cow(String[] in, int i)
		{
			index = i;
			
			if(in[0].equals("E"))
			{
				dx = 1;
				dy = 0;
			}
			
			else
			{
				dx = 0;
				dy = 1;
			}
			
			x = parse(in[1]);
			y = parse(in[2]);
		}
		
		public String toString()
		{
			return "(" + x + ", " + y + ") - " + (dx == 1 ? "E" : "N"); 
		}
	}
}
