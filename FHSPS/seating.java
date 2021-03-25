import java.util.Scanner;

public class seating {
	static int[] ans = new int[10];
	static int n;
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			
			if(n == 0 || n == 1)
				System.out.println(0);
			
			else if(ans[n] != 0)
				System.out.println(ans[n]);
			
			else
			{
				ans[n] = recur(0, new boolean[2*n]);
				System.out.println(ans[n]);
			}
		}
	}
	
	public static int recur(int id, boolean[] visited)
	{
		if(id >= n)
			return 1;
		
		//pick the first available & then every one after that
		int first = get(visited);
		int count = 0;
		
		visited[first] = true;
		
		for(int i = first+2; i < 2 * n; i++)
		{
			if(!visited[i])
			{
				visited[i] = true;
				count += recur(id+1, visited);
				visited[i] = false;
			}
		}
		
		visited[first] = false;
		return count;
	}
	
	public static int get(boolean[] visited)
	{
		for(int i = 0; i < 2 * n; i++)
			if(!visited[i])
				return i;
		return -1;
	}
}
