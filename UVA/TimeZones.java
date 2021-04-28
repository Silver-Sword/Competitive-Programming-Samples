import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

public class TimeZones {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		int tt = parse(scan.readLine());
		String[] zone = {"UTC", "GMT", "BST", "IST", "WET", "WEST", "CET", "CEST", "EET", "EEST", "MSK", "MSD", "AST", "ADT", "NST", "NDT", "EST", "EDT", "CST", "CDT", "MST", "MDT", "PST", "PDT", "HST", "AKST", "AKDT", "AEST", "AEDT", "ACST", "ACDT", "AWST" };
		
		double[] offset = {0,     0,     1,     1,     0,     1,	  1, 	  2, 	2, 		3, 		3, 		4, 	  -4, 	-3,		-3.5,  -2.5,  -5, 	 -4,    -6,    -5,   -7,    -6,     -8,    -7,    -10,   -9,   -8,       10,     11,     9.5,   10.5,   8};
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			
			GregorianCalendar cal = new GregorianCalendar();
			int hour, min;
			if(!in[0].contains(":"))
			{
				min = 0;
				if(in[0].equals("noon"))
					hour = 12;
				else
					hour = 0;
			}
			else
			{
				String[] time = in[0].split(":");
				hour = parse(time[0]);
				min = parse(time[1]);
				
				if(hour == 12 && in[1].equals("a.m."))
					hour = 0;
				else if(hour != 12 && in[1].equals("p.m."))
					hour += 12;
			}
			
			cal.set(cal.HOUR_OF_DAY, hour);
			cal.set(cal.MINUTE, min);
			
			int delta = getDelta(in[in.length - 2], in[in.length-1], zone, offset);
			
			cal.add(cal.MINUTE, delta);
			
			System.out.println(toString(cal));
		}
	}
	
	public static String toString(GregorianCalendar c)
	{
		int min = c.get(c.MINUTE);
		int hour = c.get(c.HOUR_OF_DAY);
		
		if(min == 0 && (hour == 0 || hour == 12))
		{
			if(hour == 0)
				return "midnight";
			else
				return "noon";
		}
		
		else
		{
			String am_pm;
			if(hour >= 12)
				am_pm = "p.m.";
			else
				am_pm = "a.m.";
			
			hour = c.get(c.HOUR);
			if(hour == 0) hour = 12;
			String minute = (min < 10) ? "0" + min : String.valueOf(min);
			return hour + ":" + minute + " " + am_pm;
		}
		
	}

	public static int getDelta(String zone1, String zone2, String[] zone, double[] offset) 
	{
		if(zone1.equals(zone2)) return 0;
		
		double offset1 = getOffset(zone1, zone, offset);
		double offset2 = getOffset(zone2, zone, offset);
		
		double d = offset2 - offset1; //need to check on this
		
		return (int) (d * 60);
	}
	
	public static double getOffset(String zone, String[] zones, double[] offset)
	{
		int size = zones.length;
		for(int i = 0; i < size; i++)
			if(zones[i].equals(zone))
				return offset[i];
		
		return 0;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
