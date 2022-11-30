import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

public class i 
{
	static long n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String line = scan.readLine();
		
		while(line != null)
		{
			n = parse(line);
			
			long pairs = 0;
			long total = n * n;
			
			HashMap<Long, Long> diag1 = new HashMap<Long, Long>();
			HashMap<Long, Long> diag2 = new HashMap<Long, Long>();
			
			while(n -- > 0)
			{
				String[] in = scan.readLine().split(" ");
				long x = parse(in[0]);
				long y = parse(in[1]);
				
				addDiag1(x, y, diag1);
				addDiag2(x, y, diag2);
			}
			
			pairs = sum(diag1) + sum(diag2);
			double ans = ((double) pairs ) / total;
			out.printf("%.7f\n", ( (double) pairs ) / total);
			line = scan.readLine();
		}

		out.flush();
	}

	private static long sum(HashMap<Long, Long> map) 
	{
		long ans = 0;
		long temp;
		for(Entry<Long, Long> entry : map.entrySet())
		{
			temp = entry.getValue();
			ans += (temp * temp) - temp;
		}
		
		return ans;
	}

	private static long addDiag2(long x, long y, HashMap<Long, Long> diag2) 
	{
		long key = x + y;
		
		Long pairs = diag2.get(key);
		
		if(pairs == null)
			pairs = new Long(0);
		
		diag2.put(key, pairs + 1);
		
		return pairs;
	}

	private static long addDiag1(long x, long y, HashMap<Long, Long> diag1) 
	{
		long key = x - y;
		
		Long pairs = diag1.get(key);
		
		if(pairs == null)
			pairs = new Long(0);
		
		diag1.put(key, pairs + 1);
		
		return pairs;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
