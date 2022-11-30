import java.util.Scanner;

public class CIELAB {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int A; int B;
		A = scan.nextInt();
		B = scan.nextInt();
		
		int result = A - B;
		
		if(result % 10 == 9)
			result --;
		else
			result++;
		
		System.out.println(result);
	}
}
