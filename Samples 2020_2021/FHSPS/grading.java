import java.util.Scanner;

public class grading {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 1; t <= tt; t++)
		{
			int n = scan.nextInt();
			System.out.print("Grade #" + t + ": ");
			
			if(n > 100 || n < 0)
				System.out.println("Change");
			else
				System.out.println("No Change");
		}

	}

}
