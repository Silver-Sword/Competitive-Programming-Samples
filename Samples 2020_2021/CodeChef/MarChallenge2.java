import java.util.ArrayList;
import java.util.Scanner;

public class MarChallenge2 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			ArrayList<Integer> num = new ArrayList<Integer>();
			String token = scan.next();
			
			int digit = scan.nextInt();
			scan.nextLine();
			
			for(int i = 0; i < token.length(); i++)
				num.add(Integer.valueOf(token.substring(i, i+1)));
			
			int size = num.size();
			
			while(num.size() == size)
			{
				num.add(digit);
				for(int i = 0; i < size; i++)
				{
					if(num.get(i) > num.get(i+1))
					{
						num.remove(i);
						break;
					}
				}
			}
			
			for(int i = 0;i < size; i++)
				System.out.print(num.get(i));
			System.out.println();
		}
	}
}
