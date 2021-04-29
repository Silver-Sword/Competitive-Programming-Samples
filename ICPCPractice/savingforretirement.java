import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class savingforretirement {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		int bobTime = parse(in[1]) - parse(in[0]);
		int bobMoney = bobTime * parse(in[2]);
		
		int aliceTime = bobMoney / parse(in[4]);
		aliceTime ++;
		
		System.out.println(aliceTime + parse(in[3]));
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
