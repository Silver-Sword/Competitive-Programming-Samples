import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;

public class k {
	static int row, col;
	static int yCurrent, xCurrent;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		row = parse(in[0]);
		col = parse(in[1]);
		
		while(row != 0 && col != 0)
		{
			int[][] table = new int[row][col];
			int[] rowVal = new int[row];
			int[] colVal = new int[col];
			Cell[][] cell = new Cell[row][col];
			
			for(int i = 0; i < row; i++)
			{
				in = scan.readLine().split(" ");
				for(int j = 0; j < col; j++)
					table[i][j] = parse(in[j]);
			}
			
			in = scan.readLine().split(" ");
			for(int i = 0; i < row; i++)
				rowVal[i] = parse(in[i]);
			
			in = scan.readLine().split(" ");
			for(int i = 0; i < col; i++)
				colVal[i] = parse(in[i]);
			
			for(int i = 0; i < row; i++)
			{
				for(int j = 0; j < col; j++)
				{
					cell[i][j] = new Cell(i, j, table[i][j]);
					if(table[i][j] != -1)
					{
						rowVal[i] -= table[i][j];
						colVal[j] -= table[i][j];
					}
				}
			}
			
			solve(table, rowVal, colVal, cell, out);
			
			in = scan.readLine().split(" ");
			row = parse(in[0]);
			col = parse(in[1]);
		}
	}

	private static void solve(int[][] table, int[] rowVal, int[] colVal, Cell[][] cell, PrintWriter out) 
	{
		for(int i = 0; i < row; i++)
		{
			yCurrent = i;
			for(int j = 0; j < col; j++)
			{
				xCurrent = j;
				
				if(j != 0)
					out.print(" ");
				
				if(cell[i][j].isSet)
					out.print(table[i][j]);
				else if(cell[i][j].valid.size() > 1)
					out.print("-1");
				else
				{
					forceCell(i ,j, table, rowVal, colVal, cell);
					if(cell[i][j].valid.size() == 1)
						out.print(cell[i][j].getValid());
					else
						out.print("-1");
				}
			}
			out.println();
		}
		
		out.flush();
		
	}

	private static void forceCell(int i, int j, int[][] table, int[] rowVal, int[] colVal, Cell[][] cell) 
	{
		int min = 0;
		int max = Math.min(100, Math.min(rowVal[i], colVal[j]));
		int count = 0;
		
		for(int val = min; val <= max; val++)
		{
			table[i][j] = val;
			rowVal[i] -= val;
			colVal[j] -= val;
			
			if(getValid(0, 0, table, rowVal, colVal, cell))
			{
				cell[i][j].valid.add(val);
				count++;
			}
			
			colVal[j] += val;
			rowVal[i] += val;
			table[i][j] = -1;
			
			if(count > 1)
				return;
		}
	}

	private static boolean getValid(int i, int j, int[][] table, int[] rowVal, int[] colVal, Cell[][] cell) 
	{
		boolean toReturn = false;
		
		if(i == row - 1 && j == col - 1)
		{
			if(table[i][j] != -1)
			{
				return rowVal[i] == 0 && colVal[j] == 0;
			}
			
			if(rowVal[i] == colVal[j] && rowVal[i] <= 100)
			{
				cell[i][j].valid.add(rowVal[i]);
				return true;
			}
			
			else
				return false;
		}
		
		// last valid cell for the column
		else if(i == row - 1)
		{
			if(table[i][j] != -1)
			{
				if(colVal[j] == 0)
					return getValid(i, j + 1, table, rowVal, colVal, cell);
				else
					return false;
			}
			
			if(rowVal[i] >= colVal[j] && colVal[j] <= 100)
			{
				rowVal[i] -= colVal[j];
				toReturn = getValid(i, j + 1, table, rowVal, colVal, cell);
				if(toReturn)
					cell[i][j].valid.add(colVal[j]);
				
				rowVal[i] += colVal[j];
			}
			
			else
				return false;
		}
		
		// last valid cell for the row
		else if(j == col - 1)
		{
			if(table[i][j] != -1)
			{
				if(rowVal[i] == 0)
					return getValid(i + 1, 0, table, rowVal, colVal, cell);
				else
					return false;
			}
			
			if(colVal[j] >= rowVal[i] && rowVal[i] <= 100)
			{
				colVal[j] -= rowVal[i];
				
				toReturn = getValid(i + 1, 0, table, rowVal, colVal, cell);
				if(toReturn)
					cell[i][j].valid.add(rowVal[i]);
				
				colVal[j] += rowVal[i];
			}
			
			else
				return false;
		}
		
		// every other cell
		else
		{
			if(table[i][j] != -1)
				return getValid(i, j+1, table, rowVal, colVal, cell);
			
			if(beforeCurrent(i, j))
			{
				for(int val : cell[i][j].valid)
				{
					rowVal[i] -= val;
					colVal[j] -= val;
					
					toReturn = getValid(i, j + 1, table, rowVal, colVal, cell);
					
					colVal[j] += val;
					rowVal[i] += val;
					
					if(toReturn)
					{
						cell[i][j].valid.add(val);
						break;
					}
				}
			}
			
			
			else
			{
				int max = Math.min(100, Math.min(rowVal[i], colVal[j]));
				
				for(int val = max; val >= 0; val--)
				{
					rowVal[i] -= val;
					colVal[j] -= val;
					
					toReturn = getValid(i, j + 1, table, rowVal, colVal, cell);
					
					colVal[j] += val;
					rowVal[i] += val;
					
					if(toReturn)
					{
						cell[i][j].valid.add(val);
						break;
					}
				}
			}
		}
		
		return toReturn;
	}

	private static boolean beforeCurrent(int i, int j) 
	{
		return i < yCurrent || (i == yCurrent && j < xCurrent);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Cell
	{
		HashSet<Integer> valid = new HashSet<Integer>();
		boolean isSet;
		int x, y;
		
		public Cell(int i, int j, int val)
		{
			y = i;
			x = j;
			isSet = (val != -1);
		}
		
		public int getValid()
		{
			for(int v : valid)
				return v;
			
			return -1;
		}
		
	}
}
