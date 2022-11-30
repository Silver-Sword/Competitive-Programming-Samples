import java.util.Scanner;

public class moveBrackets {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();

		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			scan.nextLine();
			
			int countOpen = 0;
			int outOfOrder = 0;
			
			for(char c : scan.nextLine().toCharArray())
			{
				if(c == '(')
					countOpen++;
				else
				{
					countOpen--;
					if(countOpen < 0)
					{
						outOfOrder++;
						countOpen = 0;
					}
				}
			}
			
			System.out.println(outOfOrder);
		}
	}
}
