import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NameExtention {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		int tt = parse(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			n = parse(scan.readLine());
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
