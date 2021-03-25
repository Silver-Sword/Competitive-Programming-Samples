import java.util.Scanner;

public class flagpoles {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			double a = scan.nextDouble();
			double b = scan.nextDouble();
			double d = scan.nextDouble();
			
			System.out.printf("%.3f\n", (a * b) / (a + b));
		}
	}
}
