import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Light {
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		long n = parse(scan.readLine());
		
		while(n != 0)
		{
			
			boolean sqrt = isSqrt(n);
			
			System.out.println(sqrt ? "yes" : "no");
			
			n = parse(scan.readLine());
		}
	}
	
	public static boolean isSqrt(long num)
	{
		long temp = (long) Math.round(Math.sqrt(num));
		
		return temp * temp == num;
	}

	public static long parse(String num)
	{
		return Long.parseLong(num);
	}
}
