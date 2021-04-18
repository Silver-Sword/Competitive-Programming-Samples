import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class g {
	static int n, k;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		k = parse(in[1]);
		int win = 0;
		
		for(int t = 0; t < n; t++)
		{
			in = scan.readLine().split(" ");
			int[] pile = new int[parse(in[0])];
			
			for(int i = 1; i <= pile.length; i++)
				pile[i-1] = parse(in[i]);
			
			//out.println("Pile " + (t+1) + " : " + winnable(pile));
			win ^= winnable(pile);
		}
		
		out.println(win == 1 ? "Alice can win." : "Bob will win.");
		out.flush();
	}

	private static int winnable(int[] pile) 
	{
		boolean[] win = new boolean[pile.length];
		for(int i = 0; i < pile.length; i++)
		{
			if(pile[i] > i)
			{
				win[i] = false;
				continue;
			}
			
			for(int j = Math.max(0, i - k - 1 - pile[i]); j <= i + pile[i]; j++)
			{
				if(win[j])
				{
					win[i] = false;
					break;
				}
			}
			
			win[i] = true;
		}
		
		int min = Math.min(pile.length - k, k);
		for(int i = 0; i <= min; i++)
			if(win[i])
				return 1;
		return 0;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
