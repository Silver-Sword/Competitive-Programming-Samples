import java.util.Scanner;

public class digitsum {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			long N = scan.nextLong();
			long D = scan.nextLong();
			
			if()
		}
	}
	
	public static int digitsum(int num)
	{
		if(num == num % 10)
			return num;
		
		return num % 10 + digitsum(num / 10);
	}
}
