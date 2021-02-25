import java.util.Scanner;

public class T23 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		boolean one;
		
		for(int c = 0; c < cases; c++)
		{
			one = false;
			int raySize = scan.nextInt();
			int[] set = new int[raySize];
			for(int r = 0; r < raySize; r++)
			{
				set[r] = scan.nextInt();
				if(set[r] == 1)
					one = true;
			}
			
			if(!one)
			{
				if(match(set))
				{
					System.out.println("YES");
				}
				
				else
				{
					System.out.println("NO");
				}
			}
			
			else {
				System.out.println("YES");
			}
			
			
		}
	}
	
	public static boolean match(int[] set)
	{
		boolean ans;
		for(int outer = 0; outer < set.length - 1; outer++)
		{
			for(int inner = outer + 1; inner < set.length; inner++)
			{
				ans = true;
				int min = Math.min(set[inner], set[outer]);
				for(int i = 2; i <= min; i++)
				{
					if(set[inner] % i == 0 && set[outer] % i == 0)
						ans = false;
				}
				if(ans)
					return true;
			}
		}
		return false;
	}
}
