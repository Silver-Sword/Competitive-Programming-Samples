import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class l {
	static int n;
	static int row = 12, col = 10;
	
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		n = parse(scan.readLine());
		
		String[] in;
		char[] temp;
		
		while(n != 0)
		{
			row = 12;
			col = 10;
			
			// make grid
			ArrayList<ArrayList<Integer>> grid = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < col; i++)
				grid.add(new ArrayList<Integer>());
			
			for(int i = 0; i < row; i++)
			{
				temp = scan.readLine().toCharArray();
				for(int j = 0; j < col; j++)
					grid.get(j).add(0, temp[j] - 'A');
			}
			
			// complete moves
			for(int i = 0; i < n; i++)
			{
				in = scan.readLine().split(" ");
				move(in[0].charAt(0) - 'a', parse(in[1]) - 1, grid);
			}
			
			// count remaining
			int ans = 0;
			for(int i = 0; i < grid.size(); i++)
				ans += grid.get(i).size();
			
			out.println(ans);
			
			n = parse(scan.readLine());
		}

		out.flush();
	}

	private static void move(int c, int r, ArrayList<ArrayList<Integer>> grid) 
	{
		if(!valid(c, r, grid))
			return;
		
		ArrayDeque<Point> next = new ArrayDeque<Point>();
		int count = 1;
		boolean[][] removed = new boolean[row][col];
		
		next.add(new Point(c, r));
		removed[r][c] = true;
		
		Point visit;
		Point temp;
		int target = getValue(c, r, grid);
		while(!next.isEmpty())
		{
			visit = next.pop();
			
			for(int d = 0; d < 4; d++)
			{
				temp = new Point(visit.x + dx[d], visit.y + dy[d]);
				if(valid(temp.x, temp.y, grid) && !removed[temp.y][temp.x] && getValue(temp.x, temp.y, grid) == target)
				{
					next.add(temp);
					count++;
					removed[temp.y][temp.x] = true;
				}
			}
		}
		
		if(count < 3)
			return;
		
		removeAll(grid, removed);
		
	}
	
	private static void removeAll(ArrayList<ArrayList<Integer>> grid, boolean[][] removed) 
	{
		for(int c = grid.size() - 1; c >= 0; c--)
		{
			for(int r = grid.get(c).size() - 1; r >= 0; r--)
			{
				if(removed[r][c])
					grid.get(c).remove(r);
			}
		}
		
		for(int i = grid.size() - 1; i >= 0; i--)
			if(grid.get(i).isEmpty())
				grid.remove(i);
	}

	private static boolean valid(int c, int r, ArrayList<ArrayList<Integer>> grid) 
	{
		return !(c >= grid.size() || c < 0 || r >= grid.get(c).size() || r < 0 );
	}

	// assume: non empty
	private static int getValue(int c, int r, ArrayList<ArrayList<Integer>> grid) 
	{
		return grid.get(c).get(r);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Point
	{
		int x, y;
		
		public Point(int c, int r)
		{
			x = c;
			y = r;
		}
		
		public String toString()
		{
			return "(c: " + x + ", r: " + y + ")";
		}
	}
}
