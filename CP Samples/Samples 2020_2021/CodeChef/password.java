import java.util.ArrayList;
import java.util.Scanner;

public class password {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int len = scan.nextInt();
			scan.nextLine();

			String word = scan.nextLine();
			
			if(len == 1)
				System.out.println(word);
			
			else
			{
				String[] ray = word.split(String.valueOf(word.charAt(0)));
				
				if((ray.length == 2 && ray[0].isEmpty()) || isEmpty(ray))
					System.out.println(word.charAt(0));
			
				else
				{
					int i = findIndex(ray);
					
					if(i == -1)
						System.out.println(word.charAt(0));
					else
						System.out.println(word.substring(0, i+1));
				}
			}
		}
	}
	
	public static int findIndex(String[] ray)
	{
		int i = 0;
		
		while(i < ray[1].length())
		{
			char c = ray[1].charAt(i);
			for(int j = 2; j < ray.length; j++)
			{
				if(!ray[j].isEmpty())
				{
				if(i >= ray[j].length())
					return i;
				
				if(ray[j].charAt(i) != c)
					return i;
				}
			}
			i++;
		}
		
		return i;

	}
	
	public static boolean isEmpty(String[] ray)
	{
		for(int i = 0; i< ray.length; i++)
			if(!ray[i].isEmpty())
				return false;
		return true;
	}
}