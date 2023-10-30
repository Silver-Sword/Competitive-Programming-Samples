import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class E_Carrots {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		
		n = parse(in[0]);
		int k = parse(in[1]);
		
		PriorityQueue<Carrot> set = new PriorityQueue<Carrot>();
		long ans = 0;
		in = scan.readLine().split(" ");
		int val;
		for(int i = 0; i < n; i++)
		{
			val = parse(in[i]);
			if(val == 1)
			{
				k--;
				ans ++;
				continue;
			}
			set.add( new Carrot(parse(in[i]), i) );
		}
		
		Carrot temp;
		k -= set.size();
		
		while(k > 0)
		{
			temp = set.poll();
				
			temp.cut();
			k--;
			set.add(temp);
		}
	
		for(Carrot c : set)
			ans += c.time();
		
		System.out.println(ans);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Carrot implements Comparable<Carrot>
	{
		long maxLen, length;
		long numCarrots;
		int id;
		
		public Carrot(int size, int i)
		{
			length = size;
			maxLen = size;
			numCarrots = 1;
			id = i;
		}
		
		public long cut()
		{
			numCarrots ++;
			maxLen = length / numCarrots + (length % numCarrots == 0 ? 0 : 1);
			return 1;
		}
		
		public long time()
		{
			long remain = length % numCarrots;
			long len = length / numCarrots;
			
			return remain * (len + 1)  * (len + 1) + (numCarrots - remain) * len * len;
		}
		
		public long delta()
		{
			if(length == 1 || numCarrots == length) return 0;
			
			long remain = length % (numCarrots + 1);
			long len = length / (numCarrots + 1);
			
			return time() - (remain * (len + 1) * (len + 1) + ((numCarrots+1) - remain) * len * len);
		}

		@Override
		public int compareTo(Carrot c) {
			long delta = delta();
			long cDelta = c.delta();
			
			if(delta == cDelta)
				return id - c.id;
			
			return Long.compare(cDelta, delta);
		}
		
		public String toString()
		{
			return length + " split into " + numCarrots + " carrots"; 
		}
		
	}
}
