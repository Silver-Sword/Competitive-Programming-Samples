import java.util.LinkedList;
import java.util.Scanner;

public class OctChal2019v2 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		int k = 6;
		
		for(int c = 0; c < cases; c++)
		{
			LinkedList<Integer> ls = new LinkedList<Integer>();
			int n = scan.nextInt();
			int[] arr = new int[n];
			for(int i = 0; i < n; i++)
				arr[i] = scan.nextInt();
			
			int i = 0;
			int last = 0;
			int count = 1;
			for(; i < k; i++)
			{
				while(!ls.isEmpty() && arr[i] < arr[ls.peekLast()])
					ls.removeLast();
				
				ls.addLast(i);
				
				if(last != ls.peekFirst())
				{
					last = ls.peek();
					count++;
				}
			}
			
			
			for(; i < n; i++)
			{
				//System.out.println(arr[ls.peek()]);
				
				//check window
				while(!ls.isEmpty() && i - k >= ls.peek())
					ls.removeFirst();
				
				while(!ls.isEmpty() && arr[i] < arr[ls.peekLast()])
					ls.removeLast();
				
				ls.addLast(i);
				
				if(last != ls.peek())
				{
					last = ls.peek();
					count++;
				}
				
			}
			
			System.out.println(count);
			
		}
	}
}
