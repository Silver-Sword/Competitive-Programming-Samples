import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Product {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		BigInteger a, b;
		
		while(scan.hasNext())
		{
			a = new BigInteger(scan.nextLine());
			b = new BigInteger(scan.nextLine());
			
			System.out.println(a.multiply(b));
		}
		scan.close();
	}
}
