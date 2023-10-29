import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class collatzconjecture {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		n = parse(scan.readLine());
		String[] in = scan.readLine().split(" ");
		ArrayList<Long> nums = new ArrayList<Long>();
		HashSet<Long> values = new HashSet<Long>();
		
		long prev = Long.parseLong(in[0]);
		long temp;
		nums.add(prev);
		values.add(prev);
		for(int i = 1; i < n; i++)
		{
			temp = Long.parseLong(in[i]);
			if(temp == prev) continue;
			
			nums.add(temp);
			prev = temp;
			values.add(prev);
		}
		
		long[] gcd = new long[nums.size()];
		Arrays.fill(gcd, 1);
		long current = nums.get(0);
		int index = 1;
		int size = nums.size();
		
		while(current > 1 && index < size)
		{
			current = getGCD(current, nums.get(index));
			gcd[index] = current;
			values.add(gcd[index]);
			
			index++;
		}
		
		goThrough(gcd, values, nums, size);
		System.out.println(values.size());
		
	}

	private static void goThrough(long[] gcd, HashSet<Long> values, ArrayList<Long> nums, int size) 
	{
		int index;
		for(int start = 1; start < size - 1; start++)
		{
			long missing = nums.get(start) / gcd[start];
			index = start + 1;
			while(index < size && missing > 1)
			{
				missing = gcd(nums.get(index), missing);
				gcd[index] *= missing;
				values.add(gcd[index]);
				
				index++;
			}
			
		}
		
	}

	private static long getGCD(long a, long b) 
	{
		if(a > b)
			return gcd(a, b);
		else
			return gcd(b, a);
	}

	private static long gcd(long a, long b) 
	{
		return (a == b || b == 0) ? a : gcd(b, a%b);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
}
