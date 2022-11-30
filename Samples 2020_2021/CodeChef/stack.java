import java.util.ArrayList;
import java.util.Scanner;

public class stack {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		scan.nextLine();
		
		for(int c = 0; c < cases; c++)
		{
			char[] line = scan.nextLine().toCharArray();
			ArrayList<Character> stack = new ArrayList<Character>();
			
			for(int i = 0; i < line.length; i++)
			{
				if("abcdefghijklmnopqrstuvwxyz".contains(String.valueOf(line[i])))
				{
					System.out.print(line[i]);
				}
				
				else if("+-*/^".contains(String.valueOf(line[i])))
				{
					stack.add(line[i]);
				}
				
				else if(line[i] == ')')
				{
						System.out.print(stack.remove(stack.size() - 1));
				}
			}
			System.out.println();
		}
	}
}
