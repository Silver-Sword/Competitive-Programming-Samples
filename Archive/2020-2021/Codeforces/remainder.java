import java.util.Scanner;

public class remainder {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();

		for(int t = 0; t < tt; t++)
		{
			int x = scan.nextInt();
			int y = scan.nextInt();
			int n = scan.nextInt();  
			
			int cur = n % x;
			if(cur == y)
				System.out.println(n);
			
			else if(cur > y)
				System.out.println(n - (cur - y));
			
			else
			{
				System.out.println(n - x - cur + y);
			}
		}

	}
}
