import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class a {
	static int[] segments = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int n = Integer.parseInt(scan.readLine());
		
		if(!getAns(n, out))
			out.println("Impossible");
		out.flush();
	}
	
	public static boolean getAns(int n, PrintWriter out)
	{
		int seg1 = 0, seg2;
		for(int h = 0; h <= 24; h++)
		{
			seg1 = getSeg(h);
			for(int m = 0; m <= 60; m++)
			{
				seg2 = getSeg(m);
				if(seg1 + seg2 == n)
				{
					printTime(h, m, out);
					return true;
				}
			}
		}
		
		return false;
	}

	private static void printTime(int h, int m, PrintWriter out) 
	{
		out.printf("%02d", h);
		out.print(":");
		out.printf("%02d", m);
	}

	public static int getSeg(int time) 
	{
		if(time < 10)
			return segments[time] + segments[0];
		
		return segments[time / 10] + segments[time % 10];
	}
}

