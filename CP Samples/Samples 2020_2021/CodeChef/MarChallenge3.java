/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MarChallenge3 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		Map<String, Boolean[]> map = new HashMap<String, Boolean[]>();
		
		for(int c= 0; c < cases; c++)
		{
			int dishes = scan.nextInt();
			int total = 0;
			int k = 0;
			scan.nextLine();
			ArrayList<Boolean[]> list = new ArrayList<Boolean[]>();
			
			
			for(int i = 0; i < dishes + k; i++)
			{
				String in = scan.nextLine();
				
				Boolean[] temp;
				
				if(!map.containsKey(in))
					map.put(in, new Boolean[] {in.contains("a"), in.contains("e"), in.contains("i"), in.contains("o"), in.contains("u")});
				
				temp = map.get(in);
				
				
				if(all(temp))
				{
					total += dishes - 1;
					dishes --;
					k ++;
				}
				
				else
					list.add(temp);
					
				
			}
			
			for(int i = 0; i < dishes - 1; i++)
			{
				for(int j = i + 1; j < dishes; j++)
				{
					if(good(list.get(i), list.get(j)))
						total++;
				}
			}
			
			System.out.println(total);
		}
	}
	
	private static boolean all(Boolean[] temp) {
		return temp[0] && temp[1] && temp[2] && temp[3] && temp[4];
	}

	public static boolean good(Boolean[] booleans, Boolean[] booleans2)
	{
		for(int i = 0; i < 5; i++)
			if(!booleans[i] && !booleans2[i])
				return false;
		return true;
	}
}
*/

import java.util.Arrays;
import java.util.Scanner;

public class MarChallenge3 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c= 0; c < cases; c++)
		{
			int dishes = scan.nextInt();
			int total = 0;
			scan.nextLine();
			String[] need = new String[dishes];
			Arrays.fill(need, "");
			
			
			for(int i = 0; i < dishes; i++)
			{
				String in = scan.nextLine();
				
				for(char letter : "aeiou".toCharArray())
				{
					if(!in.contains(String.valueOf(letter)))
						need[i] += letter;
	
				}
			}
			
			for(int i = 0; i < dishes - 1; i++)
			{
				for(int j = i + 1; j < dishes; j++)
				{
					if(good(need[i], need[j]))
						total++;
				}
			}
			
			System.out.println(total);
		}
	}

	public static boolean good(String one, String two)
	{
		for(char l : one.toCharArray())
		{
			for(char t : two.toCharArray())
			{
				if(l == t)
					return false;
			}
		}
		
		return true;
	}
}
