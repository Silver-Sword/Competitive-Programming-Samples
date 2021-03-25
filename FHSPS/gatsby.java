import java.util.Scanner;

public class gatsby {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			//daisy
			int n = scan.nextInt();
			int[] dLoc = new int[n];
			int[][] interval = new int[n][2];
			for(int i = 0; i < n; i++)
			{
				dLoc[i] = scan.nextInt();
				if(i != 0)
					interval[i][0] = interval[i-1][1];
				
				interval[i][1] = interval[i][0] + scan.nextInt();
			}
			
			//tom
			int m = scan.nextInt();
			int[] tLoc = new int[m];
			int[][] tInt = new int[m][2];
			for(int i = 0; i < m; i++)
			{
				tLoc[i] = scan.nextInt();
				if(i != 0)
					tInt[i][0] = tInt[i-1][1];
				
				tInt[i][1] = tInt[i][0] + scan.nextInt();
			}
			
			int together = 1440;
			
			int index1 = 0, index2 = 0;
			while(index1 < n && index2 < m)
			{
				//tom starts before end
				if(tInt[index2][0] <= interval[index1][1])
				{
					if(tLoc[index2] == dLoc[index1])
					{
						//do stuff
						together -= Math.min(interval[index1][1], tInt[index2][1]) - Math.max(interval[index1][0], tInt[index2][0]);
						
					}
					
					//update
					if(tInt[index2][0] == interval[index1][1])
					{
						index1++;
						index2++;
					}
					
					else if(tInt[index2][1] < interval[index1][1])
						index2++;
					else
						index1++;
				}
				
				//daisy ends before tom interval starts
				else
				{
					if(tInt[index2][1] < interval[index1][1])
						index2++;
					else
						index1++;
				}
			}
			
			System.out.println(together);
		}
	}
}
