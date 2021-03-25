import java.util.Scanner;

public class night {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			scan.nextLine();
			
			Interval[] ray = new Interval[n];
			
			for(int i = 0; i < n; i++)
			{
				String[] in = scan.nextLine().split(" ");
				ray[i] = new Interval(new Time(in[0]), new Time(in[1]));
			}
			
			int[] timeAwake = {540, 540};
			int[] max = {0, 0};
			
			for(int i = 0; i < n; i++)
			{
				timeAwake[i%2] -= ray[i].minApart;
			}
			
			if(timeAwake[0] == timeAwake[1])
			{
				Time start = new Time("11:00pm");
				Time end = new Time("8:00am");
				
				max[0] = minDist(start, ray[0].start);
				max[1] = minDist(start, ray[1].start);
				
				if(n % 2 == 0)
				{
					max[0] = Math.max(max[0], minDist(ray[n-1].end, end));
					max[1] = Math.max(max[1], minDist(ray[n-2].end, end));
				}
				
				else
				{
					max[0] = Math.max(max[0], minDist(ray[n-2].end, end));
					max[1] = Math.max(max[1], minDist(ray[n-1].end, end));
				}
				
				for(int i = 0; i+2 < n; i+=2)
				{
					max[0] = Math.max(max[0], minDist(ray[i].end, ray[i+2].start));
				}
				
				for(int i = 1; i + 2 < n; i+=2)
				{
					max[1] = Math.max(max[1], minDist(ray[i].end, ray[i+2].start));
				}
				
				System.out.println(max[0] > max[1] ? "FIRST" : "SECOND");
				
				
			}
			
			else
			{
				System.out.println(timeAwake[0] > timeAwake[1] ? "FIRST" : "SECOND");
			}
		}
	}
	
	public static int minDist(Time t1, Time t2)
	{
		return (t2.hours - t1.hours) * 60 + (t2.min - t1.min);
	}
}

class Interval
{
	Time start, end;
	
	int minApart;
	
	public Interval(Time s, Time e)
	{
		start = s;
		end = e;
		
		minApart = (e.hours - s.hours) * 60 + (e.min - s.min);
	}
}

class Time
{
	int hours, min;
	
	public Time(String in)
	{
		int temp = in.indexOf(":");
		hours = Integer.parseInt(in.substring(0, temp));
		min = Integer.parseInt(in.substring(temp + 1, temp+3));
		
		if(hours < 11)
			hours += 12;
	}
}
