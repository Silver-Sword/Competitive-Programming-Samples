import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;

public class zeroRemainderArray {
	
	/*public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++) {
			int n = scan.nextInt();
			int k = scan.nextInt();
			
			int[] ray = new int[n];
			for(int i = 0; i < n; i++)
				ray[i] = (k - (scan.nextInt() % k)) % k;
			
			Arrays.sort(ray);
			
			long highestCount = 0;
			long highest = 0;
			long last = 0;
			
			int count = 0;
			
			for(int i = 0; i < n; i++)
			{
				if(ray[i] == 0)
					continue;
				
				if(last == ray[i])
				{
					count++;
					
					if(i == n-1)
					{
						if(count >= highestCount)
						{
							highestCount = count;
							highest = ray[i];
						}
					}
				}
				
				else
				{
					if(count >= highestCount)
					{
						highestCount = count;
						highest = ray[i];
					}
					
					count = 1;
					last = ray[i];
				}
			}
			
			if(count >= highestCount)
			{
				highestCount = count;
				highest = ray[ray.length - 1];
			}
			
			System.out.println(Math.max(0, ((highestCount-1)*k) + highest + 1));
			
		}
	} */

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();

		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			long k = scan.nextInt();
			
			LinkedList<Long> list = new LinkedList<Long>();
			for(int i = 0; i < n; i++)
				list.add((k - (scan.nextInt() % k)) % k);
			
			Collections.sort(list);
			
			while(!list.isEmpty() && list.getFirst() == 0)
				list.pop();
			
			
			HashMap<Long, Long> multiply = new HashMap<Long, Long>();
			while(!list.isEmpty())
			{
				long element = list.pop();
				
				if(multiply.containsKey(element))
					multiply.put(element, multiply.get(element) + 1);
				else
					multiply.put(element, (long) 1);
				
			}
			
			long max = 0;
			for(Entry<Long, Long> e : multiply.entrySet())
			{
				if((e.getValue() - 1) * k + e.getKey() > max)
					max = (e.getValue() - 1) * k + e.getKey();
			}
			
			
			System.out.println(max == 0 ? 0 : max+1);
			
		}
	}

	/*public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();

		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			int k = scan.nextInt();
			
			LinkedList<Integer> list = new LinkedList<Integer>();
			for(int i = 0; i < n; i++)
				list.add((k - (scan.nextInt() % k)) % k);
			
			long count = 0;
			long x = 0;
			
			Collections.sort(list);
			
			while(!list.isEmpty() && list.getFirst() == 0)
				list.pop();
			
			
			while(!list.isEmpty())
			{
				int element = list.pop();
				if(x == element)
				{
					x++;
					count++;
				}
				
				else if(x < element)
				{
					count += (element - x);
					x = element;
					count++;
					x++;
				}
				
				else
				{
					list.add(element + k);
				}
			}
			
			System.out.println(count);
			
		}
	}*/
}
