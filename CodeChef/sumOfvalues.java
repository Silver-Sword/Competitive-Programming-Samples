import java.util.Scanner;

public class sumOfvalues {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		long num = scan.nextLong();
		
		long i = 1;
		long total = 0;
		
		while(total + i <= num)
		{
			total += i;
			i++;
		}
		
		System.out.println(i - 1);
	}
}
