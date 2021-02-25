import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class July20_5 {
	static int n;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			int[] a = new int[n];
			int[] b = new int[n];
			
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			
			for(int i = 0; i < n; i++)
			{
				a[i] = scan.nextInt();
				
				try
				{
					map.put(a[i], map.get(a[i]) + 1);
				}
				catch(Exception e)
				{
					map.put(a[i], 1);
				}
			}
			
			for(int i = 0; i < n; i++)
			{
				b[i] = scan.nextInt();
				
				try
				{
					map.put(b[i], map.get(b[i]) + 1);
				}
				catch(Exception e)
				{
					map.put(b[i], 1);
				}
			}
			
			//check if possible
			if(!possible(map))
				System.out.println(-1);
			
			else
				System.out.println(compare(a, b));
			
		}

	}
	
	public static int compare(int[] a, int[] b)
	{		
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		for(int i = 0; i < n; i++)
		{
			if(map.containsKey(a[i]))
				map.put(a[i], map.get(a[i]) + 1);
			else
				map.put(a[i], 1);
		}
		
		for(int i = 0; i < n; i++)
		{
			if(map.containsKey(b[i]))
				map.put(b[i], map.get(b[i]) - 1);
			else
				map.put(b[i], -1);
		}
		
		ArrayList<Integer> toSwapA = new ArrayList<Integer>();
		ArrayList<Integer> toSwapB = new ArrayList<Integer>();
		for(Entry<Integer, Integer> e : map.entrySet())
		{
			if(e.getValue() != 0)
			{
				if(e.getValue() < 0)
					toSwapB.add(e.getKey());
				else
					toSwapA.add(e.getKey());
			}
		}
		
		int count = 0;
		for(int i = 0; i < toSwapA.size(); i++)
			count += Math.min(toSwapA.get(i), toSwapB.get(i));
		
		
		return count;
		
	}
	
	public static boolean possible(HashMap<Integer, Integer> map)
	{
		for(Entry<Integer, Integer> element : map.entrySet())
		{
			if(element.getValue() % 2 != 0)
				return false;
		}
		
		return true;
	}
}
