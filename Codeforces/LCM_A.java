import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCM_A {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		String[] in;
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			int l = parse(in[0]);
			int r = parse(in[1]);
			
			if(l * 2 > r)
				System.out.println("-1 -1");
			else
				System.out.println(l + " " + (2*l));
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
