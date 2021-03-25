import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dallas {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			n = Integer.parseInt(scan.readLine());
			String[] dict = new String[n];
			for(int i = 0; i < n; i++)
			{
				dict[i] = scan.readLine();
			}
			
			int q = parse(scan.readLine());
			
			System.out.println("List #" + (t+1) + ":");
			for(int i = 0; i < q; i++)
			{
				String target = scan.readLine();
				System.out.print(target + ": ");
				
				if(search(target, dict))
					System.out.println("FOUND");
				else
					System.out.println("NOT FOUND");
			}
			
			System.out.println();
		}
	}

	public static boolean search(String target, String[] dict) {
		for(int i = 0; i < n ;i++)
		{
			if(isSub(target, dict[i]))
				return true;
		}
		
		return false;
	}

	public static boolean isSub(String target, String word) 
	{
		int index = 0;
		int pointer = 0;
		
		int len = target.length();
		
		while(pointer < len)
		{
			index = word.indexOf(target.charAt(pointer), index);
			if(index < 0)
				return false;
			
			index++;
			pointer++;
		}
		
		return true;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
