import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class D_505_v2 {
	static int row, col;
	static long minimum;
	
	static int[][] prevMask2 = {{1, 2}, {0, 3}, {0, 3}, {1, 2}};
	static int[][] prevMask3 = {{2, 5}, {3, 4}, {0, 7}, {1, 6}, {1, 6}, {0, 7}, {3, 4}, {2, 5}};
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		row = parse(in[0]);
		col = parse(in[1]);
		
		char[][] ray = new char[row][col];
		for(int i = 0; i < row; i++)
			ray[i] = scan.readLine().toCharArray();
		
		//not possible
		if(row >= 4 && col >= 4)
			System.out.println("-1");
		
		//always possible w/ no change
		else if(row == 1 || col == 1)
			System.out.println("0");
		
		//possible
		else
		{
			HashMap<Integer, Long>[] dp = new HashMap[col];
			
			int[] masks = new int[col];
			for(int i = 0; i < col; i++)
				fill(i, masks, ray);
				
			//long[][] dp = new long[col][1<<row];
			for(int i = 0; i < col; i++)
				dp[i] = new HashMap<Integer, Long>();
			
			long min = recur(0, masks, dp, 0);
			int max = 1 << row;
			for(int i = 1; i < max; i++)
				min = Math.min(min, recur(0, masks, dp, i));
			
			
			System.out.println(min);
		}
	}
	
	public static void fill(int index, int[] masks, char[][] ray) 
	{
		int result = 0;
		
		for(int i = 0; i < row; i++)
			result = (result * 2) + (ray[i][index] - '0');
		
		masks[index] = result;
		
	}

	public static long recur(int index, int[] ray, HashMap<Integer, Long>[] dp, int bitmask)
	{
		if(index >= col)
			return 0;
		
		if(dp[index].containsKey(bitmask))
			return dp[index].get(bitmask);
		
		//get this bitmask nexts
		int[] next = getNext(bitmask);
		
		//try these
		dp[index].put(bitmask, Math.min(recur(index+1, ray, dp, next[0]) + cost(index, ray, next[0]), recur(index+1, ray, dp, next[1]) + cost(index, ray, next[1])) );
		
		//return the min
		return dp[index].get(bitmask);
	}
	
	public static int[] getNext(int current)
	{
		if(col == 2)
		{
			return prevMask2[current];
		}
		
		else
		{
			return prevMask3[current];
		}
	}
	
	public static long cost(int index, int[] ray, int newBitmask)
	{
		
		return Integer.bitCount(newBitmask ^ ray[index]);
	}
	
	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
