import java.util.Arrays;
import java.util.Scanner;

public class CHEFPARTY {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int num =scan.nextInt();
			
			int[] A = new int[num];
			for(int i = 0;i < num; i++)
				A[i] = scan.nextInt();
			
			Arrays.sort(A);
			
			int total = 0;
			
			for(int i = 0; i < num; i++)
			{
				if(A[i] <= total)
					total++;
				else
					break;
			}
			
			System.out.println(total);
		}
	}
}
