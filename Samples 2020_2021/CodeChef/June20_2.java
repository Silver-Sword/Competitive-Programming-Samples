import java.util.Scanner;

public class June20_2 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			String line = scan.nextLine();
			int sum = 0;
			int index = 0;
			
			while((index + 1) < line.length())
			{
				if(line.charAt(index) != line.charAt(index+1))
				{
					sum ++;
					index += 2;
				}
				else
					index++;
			}
			
			System.out.println(sum);
		}
	}
}
