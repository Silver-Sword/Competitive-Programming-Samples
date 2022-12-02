import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class c {
	static int n;
	static long[] bitmask = new long[26+19];
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		generateBitmask();
		
		n = parse(scan.readLine());
		
		while(n != 0)
		{
			Person[] ray = new Person[n];
			for(int i = 0; i < n; i++)
				ray[i] = new Person(scan.readLine().split(" "));
			
			HashMap<Long, Integer> map = new HashMap<Long, Integer>();
			
			int ans = solve(0, ray, map, 0, 0);
			out.println(ans);
			
			n = parse(scan.readLine());
		}

		out.flush();
	}

	private static int solve(int index, Person[] ray, HashMap<Long, Integer> map, long mask, int count) 
	{
		int ans;
		if(index >= n)
			return count;
		
		// state already witnessed
		if(map.containsKey(mask))
			return map.get(mask);
		
		// if one is already activated
		if(( bitmask[ray[index].first] & mask ) > 0 || ( bitmask[ray[index].last] & mask ) > 0)
		{
			ans = solve(index + 1, ray, map, mask, count);
			map.put(mask, ans);
			return ans;
		}
		
		if(count > 19)
			return Integer.MAX_VALUE;
		
		// else choose both
		ans = Math.min( solve(index + 1, ray, map, mask + bitmask[ray[index].first], count + 1), solve(index + 1, ray, map, mask + bitmask[ray[index].last], count + 1) );
		map.put(mask, ans);
		return ans;
	}

	private static void generateBitmask() 
	{
		long num = 1;
		for(int i = 0; i < bitmask.length; i++, num *= 2)
		{
			bitmask[i] = num;
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Person
	{
		int first, last;
		
		public Person(String[] s)
		{
			first = s[0].charAt(0) - 'A';
			last = s[1].charAt(0) - 'A' + 26;
		}
		
		public String toString()
		{
			return "(" + (char) (first + 'A') + " " + (char) (last + 'A' - 26) + ")";
		}
	}
}
