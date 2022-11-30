import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

public class Dates {
	static int n;
	static String[] months = {"January", "February", "March", "April", "May", "June", 
			                  "July", "August", "September", "October", "November", "December"};
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		int tt = parse(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split("-");
			
			int year = parse(in[0]);
			int month = getMonth(in[1]);
			int day = parse(in[2]);
			
			int add = parse(scan.readLine());
			
			GregorianCalendar cal = new GregorianCalendar(year, month, day);
			cal.add(cal.DAY_OF_MONTH, add);
			
			int ansYear = cal.get(cal.YEAR);
			String ansMonth = getMonth(cal.get(cal.MONTH));
			int ansDay = cal.get(cal.DAY_OF_MONTH);
			
			System.out.println("Case " + (t+1) + ": " + ansYear + "-" + ansMonth + "-" + (ansDay< 10 ? "0" + ansDay : ansDay));
		}
	}
	
	public static String getMonth(int i)
	{
		return months[i];
	}
	
	public static int getMonth(String s)
	{
		for(int i = 0; i < 12; i++)
			if(months[i].equals(s))
				return i;
		return -1;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
