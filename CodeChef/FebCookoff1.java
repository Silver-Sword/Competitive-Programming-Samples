import java.util.Scanner;

public class FebCookoff1 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int num = scan.nextInt();
			int budget = scan.nextInt();
			
			int[] width = new int[num];
			int[] height = new int[num];
			int[] price = new int[num];
			
			for(int i = 0; i < num; i++)
			{
				width[i] = scan.nextInt();
				height[i] = scan.nextInt();
				price[i] = scan.nextInt();
			}
			
			int maxArea = 0;
			
			for(int i = 0;i < num; i++)
			{
				if(price[i] <= budget && maxArea < width[i] * height[i])
					maxArea = width[i] *height[i];
			}
			
			if(maxArea == 0)
				System.out.println("no tablet");
			else
				System.out.println(maxArea);
		}
	}
}
