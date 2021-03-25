import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Parenting {

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		
		for(int t = 1; t <= tt; t++)
		{
			int n = scan.nextInt();
			
			Time[] times = new Time[n];
			for(int i = 0; i < n ;i++)
			{
				times[i] = new Time(scan.nextInt(), scan.nextInt(), i);
			}
			
			Arrays.sort(times);
			
			String[] ans = new String[n];
			String temp = recur(0, times, -1, -1, n, ans);
			
			System.out.print("Case #" + t + ": ");
			if(temp == null)
				System.out.print("IMPOSSIBLE");
			else
				for(int i = 0; i < n; i++)
					System.out.print(ans[i]);
			
			System.out.println();
			
			
		}

	}
	
	public static String recur(int i, Time[] time, int c, int j, int n, String[] ans)
	{
		if(i >= n)
			return "";
		
		String temp;
		
		if(c == -1)
		{
			temp = recur(i+1, time, i, j, n, ans);
			if(temp == null)
				return null;
			else
			{
				ans[time[i].index] = "C";
				return "C" + temp;
			}
		}
		
		else if(j == -1)
		{
			temp = recur(i+1, time, c, i, n, ans);
			if(temp == null)
				return null;

			ans[time[i].index] = "J";
			return "";
			
		}
		
		if(time[i].start >= time[c].end)
		{
			temp = recur(i+1, time, i, j, n, ans);
			
			if(temp == null)
				return null;
			
			ans[time[i].index] = "C";
			return "";
			
		}
		else if(time[i].start >= time[j].end)
		{
			temp = recur(i+1, time, c, i, n, ans);
			if(temp == null)
				return null;
			
			ans[time[i].index] = "J";
			return "";
			
		}
		else
			return null;
		
	}
	
	static class Time implements Comparable<Time>
	{
		int start, end, index;
		String assign;
		
		public Time(int a, int b, int i)
		{
			start = a;
			end = b;
			
			index = i;
		}
		
		@Override
		public int compareTo(Time t2) {
			if(t2.start == start)
				return end - t2.end;
			
			else
				return start - t2.start;
			
		}
		
	}

}
