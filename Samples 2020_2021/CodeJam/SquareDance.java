import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SquareDance {
	
	static int row, col;
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int tt = scan.nextInt();
		
		for(int t = 1; t <= tt; t++)
		{
			row = scan.nextInt();
			col = scan.nextInt();
			
			//TreeSet<Point> set = new TreeSet<Point>();
			
			int[][] ray = new int[row][col];
			for(int i = 0; i < row; i++)
			{
				for(int j = 0; j < col; j++)
				{
					ray[i][j] = scan.nextInt();
					
				}
			}
		}

	}

}
