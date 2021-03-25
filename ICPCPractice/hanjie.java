import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class hanjie {
	static int r, c;
	static ArrayDeque<Integer>[][] valid = new ArrayDeque[7][];
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in = scan.readLine().split(" ");
		r = parse(in[0]);
		c = parse(in[1]);
		
		generateValidOfSize(r);
		if(r != c)
			generateValidOfSize(c);
		
		ArrayList<Integer>[] col = new ArrayList[c];
		ArrayList<Integer>[] row = new ArrayList[r];
		
		for(int i = 0; i < r; i++)
		{
			row[i] = new ArrayList<Integer>();
			in = scan.readLine().split(" ");

			for(int j = 1; j < in.length; j++)
				row[i].add(parse(in[j]));
		}
		
		for(int i = 0; i < c; i++)
		{
			col[i] = new ArrayList<Integer>();
			in = scan.readLine().split(" ");

			for(int j = 1; j < in.length; j++)
				col[i].add(parse(in[j]));
		}
		
		// generate col possibilites
		HashSet<Integer>[] validCol = new HashSet[c];
		HashSet<Integer>[] validRow = new HashSet[r];
		
		getValid(col, r, validCol);
		getValid(row, c, validRow);
		
		// check if the combo satisfies rows
		long ans = solve(0, c, validRow, validCol, new int[r]);
		
		out.println(ans);
		out.flush();
	}

	private static long solve(int index, int size, HashSet<Integer>[] validRow, HashSet<Integer>[] validCol, int[] row) 
	{
		if(index >= size)
		{
			return check(row, validRow) ? 1 : 0;
		}
		
		int ans = 0;
		
		for(int choice : validCol[index])
		{
			putIn(choice, row);
			
			ans += solve(index + 1, size, validRow, validCol, row);
			
			takeOut(choice, row);
		}
		
		return ans;
	}

	private static boolean check(int[] row, HashSet<Integer>[] validRow) 
	{
		for(int i = 0; i < row.length; i++)
			if(!validRow[i].contains(row[i]))
				return false;
		return true;
	}

	private static void takeOut(int choice, int[] row) 
	{
		for(int i = row.length - 1; i>= 0; i--)
		{
			row[i] /= 2;
		}
		
	}

	private static void putIn(int choice, int[] row) 
	{
		for(int i = row.length - 1; i >= 0; i--)
		{
			row[i] = (row[i] * 2) + (choice & 1);
			choice /= 2;
		}
	}

	private static void getValid(ArrayList<Integer>[] clues, int size, HashSet<Integer>[] validForClue) 
	{
		for(int i = 0; i < clues.length; i++)
		{
			validForClue[i] = new HashSet<Integer>();
			
			int clue = getClueKey(clues[i]);
			
			for(int v : valid[size][clue])
				validForClue[i].add(v);
		}
		
	}

	private static int getClueKey(ArrayList<Integer> clue) 
	{
		int res = 0;
		for(int i = 0; i < clue.size(); i++)
			res = (res * 10) + clue.get(i);
		
		return res;
	}

	private static void generateValidOfSize(int size) 
	{
		valid[size] = new ArrayDeque[250];
		
		int max = 1 << (size);
		int clue;
		for(int val = 0; val < max; val++)
		{
			clue = clueValue(val);
			
			if(valid[size][clue] == null)
				valid[size][clue] = new ArrayDeque<Integer>();
			
			valid[size][clue].addLast(val);
		}
	}

	private static int clueValue(int val) 
	{
		// in reverse
		int res = 0;
		int mult = 1;
		int count = 0;
		
		while(val > 0)
		{
			while((val & 1) != 1)
				val /= 2;
			
			while((val & 1) == 1)
			{
				count++;
				val /= 2;
			}
			
			res += (count * mult);
			mult *= 10;
			count = 0;
		}
		
		return res;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
