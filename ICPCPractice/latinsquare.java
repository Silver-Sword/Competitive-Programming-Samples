import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class latinsquare {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		int k = parse(in[1]);
		
		boolean[][] row = new boolean[n][n + 1];
		boolean[][] col = new boolean[n][n + 1];
		boolean[] seen = new boolean[n+1];
		
		boolean[][] preset = new boolean[n][n];
		int[][] val = new int[n][n];
		
		boolean valid = true;
		for(int i = 0; i < n; i++)
		{
			in = scan.readLine().split(" ");
			for(int j = 0; j < n; j++)
			{
				val[i][j] = parse(in[j]);
				
				if(val[i][j] != 0)
				{
					preset[i][j] = true;
					
					if(row[i][val[i][j]] || col[j][val[i][j]]) valid = false;
					
					row[i][val[i][j]] = true;
					col[j][val[i][j]] = true;
					
					seen[val[i][j]] = true;
				}
			}
		}
		
		boolean hasSolution = valid && solve(col, preset, val, seen);
		
		if(!hasSolution)
			out.println("NO");
		else
		{
			out.println("YES");
			
			for(int i = 0; i < n; i++)
			{
				out.print(val[i][0]);
				for(int j = 1; j < n; j++)
					out.print(" " + val[i][j]);
				
				out.println();
			}
		}

		out.flush();
	}

	private static boolean solve(boolean[][] col, boolean[][] preset, int[][] val, boolean[] seen) 
	{
		for(int v = 1; v <= n; v++)
		{
			if(seen[v]) continue;
			
			if(!put(0, v, col, preset, val))
				return false;
		}
		
		return true;
	}
	
	public static boolean put(int row, int val, boolean[][] col, boolean[][] set, int[][] grid)
	{
		if(row >= n)
			return true;
		
		// try to put in the row
		for(int c = 0; c < n; c++)
		{
			if(set[row][c] || col[c][val]) continue;
			
			// try to put here
			col[c][val] = true;
			set[row][c] = true;
			grid[row][c] = val;
			
			if(put(row + 1, val, col, set, grid))
				return true;
			
			set[row][c] = false;
			col[c][val] = false;
		}
		
		// no valid ways
		return false;
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
