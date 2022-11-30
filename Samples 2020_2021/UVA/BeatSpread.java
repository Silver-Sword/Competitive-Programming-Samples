import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BeatSpread {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			long sum = scan.nextLong();
			long diff = scan.nextLong();
			
			if((sum + diff) % 2 != 0)
				System.out.println("impossible");
			else
			{
				long a = (sum + diff) / 2;
				long b = (sum - a);
				
				if(a < 0 || b < 0)
					System.out.println("impossible");
				else
					System.out.println(a + " " + b);
			}
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}

}
