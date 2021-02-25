import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.TreeMap;

public class Feb21_6 
{
	static int n;
	static int MAX = (int) 1e6 + 1;
	static boolean[] primes = new boolean[MAX];
	static String[] state = new String[] {"Divyam", "Chef"};
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in;
		int tt = Integer.parseInt(scan.readLine());
		int win = 0;
		
		Arrays.fill(primes, true);
		sieve(primes);
		
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		fillMap(map);
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			int x = parse(in[0]);
			int y = parse(in[1]);
			
			if(y >= x)
				win = 1;
			
			else
			{
				int count = map.floorEntry(x).getValue();
				
				if(count > y)
					win = 0;
				else
					win = 1;
				
			}
			
			out.println(state[win]);
		}

		out.flush();
	}

	private static void fillMap(TreeMap<Integer, Integer> map) 
	{
		int count = 0;
		
		for(int i = 2; i < primes.length; i++)
			if(primes[i])
			{
				count ++;
				map.put(i, count);
			}
	}

	private static void sieve(boolean[] primes) 
	{
		primes[0] = false;
		primes[1] = false;
		for(int i = 2; i * i < primes.length; i++)
		{
			if(primes[i])
				for(int j = i * i; j < primes.length; j += i)
					primes[j] = false;
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
