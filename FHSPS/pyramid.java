import java.util.Scanner;

public class pyramid {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 1; t <= tt; t++)
		{
			int n = scan.nextInt();
			int[][] ray = new int[n][3];
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < 3; j++)
					ray[i][j] = scan.nextInt();
			}
			
			System.out.println("Pyramid #" + t + ": " + (pyr(ray, n) ? "Proper " : "Improper ") + "Pyramid");
		}

	}
	public static boolean pyr(int[][] ray, int n)
	{
		for(int i = 0; i < n - 1; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(!(ray[i][j] < ray[i+1][j]))
					return false;
			}
		}
		
		return true;
	}
}

