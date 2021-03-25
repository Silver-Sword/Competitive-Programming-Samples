import java.util.Scanner;

public class bathtub {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			long volume = scan.nextInt() * scan.nextInt() * scan.nextInt();
			int rate = scan.nextInt();
			int size = scan.nextInt();
			
			if(size >= volume)
				System.out.println(0);
			else
				System.out.println((volume - size) / rate);
		}

	}

}
