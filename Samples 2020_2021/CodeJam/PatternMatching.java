import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PatternMatching {
	
	static int n;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			scan.nextLine();
			
			String[] words = new String[n];
			String[][] parts = new String[n][];
			char[][] ray = new char[n][];
			
			for(int i = 0; i < n ;i++)
			{
				words[i] = scan.nextLine();
				parts[i] = words[i].split("\\*");
				ray[i] = words[i].toCharArray();
			}
			
			String result = begin(ray, parts);
			if(result == null)
			{
				System.out.println("Case #" + (t+1) + ": *");
				continue;
			}
			
			String end = end(ray, parts);
			if(end == null)
			{
				System.out.println("Case #" + (t+1) + ": *");
				continue;
			}
			
			
			int beginLen = result.length();
			
			for(int i = 0; i < n; i++)
			{
				int start = 0;
				int last = ray[i][ray[i].length-1] == '*' ? parts[i].length : parts[i].length - 1;
				
				int startingIndex = 0;
				for(int j = start; j < last; j++)
				{
					int index = result.indexOf(parts[i][j], startingIndex);
					if(index < 0)
					{
						result += parts[i][j];
						startingIndex = result.length();
					}
					else
					{
						startingIndex = index + parts[i][j].length();
					}
				}
			}
			
			if(!result.equals(end))
				result += end;
			
			if(result.isEmpty())
				result += "A";
			
			System.out.println("Case #" + (t+1) + ": " + result);
			
		}

	}
	
	public static String begin(char[][] ray, String[][] parts)
	{
		String ans = "";
		
		for(int i = 0; i < n; i++)
		{
			if(ray[i][0] == '*')
				continue;
			
			String temp = parts[i][0];
			
			if(ans.indexOf(temp) == 0 || temp.indexOf(ans) == 0 || ans.isEmpty())
			{
				if(temp.length() > ans.length())
					ans = temp;
			}
			else
				return null;
		}
		
		return ans;
	}
	
	public static String end(char[][] ray, String[][] parts)
	{
		String ans = "";
		
		for(int i = 0; i < n; i++)
		{
			if(ray[i][ray[i].length - 1] == '*')
				continue;
			
			String temp = parts[i][parts[i].length - 1];
			
			int test = Math.max(ans.lastIndexOf(temp), temp.lastIndexOf(ans));
			if(ans.isEmpty() || (test >= 0 && (test == ans.length() - temp.length() || test == temp.length() - ans.length())) )
			{
				if(temp.length() > ans.length())
					ans = temp;
			}
			else
				return null;
					
		}
		
		return ans;
	}

}
