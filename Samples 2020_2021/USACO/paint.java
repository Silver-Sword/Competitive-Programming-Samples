import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public class paint 
{
	static int n;
	static int q;
	public static void main(String[] args) throws IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		String[] in = scan.readLine().split(" ");
		n = Integer.parseInt(in[0]);
		q = Integer.parseInt(in[1]);
		
		char[] ray = scan.readLine().toCharArray();
		
		int[] start = new int[n];
		int[] end = new int[n];
		
		fillForward(ray, end);
		fillReverse(ray, start);
		
		int s, e;
		int ans;
		while(q-- > 0)
		{
			in = scan.readLine().split(" ");
			s = Integer.parseInt(in[0]) - 1;
			e = Integer.parseInt(in[1]) - 1;
			
			ans = 0;
			if(s != 0)
				ans += end[s - 1];
			
			if(e != n - 1)
				ans += start[e + 1];
			
			out.println(ans);
 		}
		
		out.flush();
	}
	
	private static void fillReverse(char[] ray, int[] start) 
	{
		LinkedList<Character> stack = new LinkedList<Character>();
		int current = 0;
		
		for(int i = n - 1; i >= 0; i--)
		{
			while(!stack.isEmpty() && stack.peek() > ray[i])
			{
				stack.pop();
				current ++;
			}
			
			if(stack.isEmpty() || stack.peek() != ray[i])
				stack.push(ray[i]);
			
			start[i] = current + stack.size();
		}
	}

	private static void fillForward(char[] ray, int[] end) 
	{
		LinkedList<Character> stack = new LinkedList<Character>();
		int current = 0;
		
		for(int i = 0; i < n; i++)
		{
			// update stack
			while(!stack.isEmpty() && stack.peek() > ray[i])
			{
				stack.pop();
				current ++;
			}
			
			if(stack.isEmpty() || stack.peek() != ray[i])
				stack.addFirst(ray[i]);
			
			// update total
			end[i] = current + stack.size();
		}
		
	}
}
