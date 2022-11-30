import java.util.Arrays;
import java.util.Scanner;

public class MarLunch03 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		scan.nextLine();

		for(int c = 0; c < cases; c++)
		{
			int cities = scan.nextInt();
			int k = scan.nextInt();
			
			int[] ray = new int[cities - 1];
			int start = scan.nextInt();
			for(int i = 0; i < cities - 1; i++)
			{
				ray[i] = scan.nextInt();
			}
				Arrays.sort(ray);
				
				double index = findLoc(start, ray);
				
				boolean ans;
				if(index == -1)
					ans = traverse(k, 1, ray, 0) && start + k >= ray[0];
				else if(index >= ray.length)
					ans = traverse(k, -1, ray, ray.length - 1) && start - k <= ray[ray.length - 1];
				
				else
				{
					if(index == (int) index)
					{
						if(traverse(k, -1, ray, (int) index) && traverse(k, 1, ray, (int) index))
						{
							if(traverse(k, 2, ray, (int) index) && traverse(k, 2, ray, (int) (index+1)))
								ans = true;
							else if(traverse(k, -2, ray, (int) index) && traverse(k, -2, ray, (int) (index-1)))
								ans = true;
							else
								ans = false;
						}
						
						else
							ans = false;
					}
					
					else
					{
						if(start + k < ray[(int) (index + 0.5)] || start - k > ray[(int) (index - 0.5)])
							ans = false;
						else if(traverse(k, -1, ray, (int) (index - 0.5)) && traverse(k, 1, ray, (int)(index + 0.5)) && ray[(int) (index-0.5)] + k >= ray[(int) (index+0.5)])
						{
							if(traverse(k, 2, ray, (int) (index+.5)) && traverse(k, 2, ray, (int) (index+1.5)) && start + k >= ray[(int) (index + 1.5)])
								ans =  true;
							else if(traverse(k, -2, ray, (int) (index-0.5)) && traverse(k, -2, ray, (int) (index-1.5)) && start - k <= ray[(int) (index - 1.5)])
								ans = true;
							else
								ans = false;
						}
						else
							ans = false;
					}
				}
				System.out.println(ans ? "YES" : "NO");	
			
		}
	}
	
	public static double findLoc(int i, int[] ray)
	{
		if(i <= ray[0])
			return -1;
		
		if(i >= ray[ray.length - 1])
			return ray.length;
		
		int min = 0, max = ray.length - 1, mid = (min + max) / 2;
		while(min + 1 < max)
		{
			if(ray[mid] == i)
				return mid;
			
			if(ray[mid] > i)
			{
				max = mid;
			}
			
			else
				min = mid;
			
			mid = (max + min) / 2;
		}
		
		return (double) (max + mid) / 2.0;
	}
	
	public static boolean traverse(int k, int dir, int[] ray, int start)
	{
		for(int i = start + dir; i >= 0 && i < ray.length; i += dir)
		{
			if(Math.abs(ray[i] - ray[i-dir]) > k)
				return false;
		}
		
		return true;
	}
}	
