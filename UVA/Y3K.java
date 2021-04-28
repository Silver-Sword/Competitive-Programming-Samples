import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

public class Y3K {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		
		while(!(in[0].equals("0") && in[1].equals("0") && in[2].equals("0")))
		{
			int days = (int) Long.parseLong(in[0]);
			
			int day = parse(in[1]);
			int month = parse(in[2]);
			int year = parse(in[3]);
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(year, month-1, day);
			calendar.add(calendar.DAY_OF_MONTH, days);
			//calendar.
			System.out.println(calendar.get(calendar.DAY_OF_MONTH) + " " + (calendar.get(calendar.MONTH)+1) + " " + calendar.get(calendar.YEAR));
			
			in = scan.readLine().split(" ");
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
