import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class uddered 
{
	static int n;
	static int ALPHA = 26;
	
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

		char[] in = scan.readLine().toCharArray();
		n = in.length;
		int[] input = new int[n];

		int[] map = new int[ALPHA];
		int[] freq = new int[ALPHA];
		int index = 0;
		
		for(int i = 0; i < n; i++)
		{
			input[i] = in[i] - 'a';
			if(freq[input[i]] ++ <= 0)
			{
				map[input[i]] = index;
				index++;
			}
		}
		
		solve(0, new int[index], new boolean[index], map, input);
		
		System.out.println(min+1);
	}

	private static void solve(int index, int[] key, boolean[] used, int[] map, int[] input) 
	{
		if(index >= used.length)
		{
			getValue(key, map, input);
		}
		
		if(index == 0)
		{
			used[0] = true;
			solve(index + 1, key, used, map, input);
			return;
		}
		
		for(int i = 0; i < used.length; i++)
		{
			if(used[i]) continue;
			
			used[i] = true;
			
			key[index] = i;
			solve(index + 1, key, used, map, input);
			
			used[i] = false;
		}
		
	}

	private static void getValue(int[] key, int[] map, int[] input) 
	{
		int count = 0;
		for(int i = 1; i < n; i++)
		{
			if(key[map[input[i]]] <= key[map[input[i-1]]])
			{
				count++;
				
				if(count > min)
					return;
			}
		}
		
		if(count < min)
			min = count;
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
