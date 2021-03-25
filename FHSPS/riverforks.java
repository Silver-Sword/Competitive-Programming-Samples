import java.util.Scanner;

public class riverforks {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			int miles = scan.nextInt();
			double ans = Math.pow(2, (miles - 1) / 5);
			System.out.println((long) ans);
		}

	}

}
