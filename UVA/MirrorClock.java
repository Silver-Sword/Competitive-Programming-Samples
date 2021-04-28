import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MirrorClock {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in;
		int tt = parse(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(":");
			
			int hour = parse(in[0]);
			int min = parse(in[1]);
			
			String flipHour = String.valueOf(flip(hour, 6, min));
			
			String flipMin = String.valueOf( flip(min, 30, -1) % 60 );
			
			if(flipHour.length() == 1)
				flipHour = "0" + flipHour;
			if(flipMin.length() == 1)
				flipMin = "0" + flipMin;
			
			System.out.println( flipHour + ":" + flipMin );
		}
	}

	public static int flip(int time, int mid, int min) {
		if(time == 12 && mid == 6)
			time = 0;
		
		int d = time - mid;
		
		if(mid == 6 && min != 0)
		{
			d++;
		}
		
		
		if(mid - d == 0 && mid == 6)
			return 12;
		
		
		return mid - d;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}

}
