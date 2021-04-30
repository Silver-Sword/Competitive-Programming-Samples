
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class c {
	static int n = 4;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		String[] in;

		while(scan.hasNextLine())
		{
			in = scan.nextLine().split(" ");
			char type = in[1].charAt(0);
			int size = parse(in[0]);
			
			out.println(solve(type, size));
		}

		out.flush();
	}

	private static int solve(char type, int size) 
	{
		if(size == 1)
			return 1;
		
		// king
		if(type == 'K')
		{
			return 4;
		}
		
		// rook
		else if(type == 'R')
			return size;
		
		// bishop
		else if(type == 'B')
			return size;
		
		// knight
		else
			return size <= 2 ? 1 : 2;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
