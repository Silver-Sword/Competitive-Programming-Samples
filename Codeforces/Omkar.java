import java.util.Scanner;

public class Omkar {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			
			for(int i = 0; i < n - 1; i++)
				System.out.print(1 + " ");
			System.out.println(1);
		}
	}

}
