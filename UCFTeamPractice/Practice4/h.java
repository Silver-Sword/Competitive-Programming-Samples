import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class h {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		
		String num1 = in[1];
		String num2 = in[2];
		
		long index1 = getIndex(in[1]);
		long index2 = getIndex(in[2]);
		
		System.out.println(Math.abs(index1 - index2) - 1);
	}
	
	public static long getIndex(String num)
	{
		long ans = recur(0, num.toCharArray(), n, 1);
		return ans;
	}
	
	public static long recur(int index, char[] num, long exp, int target)
	{
		if(index >= num.length)
			return 0;
		
		if(num[index] - '0' == target)
		{
			return pow(2, exp) - 1 - recur(index + 1, num, exp - 1, 1);
		}
		
		else
		{
			return(recur(index + 1, num, exp - 1, target));
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	public static long pow(long base, long exp)
	{
		long result = 1;
		
		while(exp > 0)
		{
			if(exp % 2 == 1)
				result = result * base;
			
			exp = exp / 2;
			base = base * base;
		}
		
		return result;
	}
}
