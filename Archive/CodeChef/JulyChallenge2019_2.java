import java.math.BigInteger;
import java.util.Scanner;

public class JulyChallenge2019_2 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		scan.nextLine();
		
		for(int c = 0; c < cases; c++)
		{
			BigInteger people = new BigInteger(scan.nextLine());
			BigInteger candies = new BigInteger(scan.nextLine());
			BigInteger TWO = new BigInteger("2");
			if(people.equals(TWO))
				if(people.mod(TWO).equals(BigInteger.ZERO))
					System.out.println("0");
				else
					System.out.println("1");
			else
			{
				BigInteger half = ((people.add(BigInteger.ONE)).divide(TWO));
				BigInteger mod = people.mod(candies);
				//System.out.println(mod);
				if(mod.compareTo(half) > 0)
				{
					mod = half.subtract(mod.subtract(half));
				}
				if(mod.equals(half))
					System.out.println(people.subtract(BigInteger.ONE));
				else
					System.out.println(mod.multiply(TWO));
			}
		}
	}
}
