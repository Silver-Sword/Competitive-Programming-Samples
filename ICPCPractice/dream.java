import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

public class dream 
{
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in;
		n = parse(scan.readLine());
		HashMap<String, Integer> event = new HashMap<String, Integer>();
		char type;
		
		ArrayDeque<Integer> real = new ArrayDeque<Integer>();
		HashSet<Integer> realSet = new HashSet<Integer>();
		HashSet<Integer> dreamSet = new HashSet<Integer>();
		int[] ordering = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			in = scan.readLine().split(" ");
			
			if((type = in[0].charAt(0)) == 'E')
			{
				addEvent(in[1], event, real, realSet, dreamSet, ordering);
			}
			
			else if(type == 'S')
			{
				int ans = story(in, event, real, realSet, dreamSet, ordering);
				
				printAns(ans, out);
			}
			
			else // type == 'D'
			{
				dreamed(parse(in[1]), real, realSet, dreamSet);
			}
		}

		out.flush();
	}

	private static void printAns(int ans, PrintWriter out) 
	{
		if(ans < 0)
			out.println("Plot Error");
		else if(ans == 0)
			out.println("Yes");
		else
			out.println(ans + " Just A Dream");
		
	}

	private static int story(String[] in, HashMap<String, Integer> event, ArrayDeque<Integer> real, HashSet<Integer> realSet, HashSet<Integer> dreamSet, int[] ordering) 
	{
		int minDidNotHappen = Integer.MAX_VALUE, maxHappened = -1 ;
		String e;
		boolean isReal;
		
		for(int i = 2; i < in.length; i++)
		{
			isReal = in[i].charAt(0) != '!';
			
			if(isReal)
				e = in[i];
			else
				e = in[i].substring(1);

			Integer id = event.get(e);
			if(id == null)
			{
				if(isReal)
					return -1;
				else
					continue;
			}
			
			if(isReal)
			{
				if(!realSet.contains(id))
					return -1;
				maxHappened = Math.max(maxHappened, ordering[id]);	// need to keep
			}
			
			else
			{
				if(realSet.contains(id))
				{
					minDidNotHappen = Math.min(minDidNotHappen, ordering[id]);
				}
			}
		}
		
		if(minDidNotHappen == Integer.MAX_VALUE)
			return 0;
		
		if(maxHappened >= minDidNotHappen)
			return -1;
		
		return real.size() - minDidNotHappen;
		
	}

	private static void dreamed(int d, ArrayDeque<Integer> real, HashSet<Integer> realSet, HashSet<Integer> dreamSet) 
	{
		int move;
		while(!real.isEmpty() && d -- > 0)
		{
			move = real.pollLast();
			realSet.remove(move);
			dreamSet.add(move);
		}
		
	}

	private static void addEvent(String s, HashMap<String, Integer> event, ArrayDeque<Integer> real, HashSet<Integer> realSet, HashSet<Integer> dreamSet, int[] ordering) 
	{
		int id = event.size();
		
		if(event.containsKey(s))
		{
			id = event.get(s);
			dreamSet.remove(id);
		}
		
		else
			event.put(s, id);
		
		ordering[id] = real.size();
		real.add(id);
		realSet.add(id);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
