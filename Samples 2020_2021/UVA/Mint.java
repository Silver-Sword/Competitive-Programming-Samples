import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.TreeSet;

public class Mint {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		
		while(!(in[0].equals("0") && in[1].equals("0")))
		{
			//generate lcms
			n = parse(in[0]);
			int q = parse(in[1]);
			long[] coin = new long[n];
			for(int i = 0; i < n; i++)
				coin[i] = Long.parseLong(scan.readLine());
			
			
			TreeSet<Long> lcms = new TreeSet<Long>();
			recur(0, coin, 4, lcms, 0);
			
			//use bin Search to get the above and below heights (treeSet .floor & .ceiling)
			for(int query = 0; query < q; query++)
			{
				TreeSet<Long> val = new TreeSet<Long>();
				long target = Long.parseLong(scan.readLine());

				for(long i : lcms)
					binSearch(i, target, val);
				
				System.out.println(val.floor(target) + " " + val.ceiling(target));
			}
			
			in = scan.readLine().split(" ");
		}
		
		
	}

	public static void binSearch(long lcm, long target, TreeSet<Long> val) 
	{
		long low = 0, high = target;
		long mid = (low + high) / 2;
		
		while(low + 1 < high)
		{
			if(mid * lcm == target)
			{
				val.add(target);
				return;
			}
			
			else if(mid * lcm < target)
				low = mid;
			
			else
				high = mid;
			
			mid = (low + high) / 2;
		}
		
		val.add(low * lcm);
		val.add(high * lcm);
		
	}

	public static void recur(int index, long[] coin, int tracker, TreeSet<Long> lcms, long currentLCM) 
	{
		if(tracker == 0)
		{
			lcms.add(currentLCM);
			return;
		}
		
		if(index >= n)
			return;
		
		//don't use
		if(index + tracker < n)
			recur(index+1, coin, tracker, lcms, currentLCM);
		
		//use
		currentLCM = (currentLCM == 0 ? coin[index] : lcm(coin[index], currentLCM));
		recur(index+1, coin, tracker-1, lcms, currentLCM);
		
	}
	
	public static long lcm(long a, long b)
	{
		return (a * b) / gcd(Math.max(a, b), Math.min(a, b));
	}
	
	public static long gcd(long a, long b)
	{
		if(b == 0 || a == b)
			 return a;
		
		return gcd(b, a%b);
					
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
