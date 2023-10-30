import java.util.Scanner;

public class Feb21_1 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		for(int i = 10; i >= 1; i--)
			if(n % i == 0)
			{
				System.out.println(i);
				return;
			}
		
		scan.close();
	}
}
