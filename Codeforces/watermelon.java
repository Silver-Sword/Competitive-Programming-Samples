import java.util.Scanner;

public class watermelon {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		if(n % 2 == 1 || n == 2)
			System.out.println("NO");
		else
			System.out.println("YES");

	}

}
