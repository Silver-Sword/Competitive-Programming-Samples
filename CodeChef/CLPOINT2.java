import java.util.ArrayList;
import java.util.Scanner;

public class CLPOINT2 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int c = 0; c < cases; c++)
		{
			int lines = scan.nextInt();
			int dimensions = scan.nextInt();
			int cost = 0;
			
			int[][] points = new int[lines][dimensions];
			
			for(int y = 0; y < lines; y++)
				for(int x = 0; x < dimensions; x++)
					points[y][x] = scan.nextInt();
			
			int minIndex = 0;
			int minCost = 0;
			
			for(int y = 1; y < lines; y++)
				for(int x = 0; x < dimensions; x++)
					minCost += Math.pow(points[0][x] - points[y][x], 2);
			
			for(int currentLine = 1; currentLine < dimensions; currentLine++)
			{
				cost = 0;
				for(int y = 0; y < lines; y++)
				{
					for(int x = 0; x < dimensions; x++)
					{
						cost += Math.pow(points[currentLine][x] - points[y][x], 2);
					}
				}
				
				if(cost < minCost)
				{
					minCost = cost;
					minIndex = currentLine;
					list = new ArrayList<Integer>();
					list.add(minIndex);
				}
				
				else if(cost == minCost)
				{
					list.add(currentLine);
				}
			}
			
			if(list.size() > 1)
			{
				while(list.size() > 1)
				{
					int[] one = points[list.get(0)];
					int[] two = points[list.get(1)];
					
					if(one[0] > two[0])
						list.remove(0);
					else if(one[0] < two[0])
						list.remove(1);
					else
						list.remove(0);
				}
				
				minIndex = list.get(0);
			}
			
			for(int i = 0; i < dimensions; i++)
			{
				if(i == dimensions - 1)
					System.out.print(points[minIndex][i]);
				else
					System.out.print(points[minIndex][i] + " ");
			}
			
			System.out.println();
		}
	}
}	
