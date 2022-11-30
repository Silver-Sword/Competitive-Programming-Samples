import java.util.Scanner;

public class going {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			String name = scan.nextLine();
			int[] ones = new int[9];
			
			for(char c : name.toCharArray())
			{
				ones[Integer.bitCount(c)]++;
			}
			
			System.out.println(flows(ones) ? "He is going to get 'em" : "You are safe");
		}

	}
	
	public static boolean flows(int[] ray)
	{
		for(int i = 0; i < 9; i++)
			if(ray[i] >= 4)
				return true;
		return false;
	}

}
