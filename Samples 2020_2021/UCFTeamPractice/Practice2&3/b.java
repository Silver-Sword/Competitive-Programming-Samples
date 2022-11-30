import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(scan.readLine());
		
		if(n % 2 == 0)
		{
			System.out.println("Alice\n1");
		}
		
		else
			System.out.println("Bob");
	}
}
