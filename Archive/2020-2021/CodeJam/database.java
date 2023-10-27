import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class database {
	static int q = 0;
	static int b;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		b = scan.nextInt();
		
		for(int t = 1; t <= tt; t++)
		{
			q = 0;
			
			int[] bits = new int[b];
			boolean[] same = new boolean[b/2 + 1];
			boolean sameFlipped = false, oppFlipped = false;
			
			int i = 0;
			int lowerHalf = (b-1) / 2;
			
			int firstOpp = -1, firstSame = -1;
			
			while(i <= lowerHalf)
			{
				if(q % 10 == 0)
					checkBits(firstSame, firstOpp, bits, same, scan, i-1);
				
				getBits(i, bits, same, scan);
				if(firstOpp == -1 && !same[i])
					firstOpp = i;
				else if(firstSame == -1 && same[i])
					firstSame = i;
				
				
				if(i == lowerHalf)
				{
					String s = "";
					for(int j = 0; j < b; j++)
						s += String.valueOf(bits[j]);
					
					System.out.println(s);
					System.out.flush();
					
					scan.nextLine();
					char c = scan.nextLine().charAt(0);
					if(c == 'N')
						return;
					break;
				}
				
				i++;
			}
		}
	}
	
	//assuming the fluctuation is not going to occur in the middle of the method
	public static void getBits(int i, int[] bits, boolean[] same, Scanner scan)
	{
		int b1 = ask(i, scan);
		
		int b2 = ask(b - i - 1, scan);
		
		bits[i] = b1;
		bits[b - (i+1)] = b2;
		
		same[i] = b1 == b2;
	}
	
	public static void checkBits(int firstSame, int firstOpp, int[] bits, boolean[] same, Scanner scan, int maxBit)
	{
		if(firstSame < 0 && firstOpp < 0)
			return; 
		
		boolean sameFlipped = false, oppFlipped = false;
		if(firstSame >= 0)
		{
			int tempBit = ask(firstSame, scan);
			if(tempBit != bits[firstSame])
				sameFlipped = true;
			
		}
		
		if(firstOpp >= 0)
		{
			int tempBit = ask(firstOpp, scan);
			if(tempBit != bits[firstOpp])
				oppFlipped = true;
		}
		
		if(firstOpp < 0 || firstSame < 0)
		{
			ask(0, scan);
		}
		
		if(!oppFlipped && !sameFlipped)
			return;
		
		for(int i = 0; i <= maxBit; i++)
		{
			if((same[i] && sameFlipped) || (!same[i] && oppFlipped))
			{
				bits[i] = (bits[i] + 1) % 2;
				bits[b-i-1] = (bits[b-i-1] + 1) % 2;
			}
		}
		
		return;
	}
	
	public static int ask(int index, Scanner scan)
	{
		System.out.println(index + 1);
		System.out.flush();
		q++;
		
		return scan.nextInt();
		
		
	}

}
