import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class zigzag2 {
	static int n;
	static long MOD = (long) 1e9 + 7;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			n = Integer.parseInt(scan.readLine());
			in = scan.readLine().split(" ");
			
			long[] ray = new long[n];
			TreeSet<Long> values = new TreeSet<Long>();
			for(int i = 0; i < n; i++)
			{
				ray[i] = parse(in[i]);
				values.add(ray[i]);
			}
			
			HashMap<Long, Integer> map = new HashMap<Long, Integer>();
			int rel = 0;
			for(Long l : values)
			{
				map.put(l, rel);
				rel++;
			}
			
			int m = values.size();
			BIT bigger = new BIT(m);
			BIT smaller = new BIT(m);
			long[] seen = new long[m];
			long count = 0, temp1, temp2;
			int val;
			
			//for n-1
			val = map.get(ray[n-1]);
			
			bigger.update(val, 1);
			smaller.update(val, 1);
			
			seen[val] ++;
			
			for(int i = n - 2; i >= 0; i--)
			{
				val = map.get(ray[i]);
				
				temp1 = smaller.query(0, val - 1) % MOD;
				temp2 = bigger.query(val + 1, m - 1) % MOD;
				
				bigger.update(val, temp1 + 1);
				smaller.update(val, temp2 + 1);
				
				count = ( (count + temp1 + temp2) - (n - i - 1 - seen[val]) ) % MOD;
				seen[val] ++;
			}
			
			System.out.println((count+MOD) % MOD);
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class BIT
	{
		long[] bucket;
		int max;
		
		public BIT(int n)
		{
			bucket = new long[n+1];
			max = n + 1;
		}
		
		public long sum(int index)
		{
			long ans = bucket[++index];
			while(index > 0)
				ans += bucket[index ^= (index & -index)];
			
			return ans;
		}
		
		public void update(int index, long val)
		{
			index ++;
			while(index < max)
			{
				bucket[index] += val;
				index += (index & -index);
			}
		}
		
		public long query(int start, int end)
		{
			if(start == 0)
				return sum(end);
			
			return sum(end) - sum(start - 1);
		}
		
		public String toString()
		{
			return Arrays.toString(bucket);
		}
	}
}
