import java.util.Scanner;

public class Intest {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		int input;
		int sum = 0;
		
		for(int i = 0; i < n; i++)
		{
			input = scan.nextInt();
			if(input % k == 0)
				sum++;
		}
		System.out.println(sum);
		scan.close();
	}
}
