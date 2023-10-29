import java.util.Scanner;

public class MagicChef {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int boxes = scan.nextInt();
			int coinIndex = scan.nextInt();
			int swaps = scan.nextInt();
			
			int box1, box2;
			for(int s = 0; s < swaps; s++)
			{
				box1 = scan.nextInt();
				box2 = scan.nextInt();
				
				if(box1 == coinIndex)
					coinIndex = box2;
				else if(box2 == coinIndex)
					coinIndex = box1;
			}
			
			System.out.println(coinIndex);
		}
	}
}
