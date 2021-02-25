import java.util.Scanner;

public class June20_5 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			int m = n * n;
			
			for(int i = 0; i < n; i++)
			{
				String out = "";
				for(int j = 0; j < n; j++)
				{
					if(i % 2 == 1)
						out = (i*n + j + 1) + " " + out;
					else
						out += " " + (i*n + j + 1);
				}
				
				out = out.trim();
				System.out.println(out);
			}
			
		}

	}

}
