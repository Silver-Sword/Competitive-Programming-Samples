import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class c {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		int tt = parse(in[1]);
		char[] word = new char[tt + n];
		boolean[] flag = new boolean[tt + n];
		int[] dp = new int[tt + n];
		Arrays.fill(dp, -1);
		int len = n;
		int total = 0;
		char[] input = scan.readLine().toCharArray();
		
		for(int i = 0; i < len; i++)
		{
			word[i] = input[i];
		}
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");

			if(parse(in[0]) == 1)
			{
				word[len] = in[1].charAt(0);
				len ++;
			}
			
			else if(parse(in[0]) == 2)
			{
				if(!flag[len - 1])
					total ++;
				flag[len - 1] = true;
			}
			
			else
			{
				out.println(solve(word, flag, len, total, dp));
			}
		}
		
		out.flush();
	}

	private static int solve(char[] word, boolean[] flag, int len, int total, int[] dp) 
	{
		if(total == 0)
			return total;
		if(dp[len] != -1)
			return dp[len];
		
		int count = 0;
		
		int left = 0, right = len - 1;
		
		while(right >= left && word[left] == word[right])
		{
			if(flag[left])
				count++;
			
			left ++;
			right--;
		}
		
		if(left > right)
		{
			dp[len] = total;
			return total;
		}
		
		dp[len] = count;
		return count;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
}
