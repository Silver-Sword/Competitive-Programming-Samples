import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class oil {
	static int n;
	static double eps = 1e-6;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		n = Integer.parseInt(scan.readLine());
		int[][] input = new int[n][3];
		
		for(int t = 0; t < n; t++)
		{
			in = scan.readLine().split(" ");
			
			for(int i = 0; i < 3; i++)
				input[t][i] = parse(in[i]);
		}
		order(input);
		
		long max = 0;
		for(int i = 0; i < n; i++)
		{
			if(input[i][0] - input[i][1] == 0) continue;
			max = Math.max(max, Math.max(drill(i, input, input[i][0]), drill(i, input, input[i][1])));
		}
		
		System.out.println(max);
	}

	private static long drill(int index, int[][] input, int x) 
	{
		int y = input[index][2];
		
		long max = input[index][1] - input[index][0];
		
		TreeSet<Vector> start = new TreeSet<Vector>();
		TreeSet<Vector> end = new TreeSet<Vector>();
		
		getAngles(index, input, x, y, start, end);
		
		long val = input[index][1] - input[index][0];
		while(!start.isEmpty())
		{
			//start comes first (or equal)
			if(compare(start.first().angle, end.first().angle) <= 0)
			{
				val += start.pollFirst().width;
				max = Math.max(val, max);
			}
			
			else
			{
				val -= end.pollFirst().width;
			}
		}
		
		return max;
	}
	
	private static void getAngles(int index, int[][] input, int x, int y, TreeSet<Vector> start, TreeSet<Vector> end) 
	{
		double s, e;
		for(int i = 0; i < n; i++)
		{
			if(i == index) continue;
			if(input[i][2] == y || input[i][1] == input[i][0]) continue;
			
			s = getAngle(x, y, input[i][0], input[i][2]);
			e = getAngle(x, y, input[i][1], input[i][2]);
			start.add(new Vector(Math.min(s, e), (long) (input[i][1] - input[i][0]), i));
			end.add(new Vector(Math.max(s, e), (long) (input[i][1] - input[i][0]), i));
		}
		
	}

	private static double getAngle(int x1, int y1, int x2, int y2) 
	{
		if(y2 > y1)
			return Math.atan2(y2 - y1, x2 - x1);
		else
			return Math.atan2(y1 - y2, x1 - x2);
	}

	private static int compare(Double a, Double b) 
	{
		if(Math.abs(a - b) <= eps)
			return 0;
		return Double.compare(a, b);
	}

	public static void order(int[][] input)
	{
		for(int i = 0; i < n; i++)
		{
			if(input[i][0] > input[i][1])
			{
				input[i][0] ^= input[i][1];
				input[i][1] ^= input[i][0];
				input[i][0] ^= input[i][1];
			}
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Vector implements Comparable<Vector>
	{
		double angle;
		long width;
		int id;
		
		public Vector(double a, long w, int i)
		{
			id = i;
			angle = a;
			width = w;
		}

		@Override
		public int compareTo(Vector v) {
			if(compare(angle, v.angle) == 0)
			{
				return id - v.id;
			}
			
			return compare(angle, v.angle);
		}
	}
}
