import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LeakedAnswers {
	
	static int n, m, k;
	static String out;
	static int maximum;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			m = scan.nextInt();
			k = scan.nextInt();
			
			out = "";
			maximum = 0;
			
			int[][] ans = new int[n][k];
			for(int i = 0; i < n ;i++)
			{
				for(int j = 0; j < k; j++)
					ans[i][j] = scan.nextInt();
				
			}
			
			int[] sum = new int[k];
			
			min(0, ans, sum, 0, "");
			System.out.println(out.trim());
		}

	}
	
	public static int min(int q, int[][] ans, int[] sum, int lastMin, String result)
	{
		
		if(q >= n)
		{
			int end = getMin(sum);
			if(end > maximum)
			{
				maximum = end;
				out = result;
			}
			
			return end;
		}
		boolean done = false;
		int max = -1;
		
		for(int i = 0; i < k; i++)
		{
			if(lastMin != sum[i])
				continue;
			
			done = true;
			
			int M = ans[q][i];
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int j = 0; j < k; j++)
			{
				if(ans[q][j] == M)
				{
					list.add(j);
					sum[j] ++;
				}
			}
			
			int temp = min(q+1, ans, sum, lastMin, result + " " + M);
			if(temp > max)
				max = temp;
			
		}
		
		
		if(!done)
		{
			return min(q, ans, sum, lastMin + 1, result);
		}
		
		return max;
	}
	
	public static int getMin(int[] ray)
	{
		int min = ray[0];
		
		for(int i = 1; i < k; i++)
		{
			if(ray[i] < min)
				min = ray[i];
		}
		
		return min;
	}

}
