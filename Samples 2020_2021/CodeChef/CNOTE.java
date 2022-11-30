import java.util.Scanner;

public class CNOTE {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int pagesToWrite = scan.nextInt();
			int pagesLeft = scan.nextInt();
			int rubblesleft = scan.nextInt();
			int notebooks = scan.nextInt();
			
			boolean can = false;
			for(int n = 0; n < notebooks; n++)
			{
				int pages = scan.nextInt();
				int cost = scan.nextInt();
				
				if(pagesToWrite - pagesLeft <= 0 || (pages >= pagesToWrite - pagesLeft && cost <= rubblesleft))
				{
					can = true;
				}
			}
			
			System.out.println(can ? "LuckyChef" : "UnluckyChef");
		}
	}
}
