import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Feb21_5 
{
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in;
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			n = parse(scan.readLine());
			in = scan.readLine().split(" ");
			
			
			HashMap<String, Team> map = new HashMap<String, Team>();
			int[][] trades = new int[26][26];
			
			// get words and populate hashmap
			for(int i = 0; i < n; i++)
			{
				String key = in[i].substring(1);
				
				 Team team = map.get(key);
				
				if(team == null)
					map.put(key, team = new Team());
				
				team.add(in[i].charAt(0));
			}
			
			// update trades
			for(Entry<String, Team> entry : map.entrySet())
			{
				entry.getValue().updateTrades(trades);
			}
			
			// count valid
			long count = 0;
			for(Entry<String, Team> entry : map.entrySet())
				count += entry.getValue().count(trades);
				
			out.println(count);
		}

		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Team
	{
		HashSet<Integer> first = new HashSet<Integer>();
		HashSet<Integer> trade = new HashSet<Integer>();
		
		public void add(char c)
		{
			first.add(c - 'a');
		}
		
		public void updateTrades(int[][] freq)
		{
			// update trade list
			for(int i = 0; i < 26; i++)
				if(!first.contains(i))
					trade.add(i);
			
			// can trade ith value with jth
			for(Integer offer : first)
				for(Integer accept : trade)
					freq[accept][offer]++;
		}
		
		public int count(int[][] freq)
		{
			int count = 0;
			
			for(Integer offer : first)
				for(Integer accept : trade)
					count += freq[offer][accept];
			
			return count;
		}
	}
}
