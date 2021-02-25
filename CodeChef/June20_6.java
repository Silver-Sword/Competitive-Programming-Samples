import java.util.Arrays;
import java.util.Scanner;

public class June20_6 {
	static int n;
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			int p = scan.nextInt();
			
			int total = ask(scan, 0, 0, n-1, n-1);
			int[][] ray = new int[n][n];
			
			if(total == n * n)
			{
				for(int i = 0; i < n; i++)
					Arrays.fill(ray[i], 1);
			}
			
			else if(total != 0)
				fill(ray, total, 0, 0, n-1, n-1, scan);
			
			
			
			print(ray, scan);
		}
	}
	
	public static void fill(int[][] ray, int total, int y1, int x1, int y2, int x2, Scanner scan)
	{
		boolean cutVert = x2 - x1 > 0;
		
		int half1, half2;
		if(cutVert)
			half1 = ask(scan, y1, x1, y2, (x1+x2)/2);
		else
			half1 = ask(scan, y1, x1, (y1+y2)/2, x2);
		
		half2 = total - half1;
		
		if(half1 == 0 || half1 == ((y2-y1) + 1) * ((x2-x1) + 1)/2)
		{
			if(half1 != 0)
			{
				int yMax = cutVert ? y2 : (y1+y2)/2;
				int xMax = cutVert ? (x1+x2)/2 : x2;
				for(int i = y1; i <= yMax; i++)
					for(int j = x1; j <= xMax; j++)
						ray[i][j] = 1;
			}
		}
		else
			fill(ray, half1, y1, x1, cutVert ? y2 : (y1+y2)/2, cutVert ? (x1+x2)/2 : x2, scan);
		
		if(half2 == 0 || half2 == ((y2-y1) + 1) * ((x2-x1) + 1)/2)
		{
			if(half2 != 0)
			{
				for(int i = cutVert ? y1 : (y1+y2)/2 + 1; i <= y2; i++)
					for(int j = cutVert ? (x1+x2)/2+1 : x2; j <= x2; j++)
						ray[i][j] = 1;
			}
		}
		
		else
			fill(ray, half2, cutVert ? y1 : (y1+y2)/2 + 1, cutVert ? (x1+x2)/2 + 1 : x1, y2, x2, scan);
	}
	
	public static void print(int[][] ray, Scanner scan)
	{
		System.out.println(2);
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				System.out.print(ray[i][j]);
				if(j != n-1)
					System.out.print(" ");
			}
			System.out.println();
		}
		
		if(scan.nextInt() < 0)
			System.exit(0);
	}
	
	public static int ask(Scanner scan, int r1, int c1, int r2, int c2)
	{
		System.out.println(1 + " " + (r1+1) + " " + (c1+1) + " " + (r2+1) + " " + (c2+1));
		System.out.flush();
		return scan.nextInt();
	}
}
