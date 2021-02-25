import java.util.Scanner;

public class problemB {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();

		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			
			int twos = 0, threes = 0;
			
			while(n % 2 == 0)
			{
				twos ++;
				n /= 2;
			}
			
			while(n % 3 == 0)
			{
				threes ++;
				n /= 3;
			}
			
			if(n != 1 || threes < twos)
			{
				System.out.println(-1);
				continue;
			}
			
			System.out.println(threes + (threes - twos));
		}

	}

}
