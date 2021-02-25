import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class Feb21_4 {
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
			Frog[] frogs = new Frog[n];
			for(int i = 0;i < n; i++)
				frogs[i] = new Frog(i);
			
			// get weights
			in = scan.readLine().split(" ");
			for(int i = 0; i < n; i++)
				frogs[i].weight = parse(in[i]);
			
			// get push
			in = scan.readLine().split(" ");
			for(int i = 0;i < n; i++)
				frogs[i].push = parse(in[i]);
			
			// put in queue
			PriorityQueue<Frog> q = new PriorityQueue<Frog>();
			for(int i = 0;i < n; i++)
				q.add(frogs[i]);
			
			out.println(solve(q));
			
		}

		out.flush();
	}
	
	public static int solve(PriorityQueue<Frog> frogs)
	{
		int pos = -1;
		int ans = 0;
		
		while(!frogs.isEmpty())
		{
			Frog frog = frogs.poll();
			
			while(frog.position <= pos)
			{
				frog.position += frog.push;
				ans ++;
			}
			
			pos = frog.position;
		}
		
		return ans;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Frog implements Comparable<Frog>
	{
		int weight, position, push;
		
		public Frog(int id)
		{
			position = id;
		}

		@Override
		public int compareTo(Frog f) 
		{
			return weight - f.weight;
		}
		
		public String toString()
		{
			return position + " is weight " + weight; 
		}
	}
}
