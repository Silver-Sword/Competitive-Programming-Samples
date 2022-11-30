import java.util.ArrayList;
import java.util.Scanner;

public class lapindromes {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		scan.nextLine();
		
		for(int c = 0; c < cases; c++)
		{
			String input = scan.nextLine();
			String begin = input.substring(0, input.length() / 2);
			String end = input.substring((input.length() + 1 ) / 2 );
			boolean ans;
			
			if(begin.equals(end))
				System.out.println("YES");
			else
			{
				ans = result(begin, end);
				System.out.println(ans ? "YES" : "NO");
			}
		}
	}
	
	public static boolean result(String word1, String word2)
	{
		for(char letter : word1.toCharArray())
		{
			if(word2.contains(String.valueOf(letter)))
			{
				int tempIndex = word2.indexOf(String.valueOf(letter));
				if(tempIndex == 0)
					word2 = word2.substring(1);
				else if(tempIndex == word2.length() - 1)
					word2 = word2.substring(0, word2.length() - 1);
				else
					word2 = word2.substring(0, tempIndex - 1) + word2.substring(tempIndex);
			}
			
			else
				return false;
		}
		
		return true;
	}
}
