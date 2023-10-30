import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class D_Round658 {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			n = Integer.parseInt(scan.readLine());
			int[] ray = new int[n * 2];
			String[] in = scan.readLine().split(" ");
			for(int i = 0; i < n * 2; i++)
				ray[i] = parse(in[i]);
			
			ArrayList<Integer> groups = new ArrayList<Integer>();
			int max = ray[0];
			
			int group = 1;
			for(int i = 1; i < n * 2; i++)
			{
				if(ray[i] > max)
				{
					groups.add(group);
					max = ray[i];
					group = 1;
				}
				
				else
				{
					group++;
				}
			}
			
			if(group!= 0)
			{
				groups.add(group);
			}
			
			//needs to sum up to n
			Collections.sort(groups);
			boolean pos = possible(groups);
			System.out.println(pos ? "YES" : "NO");
		}
	}

	public static boolean possible(ArrayList<Integer> groups) 
	{
		int[][] dp = new int[groups.size()][n + 1];

		return recur(groups, dp, n, 0, groups.size());
	}

	private static boolean recur(ArrayList<Integer> groups, int[][] dp, int target, int index, int size) 
	{
		if(target == 0)
			return true;
		
		if(target < 0 || index >= size)
			return false;
		
		if(dp[index][target] != 0)
		{
			return dp[index][target] == 1;
		}
		
		boolean temp = recur(groups, dp, target - groups.get(index), index+1, size) || recur(groups, dp, target, index+1, size);
		dp[index][target] = temp ? 1 : 2;
		return temp;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
