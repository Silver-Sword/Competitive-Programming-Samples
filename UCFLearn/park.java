import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public class park {
	static int MAX_X;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		
		int n = (int) parse(in[0]);
		int m = (int) parse(in[1]);
		
		while(n != 0 && m != 0)
		{
			//coordinate compress
			
			TreeSet<Integer>[] xytrees = new TreeSet[200001];
			TreeSet<Integer>[] yxtrees = new TreeSet[200001];
			Arrays.setAll(xytrees, x->new TreeSet<Integer>());
			Arrays.setAll(yxtrees, x->new TreeSet<Integer>());
			
			TreeSet<Integer> xPath = new TreeSet<Integer>();
			TreeSet<Integer> yPath = new TreeSet<Integer>();
			
			takeInInput(scan, n, m, xytrees, yxtrees, xPath, yPath);
			int count = 0;
			for(int i = 0; i <= MAX_X; i++)
			{
				for(int y : xytrees[i])
				{
					if(visible(i, y, xytrees, yxtrees, xPath, yPath))
						count++;
				}
			}
			
			System.out.println(count);
			
			in = scan.readLine().split(" ");
			n = (int) parse(in[0]);
			m = (int) parse(in[1]);
		}
	}
	
	private static boolean visible(int x, int y, TreeSet<Integer>[] xytrees, TreeSet<Integer>[] yxtrees, TreeSet<Integer> xPath, TreeSet<Integer> yPath) 
	{
		return visibleInOneDirection(x, yxtrees[y], xPath) || visibleInOneDirection(y, xytrees[x], yPath);
	}
	
	public static boolean visibleInOneDirection(int coor, TreeSet<Integer> trees, TreeSet<Integer> paths)
	{
		Integer a = paths.lower(coor);
		Integer b = paths.higher(coor);
		
		if(a != null)
		{
			if(trees.higher(a) == coor)
				return true;
		}
		
		if(b != null)
			if(trees.lower(b) == coor)
				return true;
		
		return false;
		
		
	}

	private static void takeInInput(BufferedReader scan, int n, int m, TreeSet<Integer>[] xytrees, TreeSet<Integer>[] yxtrees, TreeSet<Integer> xPath, TreeSet<Integer> yPath) throws IOException 
	{
		TreeSet<Long> x = new TreeSet<Long>();
		TreeSet<Long> y = new TreeSet<Long>();
		
		HashMap<Long, Integer> xMap = new HashMap<Long, Integer>();
		HashMap<Long, Integer> yMap = new HashMap<Long, Integer>();
		
		long[][] points = new long[n][2];
		
		String[] in;
		for(int t = 0; t < n; t++)
		{
			in = scan.readLine().split(" ");
			points[t][0] = parse(in[0]);
			points[t][1] = parse(in[1]);
			
			x.add(points[t][0]);
			y.add(points[t][1]);
		}
		
		LinkedList<Long> pathx = new LinkedList<Long>();
		LinkedList<Long> pathy = new LinkedList<Long>();
		
		for(int t = 0; t < m; t++)
		{
			in = scan.readLine().split("=");
			
			if(in[0].charAt(0) == 'x')
			{
				pathx.add(parse(in[1]));
				x.add(parse(in[1]));
			}
			else
			{
				pathy.add(parse(in[1]));
				y.add(parse(in[1]));
			}
				
		}
		
		int i = 0;
		for(Long xVal : x)
		{
			xMap.put(xVal, i);
			MAX_X = i;
			i++;
		}
		
		
		i = 0;
		for(long yVal : y)
		{
			yMap.put(yVal, i);
			i++;
		}
		
		int xVal, yVal;
		for(int t = 0; t < n; t++)
		{
			xVal = xMap.get(points[t][0]);
			yVal = yMap.get(points[t][1]);
			
			xytrees[xVal].add(yVal);
			yxtrees[yVal].add(xVal);
		}
		
		for(long val : pathx)
		{
			xPath.add(xMap.get(val));
		}
		
		for(long val : pathy)
		{
			yPath.add(yMap.get(val));
		}
		
		
	}

	public static long parse(String num)
	{
		return Long.parseLong(num);
	}
}
