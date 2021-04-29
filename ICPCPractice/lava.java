import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;

public class lava {
	static int ELSA, FATHER;
	static int row, col;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		ELSA = parse(in[0]);
		FATHER = parse(in[1]);
		
		in = scan.readLine().split(" ");
		row = parse(in[0]);
		col = parse(in[1]);
		
		char[][] map = new char[row][col];
		for(int i = 0; i < row; i++)
			map[i] = scan.readLine().toCharArray();
		
		int[][] elsaSteps = new int[row][col];
		for(int i = 0; i < row ;i++)
			Arrays.fill(elsaSteps[i], Integer.MAX_VALUE);
		
		int[][] dadSteps = new int[row][col];
		for(int i = 0; i < row; i++)
			Arrays.fill(dadSteps[i], Integer.MAX_VALUE);
		
		Point start = null, end = null;
		boolean foundOne = false;
		for(int i = 0; i < row; i++)
			for(int j = 0; j < col; j++)
			{
				if(map[i][j] == 'S')
				{
					start = new Point(i, j);
					if(foundOne) break;
					foundOne = true;
				}
				
				else if(map[i][j] == 'G')
				{
					end = new Point(i, j);
					if(foundOne) break;
					foundOne = true;
				}
			}
		
		dadbfs(start, end, map, dadSteps);
		elsabfs(start, end, map, elsaSteps);
		
		if(elsaSteps[end.y][end.x] == Integer.MAX_VALUE && dadSteps[end.y][end.x] == Integer.MAX_VALUE)
			out.println("NO WAY");
		
		else if(dadSteps[end.y][end.x] == elsaSteps[end.y][end.x] )
			out.println("SUCCESS");
		else if(dadSteps[end.y][end.x] < elsaSteps[end.y][end.x])
			out.println("NO CHANCE");
		else
			out.println("GO FOR IT");
		
		out.flush();
	}

	private static void elsabfs(Point start, Point end, char[][] map, int[][] path) 
	{
		int[] dy = {1, 1, -1, -1};
		int[] dx = {1, -1, -1, 1};
		
		ArrayDeque<Point> toVisit = new ArrayDeque<Point>();
		boolean[][] used = new boolean[row][col];
		
		used[start.y][start.x] = true;
		path[start.y][start.x] = 0;
		toVisit.add(start);
		
		Point p;
		Point temp;
		while(!toVisit.isEmpty())
		{
			p = toVisit.pop();
			
			for(int d = 0; d < 4; d++)
			{
				for(int lx = 0; lx <= ELSA; lx++)
				{
					for(int ly = 0; ly <= ELSA; ly++)
					{
						if(!validLen(lx, ly)) break;
						temp = new Point(p.y + ( dy[d] * lx), p.x + (dx[d] * ly) );
						if(isValid(temp, map) && !used[temp.y][temp.x])
						{
							used[temp.y][temp.x] = true;
							path[temp.y][temp.x] = path[p.y][p.x] + 1;
							toVisit.add(temp);
							
							if(temp.y == end.y && temp.x == end.x) return;
						}
					}
					
				}
			}
		}
	}

	private static boolean validLen(int lx, int ly) 
	{
		return lx * lx + ly * ly <= ELSA * ELSA;
	}

	private static void dadbfs(Point start, Point end, char[][] map, int[][] path) 
	{
		int[] dy = {0, 0, -1, 1};
		int[] dx = {1, -1, 0, 0};
		
		ArrayDeque<Point> toVisit = new ArrayDeque<Point>();
		boolean[][] used = new boolean[row][col];
		
		used[start.y][start.x] = true;
		path[start.y][start.x] = 0;
		toVisit.add(start);
		
		Point p;
		Point temp;
		while(!toVisit.isEmpty())
		{
			p = toVisit.pop();
			
			for(int d = 0; d < 4; d++)
			{
				for(int l = 1; l <= FATHER; l++)
				{
					temp = new Point(p.y + ( dy[d] * l), p.x + (dx[d] * l) );
					if(isValid(temp, map) && !used[temp.y][temp.x])
					{
						used[temp.y][temp.x] = true;
						path[temp.y][temp.x] = path[p.y][p.x] + 1;
						toVisit.add(temp);
						
						if(temp.y == end.y && temp.x == end.x) return;
					}
				}
			}
		}
	}
	

	private static boolean isValid(Point temp, char[][] map) 
	{
		if(! (temp.y >= 0 && temp.y < row && temp.x >= 0 && temp.x < col))
			return false;
		
		return map[temp.y][temp.x] != 'B';
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Point
	{
		int x, y;
		
		public Point(int i, int j)
		{
			this.x = j;
			this.y = i;
		}
		
		public String toString()
		{
			return "(" + x + ", " + y + ")";
		}
	}
}
