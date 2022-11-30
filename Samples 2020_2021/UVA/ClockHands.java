import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClockHands {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String in = scan.readLine();
		
		while(!in.equals("0:00"))
		{
			String[] time = in.split(":");
			int hour = parse(time[0]) % 12;
			int min = parse(time[1]);
			
			double angle1 = (360/12) * hour;
			angle1 += (360/12) * ((double) min / 60.0);
			
			double angle2 = 6 * min;
			
			double res = Math.abs(angle1 - angle2);
			res = Math.min(res, 360 - res);
			
			System.out.printf("%.3f\n", res);
			
			
			in = scan.readLine();
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
