import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LevelStats {

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			int[] plays = new int[n];
			int[] clears = new int[n];
			
			for(int i = 0; i < n;i++)
			{
				plays[i] = scan.nextInt();
				clears[i] = scan.nextInt();
			}
			
			System.out.println(check(plays, clears, n) ? "YES" : "NO");
		}

	}

	public static boolean check(int[] plays, int[] clears, int n) 
	{
		if(plays[0] < clears[0])
				return false;
		
		for(int i = 1; i < n; i++)
		{
			
				
			if(!(plays[i] >= plays[i-1]))
				return false;
			if(!(clears[i] >= clears[i-1]))
				return false;
			
			if(!(plays[i] >= plays[i-1] + (clears[i] - clears[i-1])))
				return false;
		}
		
		return true;
		
	}

}
