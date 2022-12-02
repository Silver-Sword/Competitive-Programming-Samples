import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.GregorianCalendar;

public class Cancer {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String in;
		int tt = parse(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine();
			
			int month = parse(in.substring(0, 2)) - 1;
			int day = parse(in.substring(2, 4));
			int year = parse(in.substring(4));
			
			GregorianCalendar cal = new GregorianCalendar(year, month, day);
			cal.add(cal.WEEK_OF_YEAR, 40);
			
			out.print((t+1) + " ");
			out.printf("%02d/%02d/%04d ", cal.get(cal.MONTH) + 1, cal.get(cal.DAY_OF_MONTH), cal.get(cal.YEAR));
			
			out.println(getSign(cal));
			out.flush();
		}
	}
	
	public static String getSign(GregorianCalendar cal)
	{
		int month = cal.get(cal.MONTH);
		int day = cal.get(cal.DAY_OF_MONTH);
		
		int[][] interval = {{0, 1, 0, 20},  //cap
							{0, 21, 1, 19}, //aqua
							{1, 20, 2, 20}, //pisces
							{2, 21, 3, 20}, //aries
							{3, 21, 4, 21}, //taur
							{4, 22, 5, 21}, //gem
							{5, 22, 6, 22},
							{6, 23, 7, 21},
							{7, 22, 8, 23},
							{8, 24, 9, 23},
							{9, 24, 10, 22},
							{10, 23, 11, 22},
							{11, 23, 11, 31}};
		
		
		String[] ans = {"capricorn", "aquarius", "pisces", "aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius", "capricorn" };
		
		for(int i = 0; i < ans.length; i++)
		{
			if(month <= interval[i][2] && (month < interval[i][2] || day <= interval[i][3]))
			{
				return ans[i];
			}
		}
		
		return "NA";
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}

}
