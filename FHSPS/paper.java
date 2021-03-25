import java.util.Scanner;

public class paper {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			long count = 0;
			int n = scan.nextInt();
			
			
			for(int i = 0; i < n; i++)
			{
				long students = scan.nextLong();
				long paper = scan.nextLong();
				
				paper = paper % 4 == 0 ? paper / 4 : paper / 4 + 1;
				count += students * paper;
			}
			
			System.out.println(count);
		}
	}
}
