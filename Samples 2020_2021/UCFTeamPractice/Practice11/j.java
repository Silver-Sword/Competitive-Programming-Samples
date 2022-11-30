import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class j {
	static int concerts;
	static int cities;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		cities = parse(in[0]);
		concerts = parse(in[1]);
		
		TreeMap<Integer, Integer>[] roundTicket = new TreeMap[cities];
		Arrays.setAll(roundTicket, x->new TreeMap<Integer, Integer>());
		TreeMap<Integer, Integer>[] oneWayTicket = new TreeMap[cities];
		Arrays.setAll(oneWayTicket, x->new TreeMap<Integer, Integer>());
		
		int[] path = new int[concerts];
		in = scan.readLine().split(" ");
		for(int i = 0; i < concerts; i++)
			path[i] = parse(in[i]) - 1;
		
		int m = parse(scan.readLine());
		for(int i = 0; i < m; i++)
		{
			in = scan.readLine().split(" ");
			int u = parse(in[0]) - 1;
			int v = parse(in[1]) - 1;
			int cost = parse(in[3]);
			
			if(in[2].charAt(0) == 'O')
			{
				if(oneWayTicket[u] == null) oneWayTicket[u] = new TreeMap<Integer, Integer>();
				oneWayTicket[u].put(v, Math.min(cost, oneWayTicket[u].getOrDefault(v, Integer.MAX_VALUE)));
			}
			
			else
			{
				if(roundTicket[u] == null) roundTicket[u] = new TreeMap<Integer, Integer>();
				roundTicket[u].put(v, Math.min(cost, roundTicket[u].getOrDefault(v, Integer.MAX_VALUE)));
			}
		}
		
		long ans = solve(path, roundTicket, oneWayTicket);
		System.out.println(ans);
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	public static long solve(int[] path, TreeMap<Integer, Integer>[] roundTicket, TreeMap<Integer, Integer>[] oneWayTicket)
	{
		long ans = 0;
		
		TreeMap<Integer, ArrayList<Integer>>[] trips = new TreeMap[cities];
		
		int u, v, index, key;
		
		for(int i = 0; i < concerts - 1; i++)
		{
			u = path[i];
			v = path[i + 1];
			
			index = Math.min(u, v);
			key = Math.max(u, v);
			
			if(trips[index] == null)
				trips[index] = new TreeMap<Integer, ArrayList<Integer>>();
			
			if(!trips[index].containsKey(key))
				trips[index].put(key, new ArrayList<Integer>());
			
			trips[index].get(key).add(u < v ? 0 : 1);
		}
		
		
		for(int i = 0; i < cities; i++)
		{
			if(trips[i] != null)
			{
				for(Entry<Integer, ArrayList<Integer>> entry : trips[i].entrySet())
				{
					long[][] cost = new long[2][2]; // O = 0; R = 1;
					cost[0][0] = oneWayTicket[i].getOrDefault(entry.getKey(), Integer.MAX_VALUE);
					cost[1][0] = oneWayTicket[entry.getKey()].getOrDefault(i, Integer.MAX_VALUE);
					cost[0][1] = roundTicket[i].getOrDefault(entry.getKey(), Integer.MAX_VALUE);
					cost[1][1] = roundTicket[entry.getKey()].getOrDefault(i, Integer.MAX_VALUE);
					
					
					ans += getSegmentCost(entry.getValue(), cost);
				}
			}
		}
		
		return ans;
	}
	
	public static long getSegmentCost(ArrayList<Integer> path, long[][] cost)
	{
		boolean[] used = new boolean[path.size()];
		long ans = 0;
		
		cost[0][0] = Math.min(cost[0][0], cost[0][1]);
		cost[1][0] = Math.min(cost[1][0], cost[1][1]);
		
		long combinedOneWay = cost[0][0] + cost[1][0];
		
		if(combinedOneWay <= cost[0][1] && combinedOneWay <= cost[1][1])
		{
			for(int i : path)
			{
				if(i == 0)
					ans += cost[0][0];
				else
					ans += cost[1][0];
			}
		}
		
		else if(combinedOneWay >= cost[0][1] && combinedOneWay >= cost[1][1])
		{
			if(cost[0][1] < cost[1][1])
			{
				ans = goAround(0, path, used, cost[0][1]);
				ans += goAround(1, path, used, cost[1][1]);
			}
			else
			{
				ans += goAround(1, path, used, cost[1][1]);
				ans += goAround(0, path, used, cost[0][1]);
			}
			
			for(int i = 0; i < path.size(); i++)
			{
				if(used[i]) continue;
				
				if(path.get(i) == 0)
					ans += cost[0][0];
				else
					ans += cost[1][0];
			}
		}
		
		else
		{
			if(cost[0][1] < cost[1][1])
				ans = goAround(0, path, used, cost[0][1]);
			else
				ans = goAround(1, path, used, cost[1][1]);
			
			for(int i = 0; i < path.size(); i++)
			{
				if(used[i]) continue;
				
				if(path.get(i) == 0)
					ans += cost[0][0];
				else
					ans += cost[1][0];
			}
			
		}
			
		return ans;
	}

	private static long goAround(int target, ArrayList<Integer> path, boolean[] used, long cost) 
	{
		int i = 0, j = 1;
		long ans = 0;
		int size = path.size();
		
		while(i < size && j < size)
		{
			if(used[i] || path.get(i) != target) i++;
			else if(j <= i || used[j] || path.get(j) == target) j++;
			else
			{
				used[i] = true;
				used[j] = true;
				i++;
				j++;
				ans ++;
			}
		}
		
		ans *= cost;
		return ans;
	}
	
	/*public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		cities = parse(in[0]);
		concerts = parse(in[1]);
		
		TreeMap<Integer, Integer>[] roundTicket = new TreeMap[cities];
		Arrays.setAll(roundTicket, x->new TreeMap<Integer, Integer>());
		TreeMap<Integer, Integer>[] oneWayTicket = new TreeMap[cities];
		Arrays.setAll(oneWayTicket, x->new TreeMap<Integer, Integer>());
		
		int[] path = new int[concerts];
		in = scan.readLine().split(" ");
		for(int i = 0; i < concerts; i++)
			path[i] = parse(in[i]) - 1;
		
		int m = parse(scan.readLine());
		for(int i = 0; i < m; i++)
		{
			in = scan.readLine().split(" ");
			int u = parse(in[0]) - 1;
			int v = parse(in[1]) - 1;
			int cost = parse(in[3]);
			
			if(in[2].charAt(0) == 'O')
			{
				if(oneWayTicket[u] == null) oneWayTicket[u] = new TreeMap<Integer, Integer>();
				oneWayTicket[u].put(v, Math.min(cost, oneWayTicket[u].getOrDefault(v, Integer.MAX_VALUE)));
			}
			
			else
			{
				if(roundTicket[u] == null) roundTicket[u] = new TreeMap<Integer, Integer>();
				roundTicket[u].put(v, Math.min(cost, roundTicket[u].getOrDefault(v, Integer.MAX_VALUE)));
			}
		}
		
		long ans = solve(path, roundTicket, oneWayTicket);
		System.out.println(ans);
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	public static long solve(int[] path, TreeMap<Integer, Integer>[] roundTicket, TreeMap<Integer, Integer>[] oneWayTicket)
	{
		long ans = 0;
		
		TreeMap<Integer, ArrayList<Trip>>[] trips = new TreeMap[cities];
		
		int u, v, index, key;
		
		for(int i = 0; i < concerts - 1; i++)
		{
			u = path[i];
			v = path[i + 1];
			
			index = Math.min(u, v);
			key = Math.max(u, v);
			
			if(trips[index] == null)
				trips[index] = new TreeMap<Integer, ArrayList<Trip>>();
			
			if(!trips[index].containsKey(key))
				trips[index].put(key, new ArrayList<Trip>());
			
			trips[index].get(key).add(new Trip(u, v, i));
		}
		
		
		for(int i = 0; i < cities; i++)
		{
			if(trips[i] != null)
			{
				for(Entry<Integer, ArrayList<Trip>> entry : trips[i].entrySet())
				{
					long[][][] cost = new long[2][2][2];
					cost[0][1][0] = oneWayTicket[i].getOrDefault(entry.getKey(), -1);
					cost[1][0][0] = oneWayTicket[entry.getKey()].getOrDefault(i, -1);
					cost[0][1][1] = roundTicket[i].getOrDefault(entry.getKey(), -1);
					cost[1][0][1] = roundTicket[entry.getKey()].getOrDefault(i, -1);
					
					HashMap<Integer, HashMap<Integer, Long>>[] dp = new HashMap[entry.getValue().size()];
					Arrays.setAll(dp, x->new HashMap<Integer, HashMap<Integer, Long>>());
					ans += dp(0, 0, 0, entry.getValue(), dp, cost);
				}
			}
		}
		
		return ans;
	}
	
	public static long dp(int index, int forward, int back, ArrayList<Trip> path, HashMap<Integer, HashMap<Integer, Long>>[] dp, long[][][] cost)
	{
		if(index >= path.size())
			return 0;
		
		int u = path.get(index).start;
		int v = path.get(index).end;
		
		if(u < v)
		{
			u = 0;
			v = 1;
		}
		
		else
		{
			u = 1;
			v = 0;
		}
		
		if(!dp[index].containsKey(forward))
			dp[index].put(forward, new HashMap<Integer, Long>());
	
		else if(dp[index].get(forward).containsKey(back))
			return dp[index].get(forward).get(back);
		
		long a = Long.MAX_VALUE;
		long b = Long.MAX_VALUE;
		long c = Long.MAX_VALUE;
		
		if((u == 0 && forward > 0))
		{
			c = dp(index + 1, forward - 1, back, path, dp, cost);
		}
		
		else if(v == 0 && back > 0)
		{
			c = dp(index + 1, forward, back - 1, path, dp, cost);
		}
		
		// only take one time pass if no return tickets are present
		else if(cost[u][v][0] != -1)
		{
			a = dp(index + 1, forward, back, path, dp, cost);
				
			if(a != Long.MAX_VALUE)
				a += cost[u][v][0];
		}
			
		if(cost[u][v][1] != -1)
		{
			int dB = u == 0 ? 1 : 0;
			int dF = v == 0 ? 1 : 0;
				
			b = dp(index + 1, forward + dF, back + dB, path, dp, cost);
			if(b != Long.MAX_VALUE)
				b += cost[u][v][1];
		}
		
		
		dp[index].get(forward).put(back, Math.min(Math.min(a, b), c));
		return dp[index].get(forward).get(back);
	}
	
	static class Trip implements Comparable<Trip>
	{
		int start, end;
		int id;
		
		public Trip(int u, int v, int id)
		{
			start = u;
			end = v;
			this.id = id;
		}

		@Override
		public int compareTo(Trip t) {
			return id - t.id;
		}
		
		public String toString()
		{
			return start + " to " + end;
		}
	}*/

}
