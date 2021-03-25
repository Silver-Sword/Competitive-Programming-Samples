import java.util.Scanner;

public class hunger {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			double x1 = scan.nextDouble(), y1 = scan.nextDouble(), x2 = scan.nextDouble(), y2 = scan.nextDouble();
			double x3 = scan.nextDouble(), y3 = scan.nextDouble();
			
			double side1Len = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2));
			double side2Len = Math.sqrt((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1));
			double side3Len = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
			
			double p = side1Len + side2Len + side3Len;
			
			double xAns = ( (side1Len * x1) + (side2Len * x2) + (side3Len * x3) )/ p;
			double yAns = ( (side1Len * y1) + (side2Len * y2) + (side3Len * y3) )/ p;
			
			
			
			System.out.printf("%.2f %.2f\n", xAns, yAns);
		}
	}
}
