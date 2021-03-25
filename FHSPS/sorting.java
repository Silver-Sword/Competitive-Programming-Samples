import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class sorting {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			PriorityQueue<Integer> nums = new PriorityQueue<Integer>();
			
			for(int i= 0; i < n; i++)
				nums.add(scan.nextInt());
			
			long count = 0;
			while(nums.size() > 1)
			{
				int temp = nums.poll() + nums.poll();
				nums.add(temp);
				count += temp;
			}
			
			System.out.println(count);
		}
	}
}
