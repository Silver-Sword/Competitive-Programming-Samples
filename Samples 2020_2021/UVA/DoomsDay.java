import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

public class DoomsDay {
	static int n;
	
	static String[] day = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		int tt = parse(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			GregorianCalendar cal = new GregorianCalendar(2011, parse(in[0])-1, parse(in[1]));
			
			System.out.println(day[cal.get(cal.DAY_OF_WEEK) - 1]);
			//cal.d
			
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
