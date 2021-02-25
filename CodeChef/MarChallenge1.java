import java.util.Scanner;

public class MarChallenge1 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int group = scan.nextInt();
			
			boolean hasZero = false;
			int posSize = 0;
			int negSize = 0;
			int zeros = 0;
			
			for(int i = 0; i < group; i++)
			{
				int temp = scan.nextInt();
				
				if(temp == 0)
				{
					hasZero = true;
					zeros ++;
				}
				
				else if(temp >= 0)
					posSize++;
				else
					negSize++;
			}
			
			System.out.print(Math.max(posSize, negSize) + zeros + " ");
			
			if(hasZero)
				System.out.println(1);
			else if(negSize > 0 && posSize > 0)
				System.out.println(Math.min(posSize, negSize));
			else
				System.out.println(Math.max(posSize, negSize));
		}
	}
}
