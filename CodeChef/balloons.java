import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class balloons {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int days = scan.nextInt();
		long have = scan.nextLong();
		
		long[] want = new long[days];
		long[] candies = new long[days];
		long totalWant = 0;
		
		for(int i = 0; i < days; i++)
		{
			want[i] = scan.nextLong();
			totalWant += want[i];
		}
		for(int i = 0; i < days; i++)
			candies[i] = scan.nextLong();
		
		if(totalWant <= have)
		{
			System.out.println(0);
		}
		else if(have == 0)
		{
			long max = want[0] * candies[0];
			for(int i = 0; i < days; i++)
			{
				long temp = want[i] * candies[i];
				if(temp > max)
					max = temp;
			}
				
		}
		else
		{
			ArrayList<Long> total = new ArrayList<Long>();
			ArrayList<Integer> index = new ArrayList<Integer>();
			long max = want[0] * candies[0];
			int maxIndex = 0;
			for(int i = 0; i < days; i++)
			{			
				
				total.add(want[i] * candies[i]);
				index.add(i);
				if(total.get(i) > max)
				{
					max = total.get(i);
					maxIndex = i;
				}
			}
			
			for(int i = days - 1; i >= 0; i--)
			{
				if(total.get(i) <= (want[maxIndex] - have) * candies[maxIndex])
				{
					total.remove(i);
					index.remove(i);
				}
			}
			
			if(total.size() > 1)
			{
				while(have > 0)
				{
					long firstMax, secondMax;
					int firstIndex, secondIndex;
					
					if(total.get(0) > total.get(1))
					{
						firstMax = total.get(0);
						firstIndex = 0;
						secondMax = total.get(1);
						secondIndex = 1;
					}
					else
					{
						firstMax = total.get(1);
						firstIndex = 1;
						secondMax = total.get(0);
						secondIndex = 0;
					}
					
					for(int i = 2; i < total.size(); i++)
					{
						if(total.get(i) > firstMax)
						{
							secondMax = firstMax;
							secondIndex = firstIndex;
							firstMax = total.get(i);
							firstIndex = i;
						}
					}
					
					long takeAway = ((firstMax - secondMax) / candies[index.get(firstIndex)]) + 1;
					takeAway = takeAway > have ? have : takeAway;
					
					total.set(firstIndex, total.get(firstIndex) - (takeAway * candies[index.get(firstIndex)]));
					have -= takeAway;
				}
				
				max = total.get(0);
				for(int i = 1; i < total.size(); i++)
					if(total.get(i) > max)
						max = total.get(i);
				
				System.out.println(max >= 0 ? max : 0);
			}
			
			else
			{
				int in = index.get(0);
				long ans = (want[in] - have) * candies[in];
				
				System.out.println(ans >= 0 ? ans : 0);
			}
		}
		
		
	}
}
