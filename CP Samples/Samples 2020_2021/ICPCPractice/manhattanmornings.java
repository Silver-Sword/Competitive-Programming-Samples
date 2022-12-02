import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.TreeSet;

public class manhattanmornings {
	static int n;
	
	static int xMin, xMax, yMin, yMax;
	static Point start, end;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		n = parse(scan.readLine());
		
		
		
		String[] in = scan.readLine().split(" ");
		start = new Point(parse(in[0]), parse(in[1]), -2);
		end = new Point(parse(in[2]), parse(in[3]), -1);
		
		xMin = Math.min(start.x, end.x);
		xMax = Math.max(start.x, end.x);
		yMin = Math.min(start.y, end.y);
		yMax = Math.max(start.y, end.y);
		
		TreeSet<Point> stops = new TreeSet<Point>(new Compare(end));
		for(int t = 0; t < n; t++)
		{
			in = scan.readLine().split(" ");
			Point temp = new Point(parse(in[0]), parse(in[1]), t);
			
			if(inBounds(temp))
				stops.add(temp);
		}
		
		TreeSet<Weighted> set = new TreeSet<Weighted>();
		Weighted temp;
		Weighted above;
		for(Point p : stops)
		{
			Weighted toAdd = new Weighted(p.y, 1, p.id);
			temp = set.floor(toAdd);
			if(set.isEmpty() || temp == null)
			{
				set.add(toAdd);
				continue;
			}
			
			toAdd.weight = temp.weight + 1;
			if(temp.y == p.y)
			{
				set.remove(temp);
				
			}
			
			while(true)
			{
				above = set.higher(temp);
				if(above != null && above.weight <= temp.weight)
					set.remove(above);
				else
					break;
			}
			
			set.add(toAdd);
			
		}
		
		System.out.println(getMax(set));
	}

	private static long getMax(TreeSet<Weighted> set) 
	{
		long max = 0;
		
		for(Weighted w : set)
			max = Math.max(w.weight, max);
		
		return max;
	}

	private static boolean inBounds(Point temp) 
	{
		return temp.x >= xMin && temp.x <= xMax && temp.y >= yMin && temp.y <= yMax;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Weighted implements Comparable<Weighted>
	{
		int y;
		long weight;
		int id;
		
		public Weighted(int y, long w, int i)
		{
			this.y = y;
			weight = w;
			id = i;
		}
		
		@Override
		public int compareTo(Weighted w) {
			
			return Math.abs(y - end.y) - Math.abs(w.y - end.y);
		}
		
		public String toString()
		{
			return y + ": " + weight;
		}
		 
	}
	
	static class Point
	{
		int x, y;
		int id;
		
		public Point(int x, int y, int id)
		{
			this.x = x;
			this.y = y;
			this.id = id;
		}
		
		public String toString()
		{
			return "(" + x + ", " + y + ")";
		}

	}
	
	static class Compare implements Comparator<Point>
	{
		Point end;
		
		public Compare(Point end)
		{
			this.end = end;
		}

		@Override
		public int compare(Point p1, Point p2) {
			if( Math.abs(end.x - p1.x) == Math.abs(end.x - p2.x) )
			{
				if(Math.abs(end.y - p1.y) == Math.abs(end.y - p2.y))
					return p1.id - p2.id;
				
				return Math.abs(end.y - p1.y) - Math.abs(end.y - p2.y);
			}
			
			return Math.abs(end.x - p1.x) - Math.abs(end.x - p2.x);
		}
	}
}
