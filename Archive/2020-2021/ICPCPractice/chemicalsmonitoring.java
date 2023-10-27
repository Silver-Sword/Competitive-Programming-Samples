import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

public class chemicalsmonitoring 
{
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		n = parse(scan.readLine());

		Stream[] ray = new Stream[n];
		
		for(int i = 0; i < n; i++)
		{
			ray[i] = new Stream(scan.readLine().split(" "));
		}
		
		Arrays.sort(ray);
		
		long ans = solve(ray);
		out.println(ans);
		out.flush();
		
	}
	
	private static long solve(Stream[] ray) 
	{
		HashMap<Long, Long>[][] max = new HashMap[n][n];
		
		return solve(0, n-1, Long.MAX_VALUE, ray, max);
	}

	private static long solve(int l, int r, long timeMarker, Stream[] ray, HashMap<Long, Long>[][] max) 
	{
		if(l > r)
			return 0;
		
		if(max[l][r] == null)
			max[l][r] = new HashMap<Long, Long>();
		else if(max[l][r].containsKey(timeMarker))
			return max[l][r].get(timeMarker);
		
		long temp = 0;
		
		// try to take left
		if(ray[l].end <= timeMarker)
			temp = take(l, r, timeMarker, ray, max) + ray[l].priority;
		
		// ignore left
		temp = Math.max(temp, solve(l + 1, r, timeMarker, ray, max));
		
		max[l][r].put(timeMarker, temp);
		return temp;
	}

	

	private static long take(int l, int r, long timeMarker, Stream[] ray, HashMap<Long, Long>[][] max) 
	{
		// split this into, starts before end, starts after end
		
		int mid = getSplitPoint(ray[l].end, ray, l, r);
		
		if(mid == -1)
			return solve(l + 1, r, ray[l].end, ray, max);
		
		return solve(l + 1, mid, ray[l].end, ray, max) + solve(mid + 1, r, timeMarker, ray, max);
		
	}

	private static int getSplitPoint(long end, Stream[] ray, int l, int r)
	{
		for(int i = l + 1; i <= r; i++)
			if(ray[i].start >= end)
				return i - 1;
		return -1;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Stream implements Comparable<Stream>
	{
		long start, end, priority;
		
		
		public Stream(String[] in)
		{
			start = parse(in[0]);
			end = parse(in[1]) + start;
			priority = parse(in[2]);
		}


		@Override
		public int compareTo(Stream s) 
		{
			if(start != s.start) return Long.compare(start, s.start); // smaller start shoudl come first
			if(end != s.end) return Long.compare(s.end, end);	// bigger end should come first
			return Long.compare(s.priority, priority);  // bigger priority comes first
		}
		
		public String toString()
		{
			return start + " " + end + " " + priority;
		}
		
	}
}
