import java.util.Scanner;

public class twelve {
	static int cities;
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		cities = scan.nextInt();
		scan.nextLine();
		int[][] map = new int[cities][cities];
		
		for(int t = 0; t < cities; t++)
		{
			for(int i = 0; i < cities; i++)
				map[t][i] = scan.nextInt();
		}
		
		int n = scan.nextInt();
		for(int t = 0; t < n; t++)
		{
			int start = scan.nextInt();
			int end = scan.nextInt();
			int max = scan.nextInt();
			
			boolean[] visited = new boolean[cities];
			visited[start] = true;
			System.out.println(dfs(start, end, max, 0, map, visited));
		}
	}
	
	public static int dfs(int index, int target, int minLeft, int count, int[][] map, boolean[] visited)
	{
		if(index == target)
			return count;
		
		int max = 0;
		//try all possible combos
		for(int i = 0; i < cities; i++)
		{
			if(i != index && !visited[i])
			{
				if(minLeft >= map[index][i] && (i % 2 == 1 ? (minLeft >= map[index][i] + 134) : true))
				{
					visited[i] = true;
					max = Math.max(max, dfs(i, target, minLeft - map[index][i] - (i % 2 == 1 ? 134 : 0), count + (i%2==1 ? 1 : 0), map, visited));
					visited[i] = false;
				}
			}
		}
		
		return max;
	}

}
