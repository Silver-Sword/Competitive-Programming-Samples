import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;

public class Flavius {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String in = scan.readLine();

		while(!in.equals("0"))
		{
			String[] input = in.split(" ");
			
			int N = Integer.parseInt(input[0]);
			long a = Long.parseLong(input[1]) % N ;
			long b = Long.parseLong(input[2]) % N;
			
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			int survive = N;
			
			long index = 0;
			map.put(0, 1);
			
			boolean stop = false;
			
			while(!stop)
			{
				index = getNext(index, a, b, N);
				
				int i = (int) index;
				if(map.containsKey(i))
				{
					int res = map.get(i) + 1;
					
					if(res == 3)
					{
						stop = true;
					}
					else if(res == 2)
					{
						survive --;
					}
					
					map.put(i, res);
				}
				
				else
					map.put(i, 1);
				
				
			}
			
			System.out.println(survive);
			
			
			in = scan.readLine();
		}
	}
	
	public static long getNext(long index, long a, long b, long N)
	{
		BigInteger ans = BigInteger.valueOf(index);
		ans = ans.multiply(ans).multiply(BigInteger.valueOf(a)).add(BigInteger.valueOf(b)).mod(BigInteger.valueOf(N));
		return ans.longValue();
	}
}
