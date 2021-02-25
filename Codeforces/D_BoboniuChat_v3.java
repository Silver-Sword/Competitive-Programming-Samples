import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class D_BoboniuChat_v3 {
	static int n, d, m;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		
		ArrayList<Integer> lower = new ArrayList<Integer>();
		ArrayList<Integer> higher = new ArrayList<Integer>();
		
		n = parse(in[0]);
		d = parse(in[1]);
		m = parse(in[2]);
		
		in = scan.readLine().split(" ");
		int temp;
		for(int i = 0; i < n ;i++)
		{
			temp = parse(in[i]);
			if(temp <= m)
				lower.add(temp);
			else
				higher.add(temp);
		}
		
		Collections.sort(lower, Collections.reverseOrder());
		Collections.sort(higher, Collections.reverseOrder());
		
		System.out.println(func(n, 0, 0, lower, higher));
	}
	
	public static long func(int daysLeft, int i, int j,  ArrayList<Integer> lower, ArrayList<Integer> higher)
	{
		long ans = 0;
		long max = 0;
		long lowerSize = lower.size();
		
		long sizeHigh = higher.size();
		
		int x = 1;
		if(!higher.isEmpty())
		{
			while(j < sizeHigh && n > (x-1) * (d + 1) + 1)
			{
				ans += higher.get(j);
				j++;
				x++;
			}
		}
		
		x--;
		daysLeft = n - ((x-1)*(d+1) + 1);
		
		if(!lower.isEmpty())
			while(daysLeft > 0 && i < lowerSize)
			{
				daysLeft --;
				ans += lower.get(i);
				i++;
			}
		
		max = ans;
		
		if(j == sizeHigh) j--;
		
		while(j >= 0 && i < lowerSize)
		{
			ans -= higher.get(j);
			x--;
			j--;
			
			for( ; i < n - ((x-1) * (d+1) + 1)  && i < lowerSize; i++ )
			{
				ans += lower.get(i);
			}
			
			if(ans > max)
				max = ans;
		}
		
		return max;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
