import java.util.Arrays;
import java.util.Scanner;

public class Palin {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		int[] count = new int[26];
		Arrays.fill(count, 0);
		
		for(int i = 0; i < input.length(); i++)
		{
			count[(int) (input.charAt(i) - 'a')] ++;
		}
		
		//find number of letters that don't have a double
		int total = 0;
		for(int i = 0; i < 26; i++)
			if(count[i] % 2 != 0)
				total++;
		if(input.length() % 2 != 0)
			total--;
		
		System.out.println(total / 2);
	}
}
