import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class leverage {
	static int row, col;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		row = parse(in[0]);
		col = parse(in[1]);
		
		char[][] grid = new char[row][col];
		for(int i = 0; i < row; i++)
			grid[i] = scan.readLine().toCharArray();
		
		int[][] lines = new int[row][col];
		
		setUpLines(grid, lines);
		
		int[][] squares = new int[row][col];
		
		int max = setUpSquares(lines, squares);
		
		out.println(max);

		out.flush();
	}

	private static int setUpSquares(int[][] lines, int[][] squares) 
	{
		int max = 1;
		for(int i = 0; i <= row - max; i++)
		{
			for(int j = 0; j <= col - max; j++)
			{
				squares[i][j] = getLargestSquare(i, j, squares, lines);
				if(max < squares[i][j])
					max = squares[i][j];
			}
		}
		
		return max * max;
		
	}

	private static int getLargestSquare(int y, int x, int[][] squares, int[][] lines) 
	{
		int max = lines[y][x];
		int actual = 1;
		
		for(int dy = 1; dy < max; dy++)
		{
			if(dy + y >= row) return actual;
			
			max = Math.min(lines[y+dy][x], max);
			if(dy >= max) return actual;
			actual++;
		}
		
		return actual;
	}

	private static void setUpLines(char[][] grid, int[][] lines) 
	{
		for(int i = 0; i < row; i++)
			for(int target = 0; target < 2; target++)
				setUpLine(grid[i], lines[i]);
	}

	private static void setUpLine(char[] grid, int[] line) 
	{
		int prevG = 0, prevB = 0;
		for(int j = col - 1; j >= 0; j--)
		{
			if(grid[j] == 'G')
			{
				prevG ++;
				prevB = 0;
				line[j] = prevG;
			}
			
			else
			{
				prevG = 0;
				prevB ++;
				line[j] = prevB;
			}
		}
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
