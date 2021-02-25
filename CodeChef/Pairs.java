import java.util.ArrayList;
import java.util.Scanner;

public class Pairs {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int nums = scan.nextInt();
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			for(int i = 0; i < nums; i++)
				list.add(scan.nextInt());
			
			int total = 0;
			int temp;
			
			for(int i = 0; i < nums; i++)
			{
				temp = 0;
				for(int k = i+1; k < nums; k++)
					if(list.get(i) != list.get(k))
						temp++;
				total+= temp;
			}
			
			System.out.println(total);
		}
	}
}
