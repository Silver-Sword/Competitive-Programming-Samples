import java.io.IOException;
import java.util.Scanner;

public class WatchingWatches {
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		int n, k;
		
		while(scan.hasNext())
		{
			n = scan.nextInt();
			k = scan.nextInt();
			
			double ans = 720;
			ans /= Math.abs(k - n);
			
			ans *= (86400 - k);
			
			ans %= 43200;
			
			int min = (int) (Math.round(ans) % (24 * 60));
			int hour = min / 60;
			min %= 60;
			hour %= 12;
			
			if(hour == 0) hour = 12;
			
			String time = (hour < 10 ? "0" + hour : hour) + ":";
			time += (min < 10 ? "0" + min : min);
			
			
			System.out.println(n + " " + k + " " + time);
			
		}
		
		scan.close();
	}
}
