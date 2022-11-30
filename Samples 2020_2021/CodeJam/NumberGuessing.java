import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class NumberGuessing {

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		
		for(int t = 1; t <= tt; t++)
		{
			int low = scan.nextInt() + 1, high = scan.nextInt();
			int n = scan.nextInt();
			int mid;
			
			while(high >= low)
			{
				mid = (low + high) / 2;
				
				System.out.println(mid);
				System.out.flush();
				String response = scan.nextLine();
				if(response.isEmpty())
					response = scan.nextLine();
				
				if(response.equals("CORRECT"))
					break;
				
				else if(response.equals("TOO_SMALL"))
					low = mid + 1;
				else
					high = mid - 1;
			}
		}

	}

}
