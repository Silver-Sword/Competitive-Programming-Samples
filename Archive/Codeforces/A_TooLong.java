import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A_TooLong {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			String word = scan.readLine();
			
			if(word.length() <= 10)
				System.out.println(word);
			else
				System.out.println((String.valueOf(word.charAt(0))) + (word.length()-2) + word.charAt(word.length()-1));
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
