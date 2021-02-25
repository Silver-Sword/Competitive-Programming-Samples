import java.util.Scanner;

public class rect {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			System.out.println("Q 500000000 0");
			int dyBottom = scan.nextInt();
			scan.nextLine();
			
			System.out.println("Q 500000000 1000000000");
			int dyTop = scan.nextInt();
			
			System.out.println("Q 0 500000000");
			int dxBottom = scan.nextInt();
			
			System.out.println("Q 1000000000 500000000");
			int dxTop = scan.nextInt();
			
			int x1 = (dxTop -dxBottom - 1000000000);
			
			System.out.println(x1*-1);
		}
	}
	
}
