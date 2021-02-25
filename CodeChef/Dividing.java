import java.util.Scanner;

public class Dividing {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int stamps = scan.nextInt();
		long totalStamps = 0;
		long wantTotal = 0;
		
		for(int i = 1; i <= stamps; i++)
		{
			totalStamps += scan.nextLong();
			wantTotal += i;
		}
		
		if(totalStamps == wantTotal)
			System.out.println("YES");
		else
			System.out.println("NO");
	}
}
