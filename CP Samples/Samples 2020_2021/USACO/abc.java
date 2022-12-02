import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class abc {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		int len = in.length;
		
		int[] ray = new int[len];
		for(int i = 0; i < len; i++)
			ray[i] = parse(in[i]);
		
		Arrays.sort(ray);
		
		int a = ray[0];
		int b = ray[1];
		
		long abc = ray[len - 1];
		
		long c = abc - a - b;
		
		out.printf("%d %d %d\n", a, b, c);
		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
