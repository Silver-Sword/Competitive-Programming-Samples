import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

public class electricity {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		int tt = parse(scan.readLine());
		int elect;
		
		while(tt != 0)
		{
			long sum = 0;
			int count = 0;
			GregorianCalendar cal = new GregorianCalendar();
			elect = setCal(cal, scan.readLine().split(" "));
			for(int i = 1; i < tt; i++)
			{
				cal.add(cal.DAY_OF_MONTH, 1);
				in = scan.readLine().split(" ");
				
				if(equals(cal, in))
				{
					sum += (parse(in[3])) - elect;
					elect = parse(in[3]);
					count++;
				}
				
				else
					elect = setCal(cal, in);
				
			}
			
			System.out.println(count + " " + sum);
			tt = parse(scan.readLine());
		}
	}


	private static boolean equals(GregorianCalendar cal, String[] in) 
	{
		boolean day = cal.get(cal.DAY_OF_MONTH) == parse(in[0]);
		boolean month = cal.get(cal.MONTH) + 1 == parse(in[1]);
		boolean year = cal.get(cal.YEAR) == parse(in[2]);
		
		return day && month && year;
	}


	private static int setCal(GregorianCalendar cal, String[] in) 
	{
		int day = parse(in[0]);
		int month = parse(in[1]) - 1;
		int year = parse(in[2]);
		
		cal.set(year, month, day);
		
		return Integer.parseInt(in[3]);
	}


	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
