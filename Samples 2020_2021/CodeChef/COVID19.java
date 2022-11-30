import java.util.Scanner;

public class COVID19 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			int[] index = new int[n];
			
			for(int i = 0; i < n; i++)
				index[i] = scan.nextInt();
			
			int i = 1;
			int streak = 1;
			int min = Integer.MAX_VALUE, max = 0;
			
			while(i < n)
			{
				if(index[i] - index[i-1] <= 2)
					streak++;
				else
				{
					if(streak < min)
						min = streak;
					if(streak > max)
						max = streak;
					streak = 1;
				}
				
				i++;
			}
			
			if(streak < min)
				min = streak;
			if(streak > max)
				max = streak;
			
			System.out.println(min + " " + max);
		}
	}

}
