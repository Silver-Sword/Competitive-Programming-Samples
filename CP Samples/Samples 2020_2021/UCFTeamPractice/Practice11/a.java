import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class a {
	static int n;
	static int rows, cols;
	static int winRow, winCol;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		in = scan.readLine().split(" ");
		rows = parse(in[0]);
		cols = parse(in[1]);
		
		char[][] ray = new char[rows][cols];
		for(int i = 0; i < rows; i++)
			ray[i] = scan.readLine().toCharArray();
		
		winRow = getDim(ray, 0, 1);
		winCol = getDim(ray, 1, 0);
		
		int count = 0;
		HashSet<Hash> hashes = new HashSet<Hash>();
		
		for(int i = 1; i < rows; i += winRow + 1)
		{
			for(int j = 1; j < cols; j += winCol + 1)
			{
				if(hashes.add(getHash(ray, i, j, 1, 1)))
				{
					count ++;
					hashes.add(getHash(ray, i + winRow - 1, j + winCol - 1, -1, -1));
					
					if(winRow == winCol)
					{
						hashes.add(getSidewaysHash(ray, i + winRow - 1, j, -1, 1));
						hashes.add(getSidewaysHash(ray, i, j + winCol - 1, 1, -1));
					}
				}
			}
		}
		
		System.out.println(count);
	}

	private static Hash getSidewaysHash(char[][] ray, int i, int j, int dy, int dx) 
	{
		Hash ans = new Hash();
		
		for(int x = 0; x < winCol; x++)
		{
			for(int y = 0; y < winRow; y++)
			{
				ans.add(ray[i + (dy * y)][j + (dx * x)]);
			}
		}
		
		return ans;
	}

	private static Hash getHash(char[][] ray, int i, int j, int dy, int dx) 
	{
		Hash ans = new Hash();
		
		for(int y = 0; y < winRow; y++)
		{
			for(int x = 0; x < winCol; x++)
			{
				ans.add(ray[i + (dy * y)][j + (dx * x)]);
			}
		}
		
		return ans;
	}

	private static int getDim(char[][] ray, int dx, int dy) 
	{
		int count = 0;
		int i = 1, j = 1;
		
		while(ray[i][j] != '#')
		{
			i += dy;
			j += dx;
			count ++;
		}
		
		return count;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Hash implements Comparable<Hash>
	{
		static final long mod1 = 8675309, mod2 = 1000000007;
		long val1 = 0, val2 = 0;
		int len = 0;
		static final long base1 = 257, base2 = 619;
		static long[] base1Pow, base2Pow;
		static boolean precomped = false;
		
		void add(int c)
		{
			val1 = ((val1 * base1) + c) % mod1;
			val2 = ((val2 * base2) + c) % mod2;
			len ++;
		}
		
		public int compareTo(Hash w)
		{
			if(val1 != w.val1)
				return Long.compare(val1, w.val1);
			return Long.compare(val2, w.val2);
		}
		
		public boolean equals(Object o)
		{
			return compareTo((Hash) o) == 0;
		}
		
		public int hashCode()
		{
			return (int) val1;
		}
		
		public Hash clone()
		{
			Hash toReturn = new Hash();
			toReturn.val1 = val1;
			toReturn.val2 = val2;
			toReturn.len = len;
			return toReturn;
		}
		
		public void precomp()
		{
			if(precomped) return;
			
			precomped = true;
			
			int max = 1000000;
			base1Pow = new long[max];
			base2Pow = new long[max];
			
			base1Pow[0] = 1;
			base2Pow[0] = 1;
			
			for(int i = 1; i < max; i++)
				base1Pow[i] = (base1Pow[i-1] * base1) % mod1;
			
			for(int i = 1; i < max; i++)
				base2Pow[i] = (base2Pow[i-1] * base2) % mod2;
		}
		
		//template says need fastPow if w can be longer than 10^6
		void append(Hash w)
		{
			precomp();
			val1 = (val1 * base1Pow[w.len] + w.val1) % mod1;
			val2 = (val2 * base2Pow[w.len] + w.val2) % mod2;
			
			len += w.len;
		}
		
		public Hash[] getPrefixHashes(char[] word)
		{
			int[] temp = new int[word.length];
			int i = 0;
			for(char c : word)
			{
				temp[i] = c - 'A' + 1;
				i++;
			}
			
			return (getPrefixHashes(temp));
		}
		
		public Hash[] getPrefixHashes(int[] word)
		{
			precomp();
			int n = word.length;
			
			Hash[] ans = new Hash[n+1];
			ans[0] = new Hash();
			for(int i = 1; i <= n; i++)
			{
				ans[i] = ans[i-1].clone();
				ans[i].add(word[i-1]);
			}
			
			return ans;
		}
		
		//inclusive, exclusive
		public Hash substringHash(Hash[] prefixHashes, int from, int to)
		{
			if(from == to)
				return new Hash();
			
			Hash old = prefixHashes[to].clone(), toSub = prefixHashes[from];
			int diff = to - from;
			
			precomp();
			old.val1 = (old.val1 - (toSub.val1*base1Pow[diff]) % mod1 + mod1) % mod1;
			old.val2 = (old.val2 - (toSub.val2*base2Pow[diff]) % mod2 + mod2) % mod2;
			old.len = to - from;
			return old;
		}
	}

}
