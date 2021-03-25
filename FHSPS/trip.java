import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class trip {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		String[] in;
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			double adultCost = Double.parseDouble(in[0]), childCost = Double.parseDouble(in[1]);
			int adults = parse(in[2]), children = parse(in[3]);
			
			System.out.printf("%.2f\n", adultCost * adults + childCost * children);
			
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
