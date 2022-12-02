import java.util.Scanner;
import java.util.TreeSet;

public class MarLunch02 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		scan.nextLine();

		for(int c = 0; c < cases; c++)
		{
			int n = scan.nextInt();
			scan.nextInt();
			scan.nextLine();
			
			TreeSet<Integer> set = new TreeSet<Integer>();
			set.add(0);
			int x = 0;
			int total = 1;
			
			for(char l : scan.nextLine().toCharArray())
			{
				if(l == 'R')
				{
					x++;
				}
				
				else
					x--;
				
				if(!set.contains(x))
				{
					set.add(x);
					total++;
				}
			}
			
			System.out.println(total);
		}
	}
}
