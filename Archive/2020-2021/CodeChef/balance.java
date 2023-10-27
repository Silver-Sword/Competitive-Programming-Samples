import java.util.Scanner;

public class balance {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int pWeight = scan.nextInt();
			int tWeight = scan.nextInt();
			int pAvail = scan.nextInt();
			int tAvail = scan.nextInt();
			
			int gcd = gcd(Math.max(pWeight, tWeight), Math.min(pWeight, tWeight));
			int lcm = (pWeight / gcd) * tWeight;
			
			if(pAvail >= lcm / pWeight && tAvail >= lcm / tWeight)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
	
	public static int gcd(int num1, int num2)
	{
		if(num2 == 0)
			return num1;
		else
			return gcd(num2, num1 % num2);
	}
}
