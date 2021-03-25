import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

public class GCPC {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		int tt = parse(in[1]);
		n = parse(in[0]);
		Team[] teams = new Team[n];
		
		TreeSet<Team> lower = new TreeSet<Team>();
		TreeSet<Team> higher = new TreeSet<Team>();
		
		for(int i = 0; i < n; i++)
		{
			teams[i] = new Team(i);
			
			if(i == 0) continue;
			lower.add(teams[i]);
		}
		
		int rank = 1;
		int index, penalty;
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			index = parse(in[0]) - 1;
			penalty = parse(in[1]);
			
			// favorite team
			if(index == 0)
			{
				teams[0].solved(penalty);
				
				while(!higher.isEmpty() && compare(teams[0], higher.first()) >= 0)
				{
					lower.add(higher.pollFirst());
					rank --;
				}
			}
			
			// team is in lower
			else if(compare(teams[0], teams[index]) >= 0)
			{
				lower.remove(teams[index]);
				teams[index].solved(penalty);
				
				// team is still lower
				if(compare(teams[0], teams[index]) >= 0)
					lower.add(teams[index]);
				// team is now higher
				else
				{
					rank ++;
					higher.add(teams[index]);
				}
				
			}
			
			// team is in higher
			else
			{
				higher.remove(teams[index]);
				teams[index].solved(penalty);
				higher.add(teams[index]);
			}
			
			out.println(rank);
		}
		
		
		out.flush();
	}
	
	public static int compare(Team a, Team b)
	{
		if(a.solved == b.solved)
			return Long.compare(b.penalty, a.penalty);
		
		return Integer.compare(a.solved, b.solved);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Team implements Comparable<Team>
	{
		int id;
		long penalty;
		int solved;
		
		public Team(int i)
		{
			id = i + 1;
			penalty = 0;
			solved = 0;
		}
		
		public void solved(int p)
		{
			solved ++;
			penalty += p;
		}

		@Override
		public int compareTo(Team t) 
		{
			if(solved != t.solved) 
				return Integer.compare(solved, t.solved);
			
			if(penalty != t.penalty)
				return Long.compare(t.penalty, penalty);
			
			return id - t.id;
		}
		
		public String toString()
		{
			return "Team #" + id + ": (" + solved + " | " + penalty + ")";
		}
	}
}
