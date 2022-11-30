import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Feb21_3 
{
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String in;
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			String s = scan.readLine();
			int target = minute(s);
			
			n = parse(scan.readLine());
			
			for(int i = 0; i < n; i++)
			{
				in = scan.readLine().trim();
				int l = minute(in.substring(0, 8));
				int r = minute(in.substring(9));
				
				if(valid(target, l, r))
					out.print(1);
				else
					out.print(0);
			}
			
			out.println();
		}

		out.flush();
	}
	
	public static boolean valid(int target, int l, int r)
	{
		return l <= target && r >= target;
	}
	
	public static int minute(String time)
	{
		String[] temp = time.split(" ");
		boolean morning = temp[1].equals("AM");
		
		String[] clock = temp[0].split(":");
		int hour = parse(clock[0]);
		int min = parse(clock[1]);
		
		// deal w/ hour
		if(hour == 12)
		{
			if(morning)
				hour = 0;
		}
		
		else
		{
			if(!morning)
				hour += 12;
		}
		
		min += hour * 60;
		
		return min;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
