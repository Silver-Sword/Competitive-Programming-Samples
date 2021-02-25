import java.util.Scanner;

public class Marriage {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		scan.nextLine();
		
		for(int c = 0; c < cases; c++)
		{
			String[] line = scan.nextLine().split(" ");
			String name1 = line[0];
			String name2 = line[1];
			boolean ans;
			if(name1.length() == name2.length())
			{
				if(name1.equals(name2))
					ans = true;
				else
					ans = false;
			}
			
			else
			{
				if(name2.length() > name1.length())
				{
					String temp = name2;
					name2 = name1;
					name1 = temp;
				}
				
				ans = getSub(name1, name2);
			}
			
			System.out.println(ans ? "YES" : "NO");
		}
		
		
	}
	
	public static boolean getSub(String line, String sub)
	{
		int subIndex = 0;
		
		for(int i = 0; i < line.length(); i++)
		{
			if(line.charAt(i) == sub.charAt(subIndex))
				subIndex++;
			
			if(subIndex >= sub.length())
				return true;
		}
		
		return false;
	}
}
