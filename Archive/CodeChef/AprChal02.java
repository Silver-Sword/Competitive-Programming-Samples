import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AprChal02 {
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
				
				out.println(ans(index, word));
			}
		}
		
		in.close();
	}
	
	public static long ans(ArrayList<Integer> index, String word)
	{
		long total = 0;
		for(int numbers = 1; numbers <= index.size(); numbers++)
		{
			for(int start = 0; start + numbers - 1 < index.size(); start++)
			{
				long before = start == 0 ? index.get(start) : index.get(start) - index.get(start - 1) - 1;
				long after = start + numbers - 1 == index.size() - 1 ? word.length() - index.get(start + numbers - 1) - 1: index.get(start + numbers) - index.get(start + numbers - 1) - 1;
				
				total += get(before, after);
			}
		}
		return total;
	}
	
	public static long get(long a, long b)
	{
		if(a == b && a == 0)
			return 1;
		
		if(a == 0 || b == 0)
			return a + b + 1;
		
		return (a+1) * (b+1);
	}
	
	public static boolean allSame(char a, String word)
	{
		for(char w : word.toCharArray())
			if(a != w)
				return false;
		return true;
	}
}
