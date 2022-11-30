import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;

public class a {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		
		in = scan.readLine().split(" ");
		n = parse(in[0]);
		int k = parse(in[1]);
		
		
		TreeSet<Integer>[] nodes = new TreeSet[n];
		Arrays.setAll(nodes, x->new TreeSet<Integer>());
		
		in = scan.readLine().split(" ");
		
		for(int i = 0; i < n - 1; i++)
		{
			int parent = parse(scan.readLine());
			nodes[parent].add( i );
		}
		
		Tree root = new Tree(nodes[0].size() - 1);
		
		root.setTree(0, nodes, 0);
		
		long[] val = new long[k];
		for(int i = 0; i < k; i++)
			val[i] = parse(in[i]);
		
		Arrays.sort(val);
		long ans = 0;
		for(int i = k - 1; i >= 0; i--)
			ans = root.setNextVal(val[i]);
		
		System.out.println(ans);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Tree
	{
		Tree[] child;
		boolean leaf;
		int numMatches;
		int numMatchesWithMaxSet;
		boolean isSet;
		
		public Tree(int n)
		{
			child = new Tree[n];
		}
		
		public void setTree(int index, TreeSet<Integer>[] nodes, int parent)
		{
			if(nodes[index].size() <= 1)
			{
				leaf = true;
				isSet = false;
				return;
			}
			
			child = new Tree[nodes[index].size() - 1];
			int j = 0;
			for(int i : nodes[index])
			{
				if(i == parent) continue;
				
				child[j] = new Tree(Math.max(0, nodes[i].size() - 1 ));
				child[j].setTree(i, nodes, index);
				j++;
			}
		}
		
		public long setNextVal(long val)
		{
			if(leaf)
				return 0;
			
			int index = 0;
			long num = 0;
			
			for(int i = 0; i < child.length; i++)
				if(child[i].numMatches - child[i].numMatchesWithMaxSet > num)
				{
					num = child[i].numMatches - child[i].numMatchesWithMaxSet;
					index = i;
				}
			
			numMatchesWithMaxSet -= num;
			long ans = child[index].setNextVal(val) + (isSet ? 0 : val);
			isSet = true;
			return ans;
		}
	}
}
