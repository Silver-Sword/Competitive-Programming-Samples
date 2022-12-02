import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

public class Feb21_7_partial 
{
	static int n;
	static int MOD = 998_244_353; 
	static HashMap<Integer, Long>[] choose;
	
	static int MAX = (int) (2 * 1e5 + 1);
	static long[] fact = new long[MAX];
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		precompute();
		
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in;
		n = parse(scan.readLine());
		choose = new HashMap[n+1];
		Arrays.setAll(choose, x->new HashMap<Integer, Long>());
		
		int[] ray = new int[n];
		in = scan.readLine().split(" ");
		for(int i = 0;i  < n; i++)
			ray[i] = parse(in[i]);
		
		//Tree tree = new Tree(0, n);
		long[] key = new long[n];
		long sum = 0;
		
		for(int m = 1; m <= n; m++)
		{
			sum = (sum + getSequence(m, ray)) % MOD;
			key[m-1] = sum;
			//tree.update(m, getSequence(m, ray));
		}
		
		int q = parse(scan.readLine());
		
		while(q -- > 0)
		{
			out.println(key[parse(scan.readLine()) - 1]);
			//out.println(tree.query(0, parse(scan.readLine())));
		}
		
		out.flush();
	}
	
	private static long getSequence(int m, int[] ray) 
	{
		int max = 1 << 30;
		int on, off;
		long total = 0;
		
		for(int bit = 1; bit < max; bit *= 2)
		{
			on = off = 0;
			
			for(int i = 0;i < n; i++)
			{
				if((ray[i] & bit) > 0)
					on ++;
				else
					off ++;
			}
			
			total = (total + (getCombos(m, on, off) * bit) % MOD) % MOD;
		}
		
		return total;
	}

	private static long getCombos(int m, int on, int off) 
	{
		long ret = 0;
		
		// take as many ones as possible
		int one = Math.min(on, m);
		one = one % 2 == 1 ? one : one - 1;	// make sure one is odd
		if(one <= 0) return 0;
		
		int zero = m - one;	// take the remainder to be zeros
		
		// not enough offs (should only happen when off == 0 and m is even) (if on < m, then off must be at least m - on)
		if(zero > off)
			return 0;
		
		for( ; one > 0 && zero <= off; one -= 2, zero += 2)
		{
			ret = (ret + choose(on, one) * choose(off, zero)) % MOD;
		}
		
		return ret;
	}

	public static long choose(int n, int k)
	{
		if(k > n)
			return 0;
		
		if(n == k || k == 0)
			return 1;
		
		Long ans = choose[n].get(k);
		
		if(ans == null)
		{
			ans = fermat(n, k);
			choose[n].put(k, ans);
			choose[n].put(n-k, ans);
		}
		
		return ans;
	}
	
	public static long fermat(int n, int k) 
	{ 
		if(n < k)
			return 0;
		
		if (k == 0) 
			return 1; 
	   
	   return (fact[n] * modInverse(fact[k]) % MOD * modInverse(fact[n - k]) % MOD) % MOD;
	} 

	private static long modInverse(long n) 
	{
		return power(n, MOD - 2);
	}
	
	public static long power(long base, long exp)
	{
		long res = 1;
		
		while(exp > 0)
		{
			if(exp % 2 == 1)
				res = (res * base) % MOD;
			
			base = (base * base) % MOD;
			exp /= 2;
		}
		
		return res;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	public static void precompute()
	{
		fact[1] = 1;
		for(int i = 2; i < MAX; i++)
			fact[i] = (fact[i-1] * i) % MOD;
		
		/*for(int i = 1; i <= 1000 ;i++)
			System.out.print(" " + i);
		System.out.println();*/
	}
}

