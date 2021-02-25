import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.TreeSet;

public class D_BoboniuChat {
	static int n, d, m;
	static Day ref, ref2;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		d = parse(in[1]);
		m = parse(in[2]);
		
		ref = new Day(n, m);
		ref2 = new Day(n, m+1);
	
		in = scan.readLine().split(" ");
		TreeSet<Day> days = new TreeSet<Day>();
		for(int i = 0; i < n ;i++)
			days.add(new Day(i, parse(in[i])));
		
		HashMap<String, Long> map = new HashMap<String, Long>();
		
		System.out.println(func(n, 0, 0, map, days));
	}
	
	public static long func(int daysLeft, int before, int after,  HashMap<String, Long> map, TreeSet<Day> days)
	{
		if(daysLeft <= 0)
		{
			return 0;
		}
		
		if(daysLeft == 1)
			return days.last().fun;
		
		String key = before + " " + after + " " + daysLeft;
		
		if(map.containsKey(key))
				return map.get(key);
		
		//try each individual
		//not exceed
		Day day1 = null, day2 = null;
		day1 = days.floor(ref);
		day2 = days.last();
		
		long ans = 0;
		
		if(day2 != day1)
		{
			days.remove(day2);
			ans = Math.max(ans,  day2.fun + func(daysLeft - 1  - d, before, after, map, days) );
			days.add(day2);
		}
		
		//exceed
		if(day1 != null) {
			days.remove(day1);
			ans = Math.max(ans, day1.fun + func(daysLeft - 1, before+1, after, map, days) );
			days.add(day1); 
		}
		
		map.put(key, ans);
		return ans;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Day implements Comparable<Day>
	{
		int id, fun;
		
		public Day(int i, int f)
		{
			id = i;
			fun = f;
		}

		@Override
		public int compareTo(Day d) {
			if(d.fun == fun)
				return id - d.id;
			
			return fun - d.fun;
		}
		
		
	}
}
