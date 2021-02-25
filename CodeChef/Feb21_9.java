import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Feb21_9 
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in;
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			int n = parse(in[0]);
			int q = parse(in[1]);
			int m = parse(in[2]);
			
			//Tree tree = new Tree(0, m);
			int[] wins = new int[m+1];
			
			int[] ray = new int[n];
			in = scan.readLine().split(" ");
			for(int i = 0; i < n; i++)
				ray[i] = parse(in[i]);
			
			while(q -- > 0)
			{
				in = scan.readLine().split(" ");
			}
			
			out.println(getMax(0, wins.length - 1, wins));
		}

		out.flush();
	}

	private static int getMax(int l, int r, int[] wins) 
	{
		if(l == r)
			return wins[l];
		
		int mid = (l + r) / 2;
		
		return Math.max(getMax(l, mid, wins), getMax(mid + 1, r, wins));
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	/*static class Tree
	{
		int L, R, mid;
		Tree left, right;
		
		int lazy;
		
		public Tree(int l, int r)
		{
			L = l;
			R = r;
			mid = (L + R) / 2;
			
			if(l == r) return;
			
			left = new Tree(L, mid);
			right = new Tree(mid + 1, R);
		}
		
		public int getMax()
		{
			return Math.max(left.getMax(), right.getMax());
		}
		
		public void propogate()
		{
			left.lazy += lazy;
			right.lazy += lazy;
			lazy = 0;
		}
		
		public void rangeAdd(int l, int r)
		{
			// out of range
			if(r > L || R > l)
				return;
			
			// completely within range
			if(L >= l && R <= r)
			{
				lazy ++;
				return;
			}
			
			else
			{
				propogate();
				left.rangeAdd(l, r);
				right.rangeAdd(l, r);
			}
		}
	}*/
}
