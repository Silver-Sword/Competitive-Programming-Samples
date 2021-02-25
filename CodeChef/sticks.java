import java.util.Scanner;
import java.util.TreeSet;

public class sticks {
	static int n;
	public static void main(String[] args)
	{
		int temp;
		
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			TreeSet<Integer> set = new TreeSet<Integer>();
			
			for(int i = 0; i < n ;i++)
			{
				temp = scan.nextInt();
				if(temp != 0)
					set.add(temp);
			}
			
			System.out.println(set.size());
		}
	}
}
