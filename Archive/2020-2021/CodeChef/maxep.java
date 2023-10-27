import java.util.Scanner;

public class maxep {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int max = scan.nextInt();
		int coinsLeft = 1000;
		int cost = scan.nextInt();
		int min = 1;
		int mid = (max + min)/2;
		
		int minBroken = max;
		boolean broke = false;
		
		while(min <= max)
		{
			if(broke && coinsLeft > cost)
			{
				System.out.println(2);
				System.out.flush();
			}
			
			System.out.println("1 " + mid);
			coinsLeft--;
			System.out.flush();
			int ans = scan.nextInt();
			
			if(ans == -1)
			{
				System.out.println(2);
				coinsLeft -= cost;
				broke = false;
			}
			
			else if(ans == 0)
			{
				min = mid + 1;
				mid = (min + max)/2;
				broke = false;
			}
			
			else
			{
				minBroken = mid;
				max = mid - 1;
				mid = (min + max)/2;
				broke = true;
			}			
		}
		
		if(broke && coinsLeft >= cost)
		{
			System.out.println(2);
			System.out.flush();
		}
		
		System.out.println("3 " + (minBroken));
		System.out.flush();
			
	}
}
