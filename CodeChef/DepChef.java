import java.util.Scanner;

public class DepChef {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int num = scan.nextInt();
			
			int[] a = new int[num];
			int[] d = new int[num];
			
			for(int i = 0; i < num; i++)
				a[i] = scan.nextInt();
			for(int i = 0; i < num; i++)
				d[i] = scan.nextInt();
			
			int max = -1;
			
			if(d[0] > a[1] + a[num - 1])
				max = d[0];
			
			if(d[num - 1] > a[0] + a[num - 2] && d[num - 1] > max)
				max = d[num - 1];
			
			for(int i = 1; i < num - 1; i++)
			{
				if(d[i] > max && d[i] > a[i - 1] + a[i + 1])
					max = d[i];
			}
			
			System.out.println(max);
		}
	}
}
