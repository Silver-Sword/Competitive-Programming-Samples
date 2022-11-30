import java.util.Scanner;

public class July20_1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			long total = 0;
			
			int start = scan.nextInt();
			int end;
			
			for(int i = 1; i < n; i++)
			{
				end = scan.nextInt();
				total += (Math.max(start, end) - Math.min(start, end)) - 1;
				start = end;
			}
			
			System.out.println(total);
		}

	}

}
