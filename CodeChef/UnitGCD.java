import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class UnitGCD {
	static int n;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		String[] map = new String[1000001];
		map[4] = "\n1 4";
		map[5] = "\n2 4 5";
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			
			
			if (n < 4)
			{
				String s = "1";
				for(int i = 2; i <= n; i++)
					s += " " + i;
				System.out.println(1);
				System.out.println(n + " " + s);
				continue;
			}
			
			System.out.println(n / 2);
			System.out.println("3 1 2 3");
			
			String s = recur(n, map);
			
			s = s.substring(1).trim();
			
			System.out.println(s);
		}

	}
	
	public static String recur(int i, String[] map)
	{
		if(i <= 3)
			return "";
		
		if(map[i] != null)
			return map[i];
		
		String s;
		if(i % 2 == 1)
			s = recur(i - 2, map) + "\n2 " + (i-1) + " " + (i);
		else
			s = recur(i-1, map) + "\n1 " + i;
		
		map[n] = s;
		return s;
	}

}
