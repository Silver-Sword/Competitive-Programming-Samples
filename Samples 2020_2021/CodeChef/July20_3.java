import java.util.Scanner;

public class July20_3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int k = scan.nextInt();
			
			boolean[][] reach = new boolean[8][8];
			
			
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					if(i == 0 && j == 0)
						System.out.print("O");
					else if(i * 8 + j >= k)
						System.out.print("X");
					else
						System.out.print(".");
				}
				
				System.out.println();
			}
			
			System.out.println();
		}
	}
}
