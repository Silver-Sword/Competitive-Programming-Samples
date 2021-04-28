import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class married {
	public static void main(String[] args) throws IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			String[] time1 = scan.readLine().split(" ");
			String[] time2 = scan.readLine().split(" ");
			
			boolean overlap = ! ( lessThan(time2[1], time1[0]) || lessThan(time1[1], time2[0]) );
			
			System.out.print("Case " + (t+1) + ": ");
			System.out.println(overlap ? "Mrs Meeting" : "Hits Meeting");
		}
	}

	public static boolean lessThan(String time1, String time2) 
	{
		int min1 = getMin(time1.split(":"));
		int min2 = getMin(time2.split(":"));
		
		return min1 < min2;
	}
	
	public static int getMin(String[] clock)
	{
		return  (60 * Integer.parseInt(clock[0])) + Integer.parseInt(clock[1]);
	}
}
