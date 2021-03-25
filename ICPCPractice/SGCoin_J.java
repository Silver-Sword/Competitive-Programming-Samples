import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

public class SGCoin_J 
{
	static int n;
	static char[] character = "abcdefghijklmnopqrstuvwxyz0123456789-".toCharArray();
	static long MOD = 1000000007;
	static long target = 140000000;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		Random rand = new Random(System.currentTimeMillis());
		
		long hash = Long.parseLong(scan.readLine());
		
		char[] A = new char[30];
		long token0 = getToken(hash, A, rand);
		
		char[] B = new char[30];
		long token1 = getToken(target, B, rand);
		
		print(A, token0, out);
		print(B, token1, out);

		//check(hash, A, token0, B, token1);
		
		out.flush();
	}

	private static long getToken(long hash, char[] transaction, Random rand) 
	{
		long v = hash;
		
		generateArray(transaction, rand);
		
		for(int i = 0; i < transaction.length; i++)
			v = (v * 31 + transaction[i]) % MOD;
		
		return getToken(v);
	}

	private static long getToken(long v) 
	{
		long temp = (target - v * 7);
		
		while(temp < 0)
			temp += MOD;
		
		return temp % MOD;
	}

	private static void generateArray(char[] transaction, Random rand) 
	{
		for(int i = 0; i < transaction.length; i++)
			transaction[i] = character[rand.nextInt(character.length)];
	}

	private static void print(char[] array, long token, PrintWriter out) 
	{
		
		for(int i = 0; i < array.length; i++)
			out.print(array[i]);
		
		out.print(" ");
		out.print(token);
		out.println();
	}
	
	/*public static void check(long hash, char[] A, long token0, char[] B, long token1)
	{
		long seven = (long) 1e7;
		
		long temp = H(hash, A, token0);
		System.out.println("Hash 1 is " + temp);
		
		temp = H(temp, B, token1);
		System.out.println("Hash 2 is " + temp);
	}
	
	static long H(long previousHash, char[] transaction, long token) 
	{
		long v = previousHash;
		for (int i = 0; i < transaction.length; i++) {
		  v = (v * 31 + transaction[i]) % 1000000007;
		}
		return (v * 7 + token) % 1000000007;
	}*/
}
