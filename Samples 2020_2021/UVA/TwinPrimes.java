import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TwinPrimes {
	static int n = 200000000;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		boolean[] prime = new boolean[n+1];
		Arrays.fill(prime, true);
		prime[0] = false;
		prime[1] = false;
		
		int p = 2;
		double max = Math.sqrt(n+1);
		ArrayList<Twin> list = new ArrayList<Twin>();
		
		while(scan.hasNext())
		{
			int num = scan.nextInt();
			
			while(list.size() < num)
			{
				if(prime[p])
				{
					if(prime[p-2])
						list.add(new Twin(p-2, p));
					
					if(p <= max)
						for(int i = p * p; i <= n && i > 0; i+= p)
							prime[i] = false;
				}
				
				p++;
			}
			
			System.out.println(list.get(num-1).toString());
		}
		
		scan.close();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Twin
	{
		int a, b;
		
		public Twin(int a, int b)
		{
			this.a = a;
			this.b = b;
		}
		
		public String toString()
		{
			return "(" + a + ", " + b + ")";
		}
	}
}
