import java.util.Scanner;

public class Horses {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int min = Integer.MAX_VALUE;
			int horses = scan.nextInt();
			
			int[] skills = new int[horses];
			
			for(int i = 0; i < horses; i++)
			{
				skills[i] = scan.nextInt();
			}
			
			int temp;
			for(int outer = 0; outer < horses - 1; outer++)
			{
				for(int inner = outer + 1; inner < horses; inner++)
				{
					temp = Math.abs(skills[inner] - skills[outer]);
					min = min > temp ? temp : min;
				}
			}
			
			System.out.println(min);
		}
	}
}
