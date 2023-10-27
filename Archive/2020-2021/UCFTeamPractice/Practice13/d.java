import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class d {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		
		int d1 = parse(in[0]);
		int d2 = parse(in[1]);
		
		int[] freq = new int[d1+ d2 + 1];
		
		for(int i = 1; i <= d1; i++)
			for(int j = 1; j <= d2; j++)
				freq[i+j] ++;
		
		int max = 0;
		for(int i = 0; i <= d1 + d2; i++)
			if(freq[i] > max)
				max = freq[i];
		
		for(int i = 0; i <= d1 + d2; i++)
			if(freq[i] == max)
				out.println(i);
		
		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
