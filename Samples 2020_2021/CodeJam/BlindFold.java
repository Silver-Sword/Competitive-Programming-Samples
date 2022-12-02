import java.util.Scanner;

public class BlindFold {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			solve(scan);			
		}

	}
	
	public static void solve(Scanner scan)
	{
		int a = scan.nextInt(), b = scan.nextInt();
		scan.nextLine();
		
		Point edge;
		
		int xHigh = 1000000000, yHigh = 1000000000;
		int xLow = 0, yLow = 0;
		int xMid, yMid;
		
		System.out.println(xHigh + " " + yHigh);
		System.out.flush();
		boolean high = scan.nextLine().equals("HIT");
		if(high)
		{
			edge = new Point(xHigh, yHigh);
		}
		else
		{
			edge = binarySearch(new Point(xLow, yLow), new Point(xHigh, yHigh), scan);
		}
		
		
	}
	
	public static Point binarySearch(Point low, Point high, Scanner scan)
	{
		Point mid = new Point();
		while(low.x < high.x || low.y < high.y)
		{
			mid.x = (low.x + high.x) / 2;
			mid.y = (low.y + high.y) / 2;
			
			System.out.println(mid.x + " " + mid.y);
			System.out.flush();
			
			String in = scan.nextLine();
			if(in.equals("CENTER"))
				return null;
			
			if(in.equals("MISS"))
			{
				high.x = mid.x - 1;
				high.y = mid.y - 1;
			}
			else
			{
				low.x = mid.x;
				low.y = mid.y;
			}
			
		}
		
		return low;
	}
	
	

}

class Point
{
	int x, y;
	
	public Point(int j, int i)
	{
		x = j;
		y = i;
	}
	
	public Point()
	{
		x = 0;
		y = 0;
	}
}