import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class d {
	static int n;
	public static void main(String[] args) throws IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int tt = parse(scan.readLine());
		String[] in;
		
		for(int t = 0; t < tt; t++)
		{
			n = parse(scan.readLine());
			
			Frog[] frog = new Frog[n];
			for(int i = 0; i < n; i++)
			{
				in = scan.readLine().split(" ");
				frog[i] = new Frog(i, parse(in[0]), parse(in[1]));
			}
			
			Arrays.sort(frog);
			
			long max = getMax(frog);
			out.println(max);
			
		}
		
		out.flush();
	}
	
	private static long getMax(Frog[] frog) 
	{
		int i, j, k;
		int max1 = n, max2 = n;
		long ans = 0, temp;
		
		for(i = 0; i < max1; i++)
		{
			for(j = i + 1; j < max2; j++)
			{
				if(!overlap(frog[i].start, frog[i].end, frog[j])) continue;
				k = getNextValue(Math.max(frog[i].start, frog[j].start), Math.min(frog[i].end, frog[j].end), frog, j+1);
				
				temp = frog[i].value + frog[j].value + (k != -1 ? frog[k].value : 0);
				
				if(temp >= ans)
				{
					max1 = j;
					max2 = (k == -1 ? max2 : k);
					ans = temp;
				}
			}
		}
		
		if(ans == 0)
			return frog[0].value;
		
		return ans;
	}
	
	public static int getNextValue(int start, int end, Frog[] frog, int low)
	{
		for(int i = low; i < n; i++)
		{
			if(overlap(start, end, frog[i]))
				return i;
		}
		
		return -1;
	}

	public static boolean overlap(int start, int end, Frog frog) 
	{
		return ((start <= frog.end && start >= frog.start) || (end >= frog.start && end <= frog.end));
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Frog implements Comparable<Frog>
	{
		int start, end;
		long value;
		
		public Frog(int i, int leap, int val)
		{
			value = val;
			start = Math.max(0, i - leap);
			end = Math.min(n - 1, i + leap);
		}

		@Override
		public int compareTo(Frog f) {
			return Long.compare(f.value, value);
		}
		
		public String toString()
		{
			return Long.toString(value);
		}
		
	}
	
	static class Compare implements Comparator<Frog>
	{
		int val;

		@Override
		public int compare(Frog frog1, Frog frog2) {
			if(val == 0)
			{
				if(frog1.start == frog2.start)
					return frog1.end - frog2.end;
				return frog1.start - frog2.start;
			}
			
			else
			{
				if(frog1.end == frog2.end)
					return frog1.start - frog2.start;
				return frog1.end - frog2.end;
			}
		}
	}
}
