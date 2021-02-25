import java.util.Arrays;
import java.util.Scanner;

public class MarchA1 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int numNotes = scan.nextInt();
			int mugged = scan.nextInt();
			
			int[] notes = new int[numNotes];
			for(int i = 0; i < numNotes; i++)
				notes[i] = scan.nextInt();
			
			Arrays.sort(notes);
			int maxIndex = numNotes - 1;
			while(maxIndex >= 0 && notes[maxIndex] > mugged)
				maxIndex --;
			
			if(mugged == 0)
				System.out.println("Yes");
			else if(maxIndex < 0)
				System.out.println("No");
			else
			{
				boolean check = false;
				for(int i = 0; i <= maxIndex; i++)
					if(notes[i] == mugged)
						check = true;
				if(check)
					System.out.println("Yes");
				else
					System.out.println(recur(notes, notes[0], mugged, 0) ? "Yes" : "No");
			}
		}
	}
	
	public static boolean recur(int[] notes, int curTotal, int target, int curIndex)
	{
		if(curTotal == target)
			return true;
		else if(curTotal > target)
			return false;
		
		for(int i = curIndex + 1; i < notes.length; i++)
			if(recur(notes, curTotal + notes[i], target, i))
				return true;
		return false;
	}
}
