import java.util.Scanner;

public class jars {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int n = scan.nextInt();
			
			long total = 1;
			
			for(int i = 0; i < n; i++)
				total += scan.nextInt() - 1;
			
			System.out.println(total);
		}
	}
}
