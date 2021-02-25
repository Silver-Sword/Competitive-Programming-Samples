import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class alpha {
	public static void main(String[] args)
	{
		//char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int key = scan.nextInt();
			
			String ans = output(map, key);
			
			System.out.println(ans);
		}
	}
	
	public static String output(Map<Integer, String> map, int key)
	{
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		if(map.containsKey(key))
		{
			return map.get(key);
		}
		
		else
		{
			if(key > 25)
				map.put(key, output(map, key - 25) +  output(map, 25));
			else
			{
				String out = "";
				
				for(int i = key; i >= 0; i--)
				{
					out += alphabet[i];
				}
				
				map.put(key, out);
			}
			
			return map.get(key);
		}
	}
}
