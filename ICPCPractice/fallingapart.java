import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class fallingapart {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		n = parse(scan.readLine());
		String[] in = scan.readLine().split(" ");
		
		int[] ray = new int[n];
		for(int i = 0 ; i < n; i++)
			ray[i] = parse(in[i]);
		
		Arrays.sort(ray);
		
		int[] scores = new int[2];
		for(int t = n - 1, i = 0; t >= 0; t--, i++)
		{
			scores[i%2] += ray[t];
		}
		
		System.out.println(scores[0] + " " + scores[1]);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
