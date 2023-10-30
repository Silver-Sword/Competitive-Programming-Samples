import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

public class EugeneArray {
	
	static int count = 0;
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int n = scan.nextInt();
		
		TreeSet<Integer> index = new TreeSet<Integer>();
		LinkedList<Integer> toRemove = new LinkedList<Integer>();

		long[] track = new long[n];
		
		int[] ray = new int[n];
		for(int i = 0; i < n; i++)
		{
			ray[i] = scan.nextInt();
			track[i] = ray[i];
			
			if(ray[i] != 0)
			{
				index.add(i);
				count ++;
			}
		}
		
		for(int dist = 1; dist < n; dist ++)
		{
			for(Integer i : index)
			{
				if(i >= n - dist)
					break;
				
				if(!index.contains(i + 1))
				{
					toRemove.add(i);
					continue;
				}
				
				track[i] += ray[i+dist];
				if(track[i] == 0)
					toRemove.add(i);	
				else
					count++;
			}
			
			while(!toRemove.isEmpty())
				index.remove(toRemove.pop());
		}
		
		System.out.println(count);
	}
	
}
