import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class dancemooves_gold 
{
	static int n;
	static long k;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		k = parse(in[1]);
		long min = Long.parseLong(in[2]);
		
		// single cycles
		HashSet<Integer>[] visits = new HashSet[n];
		Arrays.setAll(visits, x->new HashSet<Integer>());
		LinkedList<Position>[] firstVisit = new LinkedList[n];
		int[] end = new int[n];
		int[] location = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			location[i] = i;
			visits[i].add(i);
			firstVisit[i] = new LinkedList<Position>();
			firstVisit[i].add(new Position(i, 0));
		}
		
		for(int i = 0; i < k; i++)
		{
			in = scan.readLine().split(" ");
			move(parse(in[0]) - 1, parse(in[1]) - 1, visits, firstVisit, location, i+1);
		}
		
		// end cycle, record
		for(int i = 0; i < n; i++)
			end[location[i]] = i;
		
		// compressed cycles
		int[] unique = new int[n];
		solve(min, visits, firstVisit, end, unique);
		
		// print answer
		for(int i = 0; i < n; i++)
			out.println(unique[i]);

		out.flush();
	}
	
	private static void move(int i, int j, HashSet<Integer>[] visited, LinkedList<Position>[] firstVisit, int[] location, long m) 
	{
		int a = location[i];
		int b = location[j];
		
		if(visited[a].add(j))
			firstVisit[a].add(new Position(j, m));
		
		if(visited[b].add(i))
			firstVisit[b].add(new Position(i, m));
		
		location[i] = b;
		location[j] = a;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	private static void solve(long min, HashSet<Integer>[] visits, LinkedList<Position>[] firstVisit, int[] end, int[] unique) 
	{
		boolean[] visited = new boolean[n];
		LinkedList<Integer> list;
		
		// for each cycle
		for(int i = 0; i < n; i++)
		{
			// skip if already seen
			if(visited[i]) continue;
			
			list = dfs(i, new LinkedList<Integer>(), end, visited);
			
			solveCycle(list, min, visits, firstVisit, unique);
		}
		
	}
	
	private static LinkedList<Integer> dfs(int index, LinkedList<Integer> list, int[] end, boolean[] visited) 
	{
		list.add(index);
		visited[index] = true;
		
		while(end[list.peekLast()] != index)
		{
			list.add(end[list.peekLast()]);
			visited[list.peekLast()] = true;
		}
		
		return list;
	}
	
	private static void solveCycle(LinkedList<Integer> list, long min, HashSet<Integer>[] visits, LinkedList<Position>[] firstVisit, int[] unique) 
	{
		// whole cycle traversed
		if(min >= (k * list.size()))
		{
			int ans = combineHashSets(list, visits); 
			for(int i : list)
				unique[i] = ans;
			return;
		}
		
		// just use the firstVisit list (less than one cycle)
		else if(k >= min)
		{
			// just do each position separately
			for(int i : list)
			{
				unique[i] = getCyclePart(min, firstVisit[i]);
			}
		}
		
		// whole compressed cycle not traversed
		else
		{
			int[] ls = new int[list.size()];
			int count = 0;
			for(int i : list)
			{
				ls[count] = i;
				count++;
			}
			
			stackMethod(min, ls, visits, firstVisit, unique);
		}
	}


	private static int getCyclePart(long min, LinkedList<Position> firstVisit) 
	{
		int count = 0;
		
		for(Position p : firstVisit)
		{
			if(p.minuteOccupy > min) break;
			
			count++;
		}
		
		return count;
	}

	private static void stackMethod(long min, int[] ls, HashSet<Integer>[] visits, LinkedList<Position>[] firstVisit, int[] unique) 
	{
		int[] count = new int[n];
		HashSet<Integer> set = new HashSet<Integer>();
		
		int full = (int) (min / k);
		
		for(int i = 0; i < full; i++)
		{
			for(int visit : visits[ls[i]])
			{
				count[visit] ++;
				set.add(visit);
			}
		}
		
		min -= (full * k);
		
		int toRemove = 0, toAdd = (int) full;
		unique[ls[0]] = set.size() + count(count, firstVisit[ls[toAdd]], min);
		
		for(int i = 1; i < ls.length; i++)
		{
			remove(count, set, visits[ls[toRemove]]);
			add(count, set, visits[ls[toAdd]]);
			
			toRemove++;
			toAdd = (toAdd + 1) % ls.length;
			
			unique[ls[i]] = set.size() + count(count, firstVisit[ls[toAdd]], min);
		}
		
	}

	private static void add(int[] count, HashSet<Integer> set, HashSet<Integer> visits) 
	{
		for(int i : visits)
		{
			count[i] ++;
			
			if(count[i] == 1)
				set.add(i);
		}
		
	}

	private static void remove(int[] count, HashSet<Integer> set, HashSet<Integer> visits) 
	{
		for(int i : visits)
		{
			count[i] --;
			
			if(count[i] <= 0)
				set.remove(i);
		}
		
	}

	private static int count(int[] count, LinkedList<Position> firstVisit, long min) 
	{
		int ans = 0;
		
		for(Position p : firstVisit)
		{
			if(p.minuteOccupy > min)
				return ans;
			
			if(count[p.position] <= 0)
				ans++;
		}
		
		return ans;
	}

	private static int combineHashSets(LinkedList<Integer> list, HashSet<Integer>[] visits) 
	{
		HashSet<Integer> cycle = new HashSet<Integer>();
		for(int i : list)
		{
			for(int val : visits[i])
				cycle.add(val);
		}
		
		return cycle.size();
	}
	
	static class Position
	{
		int position;
		long minuteOccupy;
		
		public Position(int p, long m)
		{
			position = p;
			minuteOccupy = m;
		}
		
		public String toString()
		{
			return "Position " + position + " at minute " + minuteOccupy;
		}
	}
}
