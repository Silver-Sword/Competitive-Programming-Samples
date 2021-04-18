import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class m {
	static int n;
	static int MAX = 262144;
	static HashSet<Integer> bitFlips;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		bitFlips = new HashSet<Integer>();
		generateValidBitFlips();
		
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		ArrayList<Integer> values = new ArrayList<Integer>();
		HashSet<Integer> valid = new HashSet<Integer>();
		
		int num = parse(scan.readLine());
		while(num != -1)
		{
			values.add(num);
			valid.add(num);
			
			num = parse(scan.readLine());
		}
		
		int tt = values.size();
		for(int t = 0; t < tt; t++)
		{
			out.printf("%d:%d\n", values.get(t), solve(values.get(t), valid));
		}
		
		out.flush();
	}

	

	private static int solve(int val, HashSet<Integer> valid) 
	{
		int count = 0;
		int temp;
		
		for(int flip : bitFlips)
		{
			temp = val ^ flip;
			
			if(temp > val && valid.contains(temp))
				count++;
		}
		
		return count;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	private static void generateValidBitFlips() 
	{
		for(int i = 1; i < MAX; i++)
			if(Integer.bitCount(i) <= 2)
				bitFlips.add(i);
	}
}
