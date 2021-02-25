import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AprChal02_v1 {
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);
		int cases = in.nextInt();
		in.nextLine();

		for(int c = 0; c < cases; c++)
		{
			int len = in.nextInt();
			in.nextLine();
			
			String[] temp = in.nextLine().split(" ");
			String word = temp[0];
			String ch = temp[1];
			
			if(allSame(ch.charAt(0), word))
			{
				long i = 1;
				long total = 0;
				while(i <= word.length())
				{
					total += i;
					i++;
				}
				out.println(total);
			}
			else
			{
				ArrayList<Integer> index = new ArrayList<Integer>();
				for(int i = 0; i < len; i++)
					if(word.charAt(i) == ch.charAt(0))
						index.add(i);
				
				out.println(ans(index, word, len, index.size()));
			}
		}	
	}
	
	public static long ans(ArrayList<Integer> index, String word, int len, int size)
	{
		long total = 0;
		long i;
		for(int start = 0; start < size; start++)
		{
			i = index.get(start);
			long before = start == 0 ? i : i - index.get(start - 1) - 1;
			
			for(int number = 0; start + number < size; number++)
			{
				int j = index.get(start + number);
				long after = start + number == size - 1 ? len - j - 1 : index.get(start + number + 1) - j - 1;
				
				total += (before+1) * (after+1);
			}
		}
		return total;
	}
		
	public static boolean allSame(char a, String word)
	{
		for(char w : word.toCharArray())
			if(a != w)
				return false;
		return true;
	}
}