import java.util.HashMap;
import java.util.Scanner;

public class LCM_Round655 {
	static HashMap<Integer, Integer> map;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		map = new HashMap<Integer, Integer>();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			
			if(n % 2 == 0)
			{
				System.out.println(n / 2 + " " + (n / 2));
				continue;
			}
			
			else if(n % 3 == 0)
			{
				System.out.println((n /  3) + " " + ((n*2) / 3));
				continue;
			}
			
			
			int c = ans(n);
			
			System.out.println((n / c) + " " + ((n * (c - 1)) / c));
		}

	}
	
	public static int ans(int num)
	{
		if(map.containsKey(num))
			return map.get(num);
		
		double max = Math.sqrt(num);
		for(int i = 5; i <= max; i+=2 )
		{
			if(num % i == 0)
			{
				map.put(num, i);
				return i;
			}
		}
		
		map.put(num, num);
		return num;
	}

}

//test 10^9