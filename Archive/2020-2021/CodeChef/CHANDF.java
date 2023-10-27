import java.util.Scanner;

public class CHANDF {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			long X = scan.nextLong(), Y = scan.nextLong();
			long L = scan.nextLong(), R = scan.nextLong();
			
			long max = (X & R) * (Y & R);
			long maxNum = R;
			
			long temp = Long.highestOneBit(R);
			System.out.println(temp);
		}

	}

}
