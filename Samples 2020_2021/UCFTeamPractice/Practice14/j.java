import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class j 
{
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		/*for(int i = 1; i < 6_000; i++)
			System.out.print(i + " ");
		System.out.println();
		System.exit(0);*/
				
		String[] in;
		n = parse(scan.readLine());
		
		int[] order = new int[n];
		in = scan.readLine().split(" ");
		
		// get the ordering (array 1)
		for(int i = 0; i < n; i++)
			order[parse(in[i]) - 1] = i;
		
		int[] place = new int[n];
		//int num;
		in = scan.readLine().split(" ");
		// get the placement ordering
		for(int i = 0; i < n; i++)
		{
			//num = order[parse(in[i]) - 1];
			place[parse(in[i]) - 1] = i;
		}
		
		long count = 0;
		in = scan.readLine().split(" ");
		// get the index
		int[] index = new int[n];
		for(int i = 0;i < n; i++)
		{
			//num = order[parse(in[i]) - 1];
			index[parse(in[i]) - 1] = i;
		}
		
		Tree tree = new Tree(0, n - 1);
		
		for(int i = 0; i < n; i++)
		{
			count += tree.query(Math.min(order[place[i]], index[place[i]]));
			tree.add(Math.max(index[place[i]], order[place[i]]), n - 1);
		}
		
		out.println(count);
		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Tree
	{
		int lazy;
		
		int L, R, mid;
		
		Tree left, right;
		
		public Tree(int l, int r)
		{
			lazy = 0;
			
			L = l;
			R = r;
			mid = (L + R) / 2;
			
			if(l == r) return;
			
			left = new Tree(L, mid);
			right = new Tree(mid + 1, R);
		}
		
		public int query(int index)
		{
			// out of range
			if(L > index || index > R)
				return 0;
			
			// found the bottom node
			if(L == index && R == index)
				return lazy;
			
			// in range
			else
			{
				return left.query(index) + right.query(index) + lazy;
			}
		}
		
		public void add(int l, int r)
		{
			// out of range
			if(r < L || l > R)
				return;
			
			// encapsulated
			else if(L >= l && R <= r)
			{
				lazy ++;
			}
			
			// the correct spot is within a subtree
			else
			{
				propogate();
				left.add(l, r);
				right.add(l, r);
			}
		}
		
		public void propogate()
		{
			left.lazy += lazy;
			right.lazy += lazy;
			lazy = 0;
		}
	}
}
