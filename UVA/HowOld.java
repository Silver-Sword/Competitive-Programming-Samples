import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HowOld {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[][] date = new String[2][3];
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			scan.readLine();
			date[1] = scan.readLine().split("/");
			date[0] = scan.readLine().split("/");
			
			//date 1 is the b-day, date2 is the current date
			int[] date1 = parse(date[0]);
			int[] date2 = parse(date[1]);
			
			System.out.print("Case #" + (t+1) + ": ");
			
			if(compareTo(date1, date2) > 0)
				System.out.println("Invalid birth date");
			
			else if(compareTo(date1, date2) == 0)
				System.out.println(0);
			else
			{
				int yearsOld = calc(date1, date2);
				
				if(yearsOld > 130)
					System.out.println("Check birth date");
				else
					System.out.println(yearsOld);
			}
		}
	}

	public static int calc(int[] date1, int[] date2) 
	{
		int years = date2[2] - date1[2];
		
		if(date1[1] > date2[1] || (date1[1] == date2[1] && date1[0] > date2[0]))
		{
			years--;
		}
		
		return years;
	}

	public static int compareTo(int[] date1, int[] date2) 
	{
		for(int i = 2; i >= 0; i--)
		{
			if(date1[i] != date2[i])
				return date1[i] - date2[i];
		}
		
		return 0;
	}

	public static int[] parse(String[] num)
	{
		int[] ans = new int[3];
		for(int i = 0; i < 3; i++)
			ans[i] = Integer.parseInt(num[i]);
		return ans;
	}
}
