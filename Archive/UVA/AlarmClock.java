import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AlarmClock {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String in = scan.readLine().trim();
		
		while(!in.equals("0 0 0 0"))
		{
			String[] time = in.split(" ");
			
			int[] time1 = {parse(time[0]), parse(time[1])};
			int[] time2 = {parse(time[2]) + 24, parse(time[3])};
			
			int res = (time2[0] - time1[0]) * 60 + (time2[1] - time1[1]);
			res %= (60 * 24);
			
			System.out.println(res);
			
			in = scan.readLine();
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
