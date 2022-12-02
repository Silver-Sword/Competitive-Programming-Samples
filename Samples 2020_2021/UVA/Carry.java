import java.io.IOException;
import java.util.Scanner;

public class Carry {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		
		long a, b;
		while(scan.hasNext())
		{
			a = scan.nextLong();
			b = scan.nextLong();
			
			long ans = (a | b) ^ (a & b);
			System.out.println(ans);
		}
	}
}
