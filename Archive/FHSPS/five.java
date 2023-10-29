import java.util.Scanner;

public class five {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			System.out.println(scan.nextInt() * 5);
		}
	}
}
