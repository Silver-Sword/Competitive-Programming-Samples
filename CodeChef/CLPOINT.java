import java.util.ArrayList;
import java.util.Scanner;

public class CLPOINT {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int c = 0; c < cases; c++)
		{
			int lines = scan.nextInt();
			int point = scan.nextInt();
			int cost = 0;
			
			int[][] points = new int[lines][point];
			
			for(int y = 0; y < lines; y++)
				for(int x = 0; x < point; x++)
					points[y][x] = scan.nextInt();
			
			int minIndex = 0;
			int minCost = 0;
			
			for(int y = 1; y < lines; y++)
				for(int x = 0; x < point; x++)
					minCost += Math.abs(points[0][x] - points[y][x]);
			
			for(int currentLine = 1; currentLine < point; currentLine++)
			{
				int temp = 0;
				for(int y = 0; y < lines; y++)
				{
					for(int x = 0; x < point; x++)
					{
						temp += Math.abs(points[currentLine][x] - points[y][x]);
					}
				}
				
				if(temp < minCost)
				{
					minCost = temp;
					minIndex = currentLine;
					list = new ArrayList<Integer>();
					list.add(minIndex);
				}
				
				if(temp == minCost)
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
			
			for(int i = 0; i < point; i++)
			{
				if(i == point - 1)
					System.out.print(points[minIndex][i]);
				else
					System.out.print(points[minIndex][i] + " ");
			}
			
			System.out.println();
		}
	}
}	
