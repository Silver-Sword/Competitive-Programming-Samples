import java.util.Scanner;

public class coinFlip {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int games = scan.nextInt();
			
			for(int g = 0; g < games; g++)
			{
				int start = scan.nextInt();
				int flips = scan.nextInt();
				int find = scan.nextInt();
				int ans;
				
				if(find == start)
				{
					ans = flips / 2;
				}
				
				else
				{
					ans = (flips + 1) / 2;
				}
				
				System.out.println(ans);
			}
		}
	}
}
