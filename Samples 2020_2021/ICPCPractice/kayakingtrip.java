import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

public class kayakingtrip {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		int[] num = new int[3];
		for(int i = 0; i < 3; i++)
			num[i] = parse(in[i]);
		
		in = scan.readLine().split(" ");
		long[] strength = new long[3];
		for(int i = 0; i < 3; i++)
			strength[i] = parse(in[i]);
		
		long[] kayak = new long[(num[0] + num[1] + num[2]) / 2];
		in = scan.readLine().split(" ");
		
		for(int i = 0; i < kayak.length; i++)
			kayak[i] = parse(in[i]);
		
		Arrays.sort(kayak);
		
		long min = findMin(num, strength, kayak);
		
		out.println(min);

		out.flush();
	}

	private static long findMin(int[] num, long[] strength, long[] kayak) 
	{
		long[][] combos = getCombos(strength);
		long lo = 1, hi = (long) combos[combos.length-1][3] * kayak[0];
		long mid;
		long ans = 0;
		
		while(lo <= hi)
		{
			mid = (lo + hi) / 2;
			
			if(canDo(mid, combos, num, kayak))
			{
				ans = Math.max(ans, mid);
				lo = mid + 1;
			}
			
			else
			{
				hi = mid - 1;
			}
		}
		
		return ans;
	}

	private static boolean canDo(long target, long[][] combos, int[] numOrig, long[] kayak) 
	{
		boolean found;
		int[] num = Arrays.copyOf(numOrig, numOrig.length);
		for(int i = 0; i < kayak.length; i++)
		{
			found = false;
			for(int j = 0; j < combos.length; j++)
			{
				if(valid(target, combos[j], num, kayak[i]))
				{
					for(int k = 0; k < 3; k++)
						num[k] -= combos[j][k];
					
					found = true;
					break;
				}
			}
			
			if(!found)
				return false;
		}
		
		return true;
	}

	private static boolean valid(long target, long[] combo, int[] num, long kayak) 
	{
		for(int i = 0; i < 3; i++)
			if(num[i] < combo[i])
				return false;
		
		return (kayak * combo[3] >= target);
	}

	private static long[][] getCombos(long[] strength) 
	{
		long[][] combos = new long[6][4];
		int index = 0;
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = i; j < 3; j++)
			{
				combos[index][3] = strength[i] + strength[j];
				combos[index][i] ++;
				combos[index][j] ++;
				
				index++;
			}
		}
		Arrays.sort(combos, (a, b) -> Long.compare(a[3], b[3]));
		return combos;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
