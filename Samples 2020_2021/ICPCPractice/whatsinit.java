import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class whatsinit {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		
		n = parse(scan.readLine());
		
		String[] key = new String[n];
		int[] min = new int[n];
		int[] max = new int[n];
		boolean[] known = new boolean[n];
		
		for(int i = 0; i < n; i++)
		{
			in = scan.readLine().split(" ");
			key[i] = in[0];
			if(in[1].charAt(0) == '?')
			{
				known[i] = false;
			}
			
			else
			{
				known[i] = true;
				min[i] = (max[i] = parse(in[1]));
			}
			
		}
		
		int maxSum = 0, minSum = 0;
		for(int i = 0; i < n; i++)
		{
			if(!known[i])
			{
				if(i == 0)
					max[i] = 100;
				else
					max[i] = max[i-1];
			}
			
			maxSum += max[i];
		}
		
		for(int i = n-1; i>= 0; i--)
		{
			if(!known[i])
			{
				if(i == n-1)
					min[i] = 0;
				else
					min[i] = min[i+1];
			}
			
			minSum += min[i];
		}
		
		boolean updated = true;
		while(updated)
		{
			updated = false;
			updated = updateMax(max, minSum, known, min);
			maxSum = getSum(max);
			
			updated |= updateMin(min, maxSum, known, max);
			minSum = getSum(min);
			
		}
		
		
		for(int i = 0; i < n; i++)
		{
			out.println(key[i] + " " + min[i] + " " + max[i]);
		}
		
		out.flush();
	}

	private static boolean updateMin(int[] min, int maxSum, boolean[] known, int[] max) 
	{
		boolean changed = false;
		
		for(int i = 0; i < n; i++)
		{
			if(known[i]) continue;
			if(i != n - 1 && min[i+1] > min[i])
			{
				min[i] = min[i+1];
				changed = true;
			}
			
			int temp = 100 - (maxSum - max[i]);
			
			if(temp >= min[i] && temp <= max[i] && temp != min[i])
			{
				min[i] = temp;
				changed = true;
			}
		}
		
		return changed;
	}

	private static boolean updateMax(int[] max, int minSum, boolean[] known, int[] min) 
	{
		boolean changed = false;
		
		for(int i = 0; i < n; i++)
		{
			if(known[i]) continue;
			if(i != 0 && max[i-1] < max[i])
			{
				max[i] = max[i-1];
				changed = true;
			}
			
			int temp = 100 - (minSum - min[i]);
			
			if(temp >= min[i] && temp <= max[i] && temp != max[i])
			{
				max[i] = temp;
				changed = true;
			}
		}
		
		return changed;
	}

	private static int getSum(int[] ray) 
	{
		int total = 0;
		for(int i : ray)
			total += i;
		return total;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
