import java.util.Scanner;

public class CraneGame {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int stackNum = scan.nextInt();
		int maxHeight = scan.nextInt();
		int[] stacks = new int[stackNum];
		for(int i = 0; i < stacks.length; i++)
			stacks[i] = scan.nextInt();
		
		int currentPos = 0;
		boolean hasBox = false;
		int command = scan.nextInt();
		
		while(command != 0)
		{
			if (command == 1) //move left
			{
				if(currentPos != 0)
					currentPos--;
			}
			
			else if(command == 2) //move right
			{
				if(currentPos != stackNum - 1)
					currentPos++;
			}
			
			else if(command == 3) // pick up box
			{
				if (!hasBox && stacks[currentPos] > 0)
				{
					hasBox = true;
					stacks[currentPos]--;
				}
				
			}
			
			else if(command == 4) //drop box
			{
				if(hasBox && stacks[currentPos] < maxHeight)
				{
					hasBox = false;
					stacks[currentPos]++;
				}
			}
			
			command = scan.nextInt();
		}
		
	
	
	for(int i = 0; i < stackNum; i++)
	{
		if(i ==  stackNum - 1)
		{
			System.out.print(stacks[i]);
		}
		
		else
			System.out.print(stacks[i] + " ");
	}
}
}
