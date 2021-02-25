import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LongVoyage_Div2B_Round660 {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			n = parse(scan.readLine());
			int[] ray = new int[n];
			Arrays.fill(ray, 9);
			
			for(int i = n-1, count = 0; i >= 0 && count * 4 < n; i--, count++)
				ray[i] = 8;
			
			for(int i : ray)
				System.out.print(i);
			System.out.println();
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
