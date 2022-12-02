import java.util.Scanner;

public class SUPW {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int days = scan.nextInt();
		
		int[] minutes = new int[days];
		for(int d = 0; d < days; d++)
			minutes[d] = scan.nextInt();
		
		int min;
		
		if(minutes[2] <= minutes[1] && minutes[2] <= minutes[0])
			min = recur(minutes, 2, minutes[2]);
		else if(minutes[1] <= minutes[0])
			min = Math.min(recur(minutes, 1, minutes[1]), recur(minutes, 2, minutes[2]));
		else 
			min = Math.min(recur(minutes, 0, minutes[0]), Math.min(recur(minutes, 1, minutes[1]), recur(minutes, 2, minutes[2])));
		
		System.out.println(min);
	}
	
	public static int recur(int[] min, int curPos, int total)
	{
		if(min.length - 3 <= curPos)
		{
			return total ;
		}
		
		int val1, val2, val3;
		val1 = Integer.MAX_VALUE;
		val2 = Integer.MAX_VALUE;
		val3 = Integer.MAX_VALUE;
		
		int min1 = min[curPos + 1], min2 = min[curPos + 2], min3 = min[curPos + 3];
		
		if(min3 <= min2 && min3 <= min2 )
			val3 = recur(min, curPos + 3, total + min[curPos + 3]);
		
		else
		{
			if(min2 <= min1 || (curPos + 4 < min.length && min[curPos + 4] >= min2 && min[curPos + 4] >= min3))
			{
				val2 = recur(min, curPos + 2, total + min[curPos + 2]);
				val3 = recur(min, curPos + 3, total + min[curPos + 3]);
			}
			
			else
			{
				val2 = recur(min, curPos + 2, total + min[curPos + 2]);
				val1 = recur(min, curPos + 1, total + min[curPos + 1]);
				val3 = recur(min, curPos + 3, total + min[curPos + 3]);
			}
		}

		
		
		
		return Math.min(val1, Math.min(val2, val3));
	}
}
