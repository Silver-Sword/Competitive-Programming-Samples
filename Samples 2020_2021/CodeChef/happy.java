import java.util.Scanner;


public class happy{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int nums = scan.nextInt();
			boolean[] valid = new boolean[nums];
			int[] ray = new int[nums];
			for(int i = 0; i < nums; i++)
			{
				ray[i] = scan.nextInt();
				valid[i] = ray[i] <= nums ? true : false;
			}
			
			System.out.println(ans(ray, valid) ? "Truly Happy" : "Poor Chef");
		}
	}
	
	public static boolean ans(int[] ray, boolean[] isValidIndex)
	{
		for(int i = 0; i < ray.length - 1; i++)
		{
			int num = ray[i];
			
			if(isValidIndex[i])
			{
				for(int j = i + 1; j < ray.length; j++)
				{
					if(num != ray[j] && isValidIndex[j])
					{
						if(ray[num - 1] == ray[ray[j] - 1])
							return true;
					}
				}
			}
		}
		
		return false;
	}
}