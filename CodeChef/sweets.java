import java.util.Scanner;

public class sweets {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int num = scan.nextInt();
			int cost = scan.nextInt();
			int sum = 0;
			int min = Integer.MAX_VALUE;
			
			int[] notes = new int[num];
			for(int i = 0; i < num; i++)
			{
				notes[i] = scan.nextInt();
				
				if(min > notes[i])
					min = notes[i];
				sum += notes[i];
			}
			
			if(sum % cost >= min)
				System.out.println("-1");
			else
				System.out.println(sum / cost);
			
		}
	}
}
