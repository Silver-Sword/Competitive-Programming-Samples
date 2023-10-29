import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class d {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		int pos = parse(in[1]);
		
		char[] num = scan.readLine().toCharArray();
		
		int ans = solve(pos, num);
		System.out.println(ans);
	}
	
	private static int solve(int target, char[] num) 
	{
		int curPos = 0;
		int index = 0;
		int len = num.length;
		int freq = 0;
		
		while(index < len && curPos < target)
		{
			freq = num[index] - '0';
			index++;
			
			if(target < curPos + freq)
				return num[index] - '0';
			
			index++;
			curPos += freq;
		}
		
		return num[len - 1] - '0';
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
