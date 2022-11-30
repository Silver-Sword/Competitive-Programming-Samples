import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_Ringroad {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		
		in = scan.readLine().split(" ");
		n = parse(in[0]);
		int m = parse(in[1]);
			
			in = scan.readLine().split(" ");
			int pos = 1;
			int next;
			long total = 0;
			for(int i = 0; i < m; i++)
			{
				next = parse(in[i]);
				if(pos > next)
				{
					total += (n - pos) + (next);
				}
				
				else
				{
					total += next - pos;
				}
				
				pos = next;
			}
		System.out.println(total);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
