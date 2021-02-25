import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class exerciseWalk {

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int left = scan.nextInt(), right = scan.nextInt(), down = scan.nextInt(), up = scan.nextInt();
			int xStart = scan.nextInt(), yStart = scan.nextInt(), xMin = scan.nextInt(), yMin = scan.nextInt(), xMax = scan.nextInt(), yMax = scan.nextInt();
			
			int xEnd = xStart - left + right;
			int yEnd = yStart - down + up;
			
			if(xMin == xMax && left + right != 0)
				System.out.println("No");
			else if(yMin == yMax && up + down != 0)
				System.out.println("No");
			else if(xMin <= xEnd && xEnd <= xMax && yEnd >= yMin && yEnd <= yMax)
				System.out.println("Yes");
			else
				System.out.println("No");
		}

	}

}
