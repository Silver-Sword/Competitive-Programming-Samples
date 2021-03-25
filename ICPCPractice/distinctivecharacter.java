import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class distinctivecharacter {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		int k = parse(in[1]);
		int[] ray = new int[n];
		for(int i = 0; i < n; i++)
			ray[i] = Integer.parseInt(scan.readLine(), 2);
		
		int ans = getResult(k, ray);
		String res = Integer.toBinaryString(ans);
		
		for(int i = k - res.length() - 1; i >= 0; i--)
			out.print("0");
		
		out.println(res);
		out.flush();
	}

	private static int getResult(int k, int[] ray) 
	{
		int max = 1 << k;
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int[] minDist = new int[max];
		Arrays.fill(minDist, -1);
		int ans = 0;
		
		for(int i = 0; i < ray.length; i++)
		{
			minDist[ray[i]] = 0;
			queue.add(ray[i]);
		}
		
		int next, flipped;
		while(!queue.isEmpty())
		{
			next = queue.pop();
			ans = next;
			
			// try flipping all digits
			for(int i = 1; i < max; i = i << 1)
			{
				flipped = next ^ i;
				
				if(minDist[flipped] != -1)
					continue;
				
				queue.add(flipped);
				minDist[flipped] = minDist[next] + 1;
			}
		}
		
		return ans;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Ans implements Comparable<Ans>
	{
		int num;
		int index;
		int minDist;
		
		public Ans(int num, int bitCount)
		{
			this.num = num;
			minDist = bitCount;
			index = 1;
		}

		@Override
		public int compareTo(Ans a) 
		{
			if(minDist == a.minDist)
				return Integer.compare(a.index, index);
			return Integer.compare(a.minDist, minDist);
		}
	}
}
