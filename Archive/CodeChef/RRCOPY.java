/*import java.util.ArrayList;
import java.util.Scanner;

public class RRCOPY {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			ArrayList<Integer> e = new ArrayList<Integer>();
			int nums = scan.nextInt();
			
			for(int i = 0; i < nums; i++)
			{
				int temp = scan.nextInt();
				if(!e.contains(temp))
					e.add(temp);
			}
			
			System.out.println(e.size());
		}
	}
}

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RRCOPY {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int[] e = new int[scan.nextInt()];
			
			for(int i = 0; i < e.length; i++)
			{
				e[i] = scan.nextInt();
			}
			Arrays.sort(e);
			
			int total = 1;
			for(int i = 1; i < e.length; i++)
				if(e[i] != e[i - 1])
					total++;
			System.out.println(total);
		}
	}
}

