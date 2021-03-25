import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class vestigium {

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int cases = scan.nextInt();
		
		for(int t = 1; t <= cases; t++)
		{
			int n = scan.nextInt();
			int[][] ray = new int[n][n];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
					ray[i][j] = scan.nextInt();
			
			int sum = 0;
			for(int i = 0; i < n; i++)
				sum += ray[i][i];
			
			int row = 0, col = 0;
			for(int i = 0; i < n; i++)
				if(dupe(0, i, 1, 0, ray, n))
					col++;
			for(int j = 0; j < n; j++)
				if(dupe(j, 0, 0, 1, ray, n))
					row++;
			
			System.out.printf("Case #%d: %d %d %d\n", t, sum, col, row);
			
		}

	}
	
	public static boolean dupe(int x, int y, int dx, int dy, int[][] ray, int n)
	{
		boolean[] check = new boolean[n+1];
		if(dy == 0)
			for(int j = 0; j < n; j++)
			{
				check[ray[y][j]] = !check[ray[y][j]];
				if(!check[ray[y][j]])
					return true;
			}
		else
			for(int i = 0; i < n; i++)
			{
				check[ray[i][x]] = !check[ray[i][x]];
				if(!check[ray[i][x]])
					return true;
			}
		
		return false;
	}

}
