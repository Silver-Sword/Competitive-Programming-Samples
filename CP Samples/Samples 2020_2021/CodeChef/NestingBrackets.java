import java.util.ArrayList;
import java.util.Scanner;

public class NestingBrackets{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		
		ArrayList<Integer> nest = new ArrayList<Integer>();
		ArrayList<Integer> nestIndex = new ArrayList<Integer>();
		ArrayList<Integer> length = new ArrayList<Integer>();
		ArrayList<Integer> lengthIndex = new ArrayList<Integer>();
		int maxNest = 0;
		int maxNestIndex = -1;
		int maxLength = 0;
		int maxLengthIndex = -1;
		
		int current;
		for(int index = 0; index < N; index++)
		{
			current = scan.nextInt();
			if(current == 1)
			{
				if(nest.isEmpty())
					nest.add(1);
				else
				{
					nest.add(nest.get(nest.size() - 1) + 1);
					int checkNest = 1;
					int tempIndex = nest.size() - 1;
					while(tempIndex >= 0 && nest.get(tempIndex) == checkNest)
					{
						nest.add(nest.get(nest.size() - 1) + 1);
						tempIndex --;
						checkNest++;
					}
				}
				nestIndex.add(index + 1);
				length.add(2);
				lengthIndex.add(index + 1);
				
				
				for(int i = 0; i < length.size() - 1; i++)
					length.set(i, length.get(i) + 2);
				
			}
			else if(current == 2)
			{
				int tempLast = length.size() - 1;
				if(maxNest < nest.get(tempLast))
				{
					maxNest = nest.get(tempLast);
					maxNestIndex = nestIndex.get(tempLast);
				}
				if(maxLength < length.get(tempLast))
				{
					maxLength = length.get(tempLast);
					maxLengthIndex = lengthIndex.get(tempLast);
				}
				
				
				nest.remove(tempLast);
				nestIndex.remove(tempLast);
				length.remove(tempLast);
				lengthIndex.remove(tempLast);
				
			}
		}
		System.out.print(maxNest + " " + maxNestIndex + " " + maxLength + " " + maxLengthIndex);
	}
}