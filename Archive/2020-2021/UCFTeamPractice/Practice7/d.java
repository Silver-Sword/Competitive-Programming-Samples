import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class d {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] input = scan.readLine().split(" ");
		
		char[] rank = "A23456789TJQK".toCharArray();
		int[] freq = new int[rank.length];
		
		for(int i = 0; i < 5; i++)
			freq[getIndex(rank, input[i].charAt(0))] ++;
		
		System.out.println(getMaxFreq(freq));
	}

	private static int getMaxFreq(int[] freq) {
		int max = freq[0];
		
		for(int i = 1; i < freq.length; i++)
			max = Math.max(max, freq[i]);
		
		return max;
	}

	private static int getIndex(char[] rank, char c) {
		for(int i = 0; i < rank.length; i++)
			if(c == rank[i])
				return i;
		
		return -1;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
