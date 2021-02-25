import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

public class Feb21_8_partial {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		n = parse(scan.readLine());
		Node[] tree = new Node[n];
		Arrays.setAll(tree, x-> new Node());
		
		String[] in = scan.readLine().split(" ");
		for(int i = 0; i < n-1; i++)
			tree[parse(in[i]) - 1].children.add(tree[i+1]);
		
		for(int i = 0; i < n; i++)
			tree[i].numChildren = tree[i].children.size();
		
		int q = parse(scan.readLine());
		
		while(q -- > 0)
		{
			in = scan.readLine().split(" ");
			int push = parse(in[1]);
			out.println(push - tree[parse(in[0]) - 1].propogate(push));
		}

		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Node
	{
		int numChildren = 0;
		HashMap<Long, Long> map;
		
		public Node()
		{
			map = new HashMap<Long, Long>();
		}
		
		ArrayDeque<Node> children = new ArrayDeque<Node>();
		
		public boolean isLeaf()
		{
			return children.isEmpty();
		}
		
		public long propogate(long W)
		{
			if(isLeaf())
				return W;
			
			Long ans = map.get(W);
			
			if(ans == null)
			{
				if(W % numChildren != 0)
				{
					map.put(W, 0L);
					return 0;
				}
				
				ans = 0L;
				
				long send = W / numChildren;
				for(Node child : children)
					ans += child.propogate(send);
				
				map.put(W, ans);
			}
			
			return ans;
		}
	}
}
