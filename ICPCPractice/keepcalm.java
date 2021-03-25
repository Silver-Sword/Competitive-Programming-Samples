import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class keepcalm 
{
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		char[] a = scan.readLine().toCharArray();
		char[] b = scan.readLine().toCharArray();
		
		int[] a1 = new int[Math.max(a.length, b.length)];
		int[] a2 = new int[a1.length];
		int[] b1 = new int[a1.length];
		int[] b2 = new int[a1.length];
		
		for(int i = a1.length - a.length, j = 0; j < a.length; i++, j++)
		{
			a1[i] = a2[i] = a[j] - '0';
		}
		
		for(int i = b1.length - b.length, j = 0; j < b.length; i++, j++)
		{
			b1[i] = b2[i] = b[j] - '0';
		}
		
		int[] ans1 = new int[Math.max(a1.length, b1.length) + 1];
		int[] ans2 = new int[Math.max(a.length, b.length) + 1];
		
		boolean needed = solve(a1, b1, ans1);
		if(!needed)
			out.println(0);
		
		else
		{
			solve(b2, a2, ans2);
			
			printSmaller(ans1, ans2, out);
		}
		
		out.flush();
	}

	private static boolean solve(int[] a, int[] b, int[] ans) 
	{
		for(int i = 0; i < a.length; i++)
		{
			// there is carry here
			if(a[i] + b[i] >= 10)
			{
				// if you add the second number to the first (just this digit), there has to be overflow (guaranteed)
				// you cannot take away from the second number anything less than the overflow digit
				// therefore, just add the minimum amount to the first number to cause an overflow
				// this logic seems shaky
				overflow(i, a, b, ans);
				backwardsOverflow(i, a, b, ans);
				return true;
			}
		}
		
		return false;
	}

	private static void backwardsOverflow(int index, int[] a, int[] b, int[] ans) 
	{
		while(sum(index - 1, a, b) == 9)
		{
			ans[index] += b[index-1];
			index --;
		}
		
	}

	private static int sum(int index, int[] a, int[] b) 
	{
		return index < 0 ? 0 : a[index] + b[index];
	}

	private static void overflow(int index, int[] a, int[] b, int[] ans) 
	{
		int sub = 10;
		// add to a
		for(int i = a.length - 1; i >= index; i--)
		{
			if(sub == 10 && a[i] != 0)
			{
				ans[i+1] = 10 - a[i];
				sub = 9;
			}
			
			else if(sub == 10) continue;
			
			else
				ans[i+1] = sub - a[i]; 
		}
		
		// subtract from b
		// should be guaranteed that the previous index exists to draw from
		for(int i = b.length - 1; i >= index; i--)
		{
			b[i] -= ans[i+1];
			
			if(b[i] < 0)
			{
				b[i] += 10;
				b[i-1] --;
			}
		}
		
	}

	private static void printSmaller(int[] ans1, int[] ans2, PrintWriter out) 
	{
		boolean printed = false;
		for(int i = 0; i < ans1.length; i++)
		{
			if(ans1[i] != ans2[i])
			{
				if(ans1[i] < ans2[i])
					printArray(i, ans1, printed, out);
				else
					printArray(i, ans2, printed, out);
				
				return;
			}
			
			else if(!printed && ans1[i] == 0)	// don't print leading zeros
			{
				continue;
			}
			
			else
			{
				printed = true;
				out.print(ans1[i]);
			}
		}
		
		// ans is 0
		if(!printed)
			out.print(0);
		
		out.println();
	}

	private static void printArray(int start, int[] ans, boolean printed, PrintWriter out) 
	{
		
		for(int i = start; i < ans.length; i++)
		{
			if(!printed && ans[i] == 0) continue;
			out.print(ans[i]);
			printed = true;
		}
		
		if(!printed)
			out.print(0);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
