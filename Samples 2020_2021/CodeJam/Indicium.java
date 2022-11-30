import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Indicium {
	
	static int n;
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		
		for(int t = 1; t <= tt; t++)
		{
			n = scan.nextInt();
			int k = scan.nextInt();
			
			//find valid k
			int[] diag = new int[n];
			boolean check = validK(n, k, diag);
			
			if(!check)
			{
				System.out.println("Case #" + t + ": IMPOSSIBLE");
				continue;
			}
			
			//fill in square
			int[][] ray = new int[n][n];
			for(int i = 0; i < n; i++)
				ray[i][i] = diag[i];
			
			TreeSet<Integer>[] set = new TreeSet[n];
			for(int i = 0; i < n; i++)
			{
				set[i] = new TreeSet<Integer>();
				set[i].add(ray[i][i]);
			}
			
			TreeSet<Integer>[] xIndex = new TreeSet[n];
			for(int j = 0; j < n; j++)
			{
				xIndex[j] = new TreeSet<Integer>();
				for(int i = 1; i <= n; i++) 
				{	
					xIndex[j].add(i);
				}
				
				xIndex[j].remove(ray[j][j]);
			}
				
			fill(0, 0, ray, xIndex, set);
			
			
			System.out.println("Case #" + t + ": POSSIBLE");
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n; j++)
				{
					System.out.print(ray[i][j]);
					if(j != n - 1)
						System.out.print(" ");
				}
				System.out.println();
			}
		}
		

	}
	
	public static boolean fill(int y, int x, int[][] ray, TreeSet<Integer>[] rowNum, TreeSet<Integer>[] col)
	{
		if(y >= n)
			return true;
		
		if(x >= n)
			return fill(y+1, 0, ray, rowNum, col);
		
		if(y == x)
			return fill(y, x+1, ray, rowNum, col);
		
		for(int i = 1; i <= n; i++)
		{
			if(rowNum[y].contains(i) && !col[x].contains(i))
			{
				rowNum[y].remove(i);
				col[x].add(i);
				
				boolean temp = fill(y, x+1, ray, rowNum, col);
				if(temp)
				{
					ray[y][x] = i;
					return true;
				}
				
				rowNum[y].add(i);
				col[x].remove(i);
			}
		}
		
		return false;
	}
	
	public static boolean validK(int n, int k, int[] diag)
	{
		Arrays.fill(diag, 1);
		int left = k - n;
		
		if(left == 0)
			return true;

		if(left - 1 == 0)
			return false;
		
		if(left % n == 0)
		{
			Arrays.fill(diag, k / n);
			return true;
		}
		
		if(k == (n*n) - 1)
			return false;
		
		diag[n-2]++;
		left--;
		
		for(int i = n - 1; i >= 0; i--)
		{
			int add = Math.min(left, i == n-2 ? n-2 : n-1);
			diag[i] += add;
			left -= add;
			
			if(left == 0)
				break;
		}
		
		int sum = diag[0] + diag[1];
		diag[0] = sum / 2;
		diag[1] = sum / 2;
		if(sum % 2 != 0)
			diag[1] ++;
		
		if(n == 3 && diag[1] == diag[2] || (n == 3 && diag[0] == diag[1]))
			return false;
		
		return true;
	}

}
