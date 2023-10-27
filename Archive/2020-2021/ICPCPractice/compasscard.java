import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class compasscard {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		n = Integer.parseInt(scan.readLine());
		
		Card[] cards = new Card[n];
		
		for(int t = 0; t < n; t++)
		{
			in = scan.readLine().split(" ");
			cards[t] = new Card(in);			
		}
		
		TreeSet<Card>[] list = new TreeSet[3];
		Compare[] comp = new Compare[3];
		
		for(int i = 0; i < 3; i++)
		{
			comp[i] = new Compare();
			comp[i].c = i;
			list[i] = new TreeSet<Card>(comp[i]);
			
			for(int j = 0; j < n; j++)
				list[i].add(cards[j]);
		}
		
		PriorityQueue<Card> queue = new PriorityQueue<Card>();
		for(int i = 0; i < n; i++)
		{
			cards[i].calc(list);
			queue.add(cards[i]);
		}
		
		Card next;
		for(int i = 0; i < n-2; i++)
		{
			next = queue.poll();
			out.println(next.id);
			next.remove(queue, list);
		}
		
		while(!queue.isEmpty())
		{
			out.println(queue.poll().id);
		}

		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Card implements Comparable<Card>
	{
		int id;
		int[] angle = new int[3];
		long[] uni = new long[3];
		long unique;
		
		long[] before = new long[3];
		long[] after = new long[3];
		
		HashSet<Card>[] adj = new HashSet[3];
		
		public Card(String[] in)
		{
			for(int i = 0; i < 3; i++)
				angle[i] = parse(in[i]);
			
			id = parse(in[3]);
			
			unique = 0;
		}
		
		public void remove(PriorityQueue<Card> queue, TreeSet<Card>[] cards)
		{
			for(int i = 0; i < 3; i++)
			{
				cards[i].remove(this);
				
				for(Card c : adj[i])
				{
					queue.remove(c);
					
					c.recalc(i, cards);
						
					queue.add(c);
				}
			}
		}
		
		public void calc(TreeSet<Card>[] cards)
		{
			unique = 0;
			for(int i = 0; i < 3; i++)
				recalc(i, cards);
		}
		
		public void recalc(int i, TreeSet<Card>[] cards)
		{
			unique -= uni[i];
			uni[i] = 0;
			adj[i] = new HashSet<Card>();
			Card be, aft;
			
			// get before
			be = cards[i].lower(this);
			if(be == null)
				be = cards[i].last();
				
			// get after
			aft = cards[i].higher(this);
			if(aft == null)
				aft = cards[i].first();
				
			adj[i].add(be);
			adj[i].add(aft);
				
			if(be.angle[i] == angle[i] || aft.angle[i] == angle[i])
				return;
				
			// calc before
			if(be.angle[i] > angle[i])
			{
				uni[i] += (360 - be.angle[i]) + angle[i];
			}
			
			else
				uni[i] += angle[i] - be.angle[i];
				
			
			// calc after
			if(angle[i] > aft.angle[i])
			{
				uni[i] += (360 - angle[i]) + aft.angle[i];
			}
			else
				uni[i] += aft.angle[i] - angle[i];
				
			
			unique += uni[i];
			
		}

		@Override
		public int compareTo(Card c)
		{
			if(unique == c.unique)
				return Integer.compare(c.id, id);
			
			return Long.compare(unique, c.unique);
		}
		
		public String toString()
		{
			return "(ID: " + id + " | Score: " + unique + ")";
		}
	}
	
	static class Compare implements Comparator<Card>
	{
		int c;
		
		@Override
		public int compare(Card c0, Card c1) 
		{
			if(c0.angle[c] != c1.angle[c])
				return c0.angle[c] - c1.angle[c];
			
			return c0.id - c1.id;
		}
		
	}
}
