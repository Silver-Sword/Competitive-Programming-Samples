import java.util.Scanner;

public class Rocks {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int d = 0; d < cases; d++)
		{
			int a = scan.nextInt();
			int b = scan.nextInt();
			int c = scan.nextInt();
			
			int x = scan.nextInt();
			int y = scan.nextInt();
			
			if(a + b + c != x + y)
			{
				System.out.println("NO");
			}
			
			else
			{
				if(a == x || a == y || b == x || b == y || c == x || c == y)
					System.out.println("YES");
				else
				{
					int min = Math.min(Math.min(a, b), c);
					int mid;
					if(a == min)
						mid = Math.min(b, c);
					else if(b == min)
						mid = Math.min(a, c);
					else 
						mid = Math.min(a, b);
					
					if(min < Math.min(x, y) && mid < Math.max(x, y))
					{
						System.out.println("YES");
					}
					else
						System.out.println("NO");
				}
			}
		}
	}
}
