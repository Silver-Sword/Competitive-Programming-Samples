import java.util.Arrays;
import java.util.Scanner;

public class cleanup {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			boolean[] jobs = new boolean[scan.nextInt()];
			int complete = scan.nextInt();
			
			Arrays.fill(jobs, true);
			
			for(int i = 0; i < complete; i++)
				jobs[scan.nextInt() - 1] = false;
			
			String chef = "";
			String assist = "";
			
			int count = 0;
			for(int i = 0; i < jobs.length; i++)
			{
				if(jobs[i])
				{
					if(count % 2 == 0)
						chef += String.valueOf(i + 1) + " ";
					else
						assist += String.valueOf(i + 1) + " ";
					
					count++;
				}
			}
			
			System.out.println(chef);
			System.out.println(assist);
			
		}
	}
}
