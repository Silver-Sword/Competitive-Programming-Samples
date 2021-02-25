import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ChewingGum {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int types = scan.nextInt();
		int max = scan.nextInt();
		ArrayList<Long> hQ = new ArrayList<Long>();
		long temp;
		
		for(int i = 0; i < types; i++)
		{ 
			temp = scan.nextInt();
			if(temp < max)
				hQ.add(temp);
		}
		
		long total = 0;
		types = hQ.size();
		Collections.sort(hQ);
		
		for(int first = 0; first < types - 1; first++)
		{
			for(int second = first + 1; second < types; second++)
			{
				if(hQ.get(first) + hQ.get(second) < max)
					total++;
				else
					second = types;
			}
		}
		
		System.out.println(total);
	}
}
