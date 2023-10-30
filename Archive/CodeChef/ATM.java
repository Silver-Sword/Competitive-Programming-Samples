import java.util.Scanner;

public class ATM {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int bank; double balance;

			bank = scan.nextInt();
			balance = scan.nextDouble();
			
			if(bank % 5 == 0)
			{
				if(bank + 0.5 <= balance)
				{
					balance -= 0.5;
					balance -= bank;
				}
			
			}
			System.out.printf("%.2f", balance);
		scan.close();
	}
}
