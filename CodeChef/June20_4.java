import java.util.Scanner;

public class June20_4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			long tom = scan.nextLong();
			
			//tom is even
			if(tom % 2 == 0)
			{
				long two = 1;
				long temp = tom;
				
				while(temp % 2 == 0)
				{
					two *= 2;
					temp /= 2;
				}
				two *= 2;
			
				System.out.println(tom / two);
			}
			
			else
			{
				System.out.println(tom / 2);
			}
		}

	}

}
