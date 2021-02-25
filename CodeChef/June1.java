import java.util.Scanner;

public class June1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			int k = scan.nextInt();
			
			int sum = 0;
			for(int i = 0; i < n; i++)
			{
				sum += Math.max(scan.nextInt() - k, 0);
			}
			
			System.out.println(sum);
		}
			

	}

}
