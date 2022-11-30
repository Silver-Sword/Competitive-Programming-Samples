import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PollyPoly {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(scan.hasNextInt())
		{
			String[] in = scan.nextLine().trim().split(" ");
			
			long[] m = new long[in.length];
			for(int i = 0; i < in.length; i++)
				m[in.length - i - 1] = Long.parseLong(in[i]);
			
			String[] x = scan.nextLine().trim().split(" ");
			
			for(int i = 0; i < x.length; i++)
			{
				long ans = 0;
				
				for(int j = 0; j < m.length; j++)
				{
					if(x[i].equals("") || x[i].isEmpty())
						continue;
					ans += m[j] * fastExpo(Long.parseLong(x[i]), j);
				}
				
				if(i != 0)
					out.print(" ");
				
				out.print(ans);
			}
			
			out.println();
			out.flush();
		}
		
		scan.close();
	}
	
	public static long fastExpo(long base, long exp)
	{
		long res = 1;
		
		while(exp > 0)
		{
			if(exp % 2 == 1)
				res = (res * base);
			
			exp /= 2;
			base = (base * base);
		}
		
		return res;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
