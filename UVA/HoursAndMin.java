import java.util.Scanner;

public class HoursAndMin {
	static int n;
	public static void main(String[] args) throws NumberFormatException 
	{
		Scanner scan = new Scanner(System.in);
		boolean[] angle = new boolean[181];
		fillAngle(angle);
		
		while(scan.hasNext())
		{
			int query = scan.nextInt();
			
			System.out.println(angle[query] ?  "Y" : "N");
		}
	}

	public static void fillAngle(boolean[] angle) 
	{
		int hand1, hand2;
		for(int hour = 0; hour < 12; hour++)
		{
			hand1 = (360 / 12) * hour;
			for(int min = 0; min < 60; min++)
			{
				if(min != 0 && min % 12 == 0) hand1 += ((360 / 12) / 5);
				
				hand2 = (360 / 60) * min;
				
				int theta = Math.min((hand1 - hand2 + 360) % 360, (hand2 - hand1 + 360) % 360);
				angle[theta] = true;
			}
		}
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
