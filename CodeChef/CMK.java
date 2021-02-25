import java.util.Arrays;
import java.util.Scanner;

public class CMK {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++) {
		int fights = scan.nextInt();
		int min = scan.nextInt();
		
		int[] opponent = new int[fights];
		int[] chef = new int[fights];
		
		for(int i = 0; i < opponent.length; i++)
			opponent[i] = scan.nextInt();
		for(int i = 0; i < fights; i++)
			chef[i] = scan.nextInt();
		
		Arrays.sort(opponent);
		Arrays.sort(chef);
		
		int one = 0, two = 0, wins = 0;
		
		while(one < fights && two < fights)
		{
			if(chef[one] > opponent[two])
			{
				wins++;
				one++;
				two++;
			}
			
			else
			{
				one++;
			}
		}
		
		if(wins >= min)
			System.out.println("YES");
		else
			System.out.println("NO");
		
		}
	}
}
