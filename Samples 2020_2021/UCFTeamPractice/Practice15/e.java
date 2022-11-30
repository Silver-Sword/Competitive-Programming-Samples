import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class e 
{
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String line = scan.readLine();
		
		while(line != null)
		{
			String[] in = line.split(" ");
			n = parse(in[0]);
			
			int tt = parse(in[1]);
			char[][] forest = new char[n][n];
			for(int i = 0; i < n; i++)
				Arrays.fill(forest[i], '.');
			
			for(int t = 0; t < tt; t++)
			{
				in = scan.readLine().split(" ");
				addTree(parse(in[0]), parse(in[1]), parse(in[2]), forest);
			}
			
			printForest(forest , out);
			line = scan.readLine();
		}
		out.flush();
	}

	private static void addTree(int S, int X, int Y, char[][] forest) 
	{
		// tree not in view (probably needs to be checked)
		if(X + S + 5 < 0 || X > n || Y > n || Y + S + 5 < 0)
			return;
		
		put(X - 1, Y, forest, '_');
		put(X + 1, Y, forest, '_');
		
		// add tree stump
		if(S == 0)
		{
			put(X, Y, forest, 'o');
		}
		
		// add standing tree
		else
		{
			put(X, Y, forest, '|');
			
			// add trunk
			for(int s = 1; s <= S; s++)
			{
				put(X, Y + s, forest, '|');
				put(X-1, Y + s, forest, '/');
				put(X+1, Y + s, forest, '\\');
			}
			
			// top of tree
			put(X, Y + S + 1, forest, '^');
		}
	}

	private static void put(int x, int y, char[][] forest, char c) 
	{
		if(!valid(x, y)) return;
		forest[y][x] = c;
	}

	private static boolean valid(int x, int y) 
	{
		return x >= 0 && y >= 0 && y < n &&  x < n;
	}

	private static void printForest(char[][] array, PrintWriter out) 
	{
		printBorder(array.length + 2, out);
		
		for(int i = array.length - 1; i >= 0; i--)
		{
			out.print("*");
			
			for(int j = 0; j < array[i].length; j++)
				out.print(array[i][j]);
			
			out.print("*");
			out.println();
		}
		
		printBorder(array.length + 2, out);
		out.println();
	}

	private static void printBorder(int len, PrintWriter out) 
	{
		while(len -- > 0)
			out.print("*");
		out.println();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
