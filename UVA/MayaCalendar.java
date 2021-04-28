import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MayaCalendar {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = parse(scan.readLine());
		String[] in;
		System.out.println(tt);
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			int days;
			
			//convert year to days
			days = (Integer.parseInt(in[2])) * 365;
			
			//convert day to days
			days += Integer.parseInt(in[0].substring(0, in[0].length()-1)) ;
			
			//convert month to days
			days += HaabMonthtoDay(in[1]) * 20;
			
			
			
			//convert to Tzolkin
			int year = (int)days / (13 * 20);
			String word = TzolkinWord(days % (13 * 20));
			int date = ((days % (13 * 20)) % 13) + 1;
			
			System.out.println(date + " " + word + " " + year);
			
		}
	}
	
	public static String TzolkinWord(int days)
	{
		String[] months = {"imix", "ik", "akbal", "kan", "chicchan", "cimi", 
				"manik", "lamat", "muluk", "ok", "chuen", "eb", "ben", "ix", 
				"mem", "cib", "caban", "eznab", "canac", "ahau"};
		
		return months[days % 20];
	}
	
	public static int HaabMonthtoDay(String month)
	{
		String[] months = {"pop", "no", "zip", "zotz", "tzec", "xul", "yoxkin", "mol", "chen", 
				"yax", "zac", "ceh", "mac", "kankin", "muan", "pax", "koyab", "cumhu", "uayet"};
		
		
		for(int i = 0;i < 19; i++)
			if(month.equals(months[i]))
				return i;
		
		return 0;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
