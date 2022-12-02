import java.util.Scanner;

public class ORMATRIX {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			boolean one = false;
			int ySize = scan.nextInt();
			int xSize = scan.nextInt();
			int[][] ray = new int[ySize][xSize];
			boolean[][] ones = new boolean[ySize][xSize];
			
			for(int y = 0; y < ones.length; y++)
				for(int x = 0; x < ones[0].length; x++)
					ones[y][x] = false;
			scan.nextLine();
			String temp;
			for(int y = 0; y < ySize; y++)
			{ 				
				temp = scan.nextLine();
				for(int x = 0; x < xSize; x++)
				{
					ray[y][x] = Integer.parseInt(temp.substring(x, x+1));
					
					if(!one && ray[y][x] == 1)
						one = true;
					
					if(ray[y][x] == 1)
					{
						for(int k = 0; k < ray.length; k++)
						{
							ones[k][x] = true;
							ones[y][k] = true;
						}
					}
				}
			}
			
			if(one)
			{
				for(int y = 0; y < ySize; y++)
				{
					for(int x = 0; x < xSize; x++)
					{
						if(ray[y][x] == 1)
							System.out.print("0");
						else if(ones[y][x] == true)
							System.out.print("1");
						else
							System.out.print("2");
						
						if(x != xSize - 1)
							System.out.print(" ");
					}
					
					System.out.println();
				}
			}
			
			else
			{
				for(int y = 0; y < ySize; y ++)
				{
					for(int x = 0; x < xSize; x++)
					{
						System.out.print("-1");
						
						if(x != xSize - 1)
							System.out.print(" ");
					}
					
					System.out.println();
				}
			}
		}
	}
}
