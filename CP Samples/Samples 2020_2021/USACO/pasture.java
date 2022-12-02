import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class pasture {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		debug();
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		n = parse(scan.readLine());
		BigInteger ans = pow(n);
		
		TreeSet<Integer> yValues = new TreeSet<Integer>();
		
		Point[] cow = new Point[n];
		for(int i = 0; i < n; i++)
		{
			cow[i] = new Point(scan.readLine().split(" "));
			yValues.add(cow[i].y);
		}
		
		Arrays.sort(cow);
		HashMap<Integer, Integer> yValueCompressed = new HashMap<Integer, Integer>();
		int counter = 0;
		for(int y : yValues)
		{
			yValueCompressed.put(y, counter);
			counter++;
		}
		
		for(int i = 0; i < n; i++)
			cow[i].y = yValueCompressed.get(cow[i].y);
		
		int yMin, yMax;
		
		long temp;
		int between;
		
		for(int i = 0; i < n - 2; i++)
		{
			BIT bit = new BIT(yValueCompressed.size());
			long subtractedBelow = 0, subtractedAbove = 0;
			yMin = Math.min(cow[i].y, cow[i+1].y);
			yMax = Math.max(cow[i].y, cow[i+1].y);
			bit.update(yMin, 1);
			bit.update(yMax, 1);
			
			for(int j = i + 2; j < n; j++)
			{
				if(cow[j].y > cow[i].y)
				{
					between = bit.query(cow[i].y + 1, cow[j].y - 1);
					temp = getTheChooses(between);
					ans = ans.subtract(BigInteger.valueOf(temp));
					subtractedAbove += temp;
					ans = ans.subtract( combine(temp, subtractedBelow, bit.query(yMin, cow[i].y - 1)) );
				}
				
				else
				{
					between = bit.query(cow[j].y + 1, cow[i].y - 1);
					temp = getTheChooses(between);
					ans = ans.subtract(BigInteger.valueOf(temp));
					subtractedBelow += temp;
					ans = ans.subtract( combine(temp, subtractedAbove, bit.query(cow[i].y + 1, yMax)) );
				}
				
				bit.update(cow[j].y, 1);
			}
		}
		
		System.out.println(ans.toString());
	}
	
	private static BigInteger combine(long temp, long subtract, int between) 
	{
		BigInteger pow = pow(between).subtract(BigInteger.ONE);
		
		pow = pow.subtract(BigInteger.valueOf(subtract));
		
		return pow.multiply(BigInteger.valueOf(temp));
	}

	private static long getTheChooses(int between) 
	{
		long result = pow(between).longValue() - 1;
		return result;
	}
	
	public static long choose(long n, long k)
	{
		k = Math.max(n-k, k);
		long result = n;
		
		for(long i = n - 1, j = 2; i > k; i--, j++)
			result = (result / j) * i;
		return result;
		
	}

	public static BigInteger pow(int exp)
	{
		BigInteger result = new BigInteger("1");
		BigInteger base = new BigInteger("2");
		
		while(exp > 0)
		{
			if(exp % 2 == 1)
				result = result.multiply(base);
			
			exp = exp / 2;
			base = base.multiply(base);
		}
		
		return result;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class BIT
	{
		int[] bucket;
		int max;
		
		public BIT(int n)
		{
			max = n + 1;
			bucket = new int[max];
		}
		
		int sum(int index) 
		{
			int ans = bucket[++index];
			while(index > 0)	
				ans += bucket[index ^= (index & -index)];
				
			return ans;
		}

		//point update
		void update(int index, int val) 
		{
			index++;
			while(index < max) 
			{
				bucket[index] += val;
				index += (index & -index);
			}
		}
		
		int query(int start, int end)
		{
			if(start > end) return 0;
			
			if(start == 0)
				return sum(end);
			else
				return sum(end) - sum(start - 1);
		}

	}

	
	static class Point implements Comparable<Point>
	{
		int x, y;
		
		public Point(String[] in)
		{
			x = parse(in[0]);
			y = parse(in[1]);
		}

		@Override
		public int compareTo(Point p) {
			return x - p.x;
		}
	}
	
	public static void debug()
	{
		//System.out.println(choose(10, 6));
	}
}
