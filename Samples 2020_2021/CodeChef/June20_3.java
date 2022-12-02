import java.util.Scanner;

public class June20_3 {
	static int n;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			int[] coins = {0, 0, 0};
			
			int[] people = new int[n];
			for(int i = 0; i < n; i++)
				people[i] = scan.nextInt();
			
			if(people[0] != 5)
			{
				System.out.println("NO");
				continue;
			}
			
			System.out.println(possible(coins, people) ? "YES" : "NO");
		}

	}
	
	public static boolean possible(int[] coins, int[] p)
	{
		for(int i = 0; i < n; i++)
		{
			if(p[i] == 5)
			{
				coins[0] ++;
				continue;
			}
			
			if(p[i] == 10)
			{
				if(coins[0] > 0)
				{
					coins[0]--;
					coins[1] ++;
				}
				else
					return false;
			}
			
			else
			{
				if(coins[1] > 0)
				{
					coins[2] ++;
					coins[1] --;
				}
				else if(coins[0] > 1)
				{
					coins[0] -= 2;
					coins[2] ++;
				}
				
				else
					return false;
			}
		}
		
		return true;
	}

}
