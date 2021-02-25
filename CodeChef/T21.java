import java.math.BigInteger;
import java.util.Scanner;

public class T21 {
	
	public static BigInteger THREE = new BigInteger("3");
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		scan.nextLine();
		
		for(int c = 0; c < cases; c++)
		{
			String temp = scan.nextLine();
			String[] tempRay = temp.split(" ");
			BigInteger m = new BigInteger(tempRay[0]);


			BigInteger n = new BigInteger(tempRay[1]);
			
			m = m.mod(THREE);
			n = n.mod(THREE);
			
			int remain1 = Integer.parseInt(m.toString());
			int remain2 = Integer.parseInt(n.toString());
			
			if(remain1 == 0 || remain2 == 0)
			{
				System.out.println("0");
			}
			
			else if(remain1 != remain2)
			{
				System.out.println("2");
			}
			
			else
			{
				System.out.println("1");
			}
			
		}
	}
}
