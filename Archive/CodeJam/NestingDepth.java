import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class NestingDepth {

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 1; t <= tt; t++)
		{
			String s = scan.nextLine();
			String[] ans = s.split("");
			int len = s.length();
			int[] count = new int[s.length()];
			char[] ray = s.toCharArray();
			
			System.out.print("Case #" + t + ": ");
			
			int i = 0;
			while(i < len)
			{
				if(ray[i] - '0' - count[i] == 0)
				{
					System.out.print(ans[i]);
					i++;
					continue;
				}
				
				int add = (ray[i] - '0') - count[i];
				for(int j = 0; j < add; j++)
					System.out.print("(");
				System.out.print(ans[i]);
				
				while(count[i] < ray[i] - '0')
					addParen(i, count, ans, ray, len);
				
				i++;
			}
			
			System.out.println();
		}
	}
	
	public static void addParen(int start, int[] count, String[] ans, char[] ray, int len)
	{
		int max =  (ray[start] - '0') - count[start];
		
		int i = start + 1;
		while(i < len &&  (ray[i] - '0') - count[i] > 0)
		{
			max = Math.min(max, (ray[i] - '0')- count[i] );
			i++;
		}
		
		for(int j = start; j < i; j++)
		{
			count[j] += max;
		}
		
		String temp = "";
		for(int j = 0; j < max; j++)
			temp += ")";
		
		if(i-1 == start)
			System.out.print(temp);
		else
			ans[i-1] = ans[i-1] + temp;
	}
}