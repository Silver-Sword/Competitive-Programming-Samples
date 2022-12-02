import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Round655_ProbC {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
			
		ArrayList<Long> list = new ArrayList<Long>();
		TreeSet<Long> ordered = new TreeSet<Long>();
			
		for(int i = 0; i < n; i++)
		{
			list.add(scan.nextLong());
			ordered.add(list.get(i));
		}
		
		while(list.size() > 1)
		{
			combine(getSmallestIndex(list, ordered), list, ordered);
		}
			
		System.out.println(list.get(0));
		
	}
	
	public static int getSmallestIndex(ArrayList<Long> list, TreeSet<Long> orderedList)
	{
		return list.indexOf(orderedList.first());
	}
	
	public static void combine(int index, ArrayList<Long> list, TreeSet<Long> ordered)
	{
		ordered.pollFirst();
		
		long a, b;
		if(index != list.size() - 1)
		{
			b = list.remove((index + 1) % list.size());
			a = list.remove((index - 1 + list.size()) % list.size());
		}
		
		else
		{
			a = list.remove((index - 1 + list.size()) % list.size());
			b = list.remove((index) % list.size());
		}
		
		
		if(index != 0)
			list.set((index - 1 + list.size()) % list.size(), a+b);
		else
			list.set(0, a+b);
		ordered.remove(a);
		ordered.remove(b);
		ordered.add(a+b);
	}

}
