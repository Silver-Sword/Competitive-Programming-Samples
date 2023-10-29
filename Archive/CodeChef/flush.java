import java.util.Scanner;

public class flush {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int people = scan.nextInt();
		int minRate = scan.nextInt();
		
		for(int p = 0; p < people; p++)
		{
			int rating = scan.nextInt();
			
			String ans;
			
			if(rating < minRate)
			{
				ans = "Bad boi";
			}
			
			else
				ans = "Good boi";
			
			System.out.println(ans);
		}
	}
}
