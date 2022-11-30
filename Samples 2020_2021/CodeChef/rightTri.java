import java.util.Scanner;

public class rightTri {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		int[] x = new int[3];
		int[] y = new int[3];
		int total = 0;
		
		for(int d = 0; d < cases; d++)
		{
			for(int i = 0; i < 3; i++)
			{
				x[i] = scan.nextInt();
				y[i] = scan.nextInt();
			}
			
			int a = (int) Math.pow(x[1] - x[0], 2) + (int) Math.pow(y[1] - y[0], 2);
			int b = (int) Math.pow(x[2] - x[1], 2) + (int) Math.pow(y[2] - y[1], 2);
			int c = (int) Math.pow(x[0] - x[2], 2) + (int) Math.pow(y[0] - y[2], 2);
			
			boolean right = false;
			
			if(c > a && c > b)
			{
				if(a + b == c)
					right = true;
			}
			
			else if(b > a && b > c)
			{
				if(a + c == b)
					right = true;
			}
			
			else if(a > c && a > b)
			{
				if(c + b == a)
					right = true;
			}
			
			if(right)
				total++;
		}
		System.out.println(total);
	}
}
