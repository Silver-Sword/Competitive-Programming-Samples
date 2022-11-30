import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class empytingbaltic {
	static int row, col;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		row = parse(in[0]);
		col = parse(in[1]);
		
		int[][] grid = new int[row][col];
		for(int i = 0; i < row; i++)
		{
			in = scan.readLine().split(" ");
			for(int j = 0; j < col; j++)
			{
				grid[i][j] = parse(in[j]);
				if(grid[i][j] < 0)
					grid[i][j] = Math.abs(grid[i][j]);
				else
					grid[i][j] = 0;
			}
		}
		
		in = scan.readLine().split(" ");
		long ans = bfs(parse(in[1]) - 1, parse(in[0]) - 1, grid);
		
		out.println(ans);

		out.flush();
	}

	private static long bfs(int xStart, int yStart, int[][] grid) 
	{
		PriorityQueue<Point> next = new PriorityQueue<Point>();
		boolean[][] visited = new boolean[row][col];
		long ans = 0;
		
		Point temp = new Point(yStart, xStart, grid[yStart][xStart]);
		Point toVisit;
		next.add(temp);
		visited[yStart][xStart] = true;
		ans = temp.weight;
		
		while(!next.isEmpty())
		{
			temp = next.poll();
			
			for(int dy = -1; dy <= 1; dy++)
			{
				for(int dx = -1; dx <= 1; dx++)
				{
					if(dy == 0 && dx == 0) continue;
					
					if(valid(temp.y + dy, temp.x + dx, visited, grid))
					{
						toVisit = new Point(temp.y + dy, temp.x + dx, Math.min(grid[temp.y + dy][temp.x + dx], temp.weight));
						next.add(toVisit);
						visited[toVisit.y][toVisit.x] = true;
						ans += toVisit.weight;
					}
				}
			}
		}
		
		return ans;
	}

	private static boolean valid(int i, int j, boolean[][] visited, int[][] grid) 
	{
		if(! (i >= 0 && i < row && j >= 0 && j < col) )
			return false;
		
		return (!visited[i][j] && grid[i][j] != 0);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y, weight;
		
		public Point(int i, int j, int w)
		{
			y = i;
			x = j;
			weight = w;
		}

		@Override
		public int compareTo(Point p) 
		{
			return Integer.compare(p.weight, weight);
		}
	}
}
