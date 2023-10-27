import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LittleArtem {

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int row = scan.nextInt();
			int col = scan.nextInt();
			
			for(int r = 0; r < row; r++)
			{
				String s = "";
				for(int c = 0; c < col; c++)
				{
					if((r + c) % 2 == 0)
						s += "B";
					else
						s += "W";
				}
				
				if(r == row - 1 && (row * col) % 2 == 0)
				{
					if(s.charAt(0) == 'W')
						System.out.println("B" + s.substring(1));
					else 
						System.out.println("BB" + s.substring(2));
				}
				else
					System.out.println(s);
			}
		}

	}

}
