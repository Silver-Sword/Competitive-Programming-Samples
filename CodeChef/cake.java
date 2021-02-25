import java.util.Scanner;

public class cake {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int pieces = scan.nextInt();
			String ans = "";
			
			if(((double)360.0 / pieces) ==  360 / pieces)
				ans += "y ";
			else
				ans+= "n ";
			
			if(pieces <= 360)
				ans += "y ";
			else
				ans += "n ";
			
			if(pieces <= 26)
				ans += "y";
			else
				ans += "n";

			System.out.println(ans);
		}
	}
}
