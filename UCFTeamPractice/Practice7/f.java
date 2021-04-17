import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class f {
	static int n;
	static long MOD = 998244353;
	static int SIZE = 'p' - 'a' + 1;
	static int CUBE = 10;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(scan.readLine());
		HashSet<String> words = new HashSet<String>();
		long[][][] freq = new long[CUBE + 1][SIZE][SIZE];
		
		StringBuilder word;
		String forward, reverse;
		for(int i = 0; i < n; i++)
		{
			word = new StringBuilder(scan.readLine().toLowerCase());
			forward = word.toString();
			reverse = word.reverse().toString();
			
			if(words.add(forward))
				updateFreq(freq, forward);
			
			if(words.add(reverse))
				updateFreq(freq, reverse);
			
		}
		
		long ans = solve(freq) % MOD;
		System.out.println(ans);
	}

	private static long solve(long[][][] freq) 
	{
		long ans = 0;
		
		for(int i = 3; i <= CUBE; i++)
		{
			ans = (ans + makeCube(freq[i])) % MOD;
		}
		
		return ans;
	}

	private static long makeCube(long[][] freq) 
	{
		int[][] connections = { {0, 1}, {0, 3}, {0, 4}, {1, 2}, {1, 5}, {2, 3}, {2, 6}, {3, 7}, {4, 5}, {4, 7}, {5, 6}, {6, 7} };
		int[] edges = new int[8];
		Arrays.fill(edges, '-');
		
		long ans = recur(0, connections, edges, freq) % MOD;
		return ans;
	}

	private static long recur(int index, int[][] connections, int[] edges, long[][] freq) 
	{
		if(index >= 12)
		{
			return 1;
		}
		
		int c1 = connections[index][0];
		int c2 = connections[index][1];
		long ans = 0;
		
		//first choice
		if(edges[c1] == '-' && edges[c2] == '-')
		{
			for(int i = 0; i < SIZE; i++)
			{
				for(int j = i; j < SIZE; j++)
				{
					if(freq[i][j] > 0)
					{
						edges[c1] = i;
						edges[c2] = j;
						
						ans = (ans + ((i != j ? 2 : 1) * freq[i][j] * recur(index + 1, connections, edges, freq)) % MOD) % MOD;
					}
				}
			}
			
			return ans;
		}
		
		// forced choice
		if(edges[c1] != '-' && edges[c2] != '-')
		{
			if(freq[edges[c1]][edges[c2]] == 0)
				return 0;
			
			return (freq[edges[c1]][edges[c2]] * recur(index + 1, connections, edges, freq) ) % MOD;
		}
		
		// choose w/ one character
		
		int change = edges[c1] == '-' ? c1 : c2;
		int c = edges[c1] == '-' ? edges[c2] : edges[c1];
		
		ans = 0;
		for(int i = 0; i < SIZE; i++)
		{
			if(freq[i][c] != 0)
			{
				edges[change] = i;
				ans = (ans + (freq[i][c] * recur(index + 1, connections, edges, freq)) % MOD) % MOD;
			}
		}
		edges[change] = '-';
		return ans;
	}

	private static void updateFreq(long[][][] freq, String word) 
	{
		int a = word.charAt(0) - 'a';
		int b = word.charAt(word.length() - 1) - 'a';
		
		freq[word.length()][a][b] ++;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
