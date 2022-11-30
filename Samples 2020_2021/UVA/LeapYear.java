import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class LeapYear {
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		BigInteger year;
		
		while(scan.hasNext())
		{
			year = new BigInteger(scan.nextLine());
			
			boolean leap = false, special = false;
			leap = getLeap(year);
			
			
			if(leap) {
				out.println("This is leap year.");
				special = true;
			}
			
			if(year.mod(BigInteger.valueOf(15)).equals(BigInteger.ZERO) ) {
				out.println("This is huluculu festival year.");
				special = true;
			}
			
			if(leap && year.mod(BigInteger.valueOf(55)).equals(BigInteger.ZERO) )
				out.println("This is bulukulu festival year.");
			
			if(!special)
				out.println("This is an ordinary year.");
			
			if(scan.hasNext())
				out.println();
			out.flush();
		}
		
		scan.close();
		
		
	}

	private static boolean getLeap(BigInteger year) {
		boolean leap = false;
		if((year.mod(BigInteger.valueOf(400))).equals(BigInteger.ZERO))
			leap = true;
		else if(year.mod(BigInteger.valueOf(100)).equals(BigInteger.ZERO))
			leap = false;
		else if(year.mod(BigInteger.valueOf(4)).equals(BigInteger.ZERO) )
			leap = true;
		
		return leap;
	}

}
