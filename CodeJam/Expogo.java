import java.util.Scanner;

public class Expogo {
	static String[] xDir = {"E", "W"};
	static String[] yDir = {"N", "S"};
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 1; t <= tt; t++)
		{
			System.out.print("Case #" + t + ": ");
			int x = scan.nextInt(), y = scan.nextInt();
			int xSign = (x < 0) ? 1 : 0, ySign = (y < 0) ? 1 : 0;
			
			String xBin = Integer.toBinaryString(Math.abs(x));
			int xLen = xBin.length();
			
			String yBin = Integer.toBinaryString(Math.abs(y));
			int yLen = yBin.length(); 
			
			int len = Math.max(xLen, yLen);
			
			if((Math.abs(x) & Math.abs(y)) == 0)
			{
				print(yBin, xBin, ySign, xSign, false, false);
				continue;
			}
			
			int xOpp = opp(xBin, Math.abs(x));
			if((Math.abs(y) & xOpp) == 0)
			{
				print(yBin, Integer.toBinaryString(xOpp), ySign, (xSign+1) % 2, true, false);
				continue;
			}
			
			int yOpp = opp(yBin, Math.abs(y));
			String yOppBin = Integer.toBinaryString(yOpp);
			if((yOpp & xOpp) == 0)
			{
				print(yOppBin, Integer.toBinaryString(xOpp), (ySign+1) %2, (xSign+1) % 2, true, true);
				continue;
			}
			
			if((Math.abs(x) & yOpp) == 0)
			{
				print(yOppBin, xBin, (ySign+1) %2, xSign, false, true);
				continue;
			}
			
			System.out.println("IMPOSSIBLE");
			
		}

	}
	
	public static void print(String yBin, String xBin, int ySign, int xSign, boolean xSub, boolean ySub)
	{
		int len = Math.max(yBin.length(), xBin.length());
		
		int yTemp = 0, xTemp = 0;
		
		for(int x = xBin.length() - len, y = yBin.length() - len, i = 0; i < len; i++, x++, y++)
		{
			if(x < 0 || xBin.charAt(x) != '1')
			{
				if(ySub && yTemp != 0)
				{
					System.out.print(yDir[(ySign+1) %2]);
				}
				else
				{
					System.out.print(yDir[ySign]);
					yTemp ++;
				}
			}
			else
			{
				if(xSub && xTemp != 0)
				{
					System.out.print(xDir[(xSign+1) % 2]);
				}
				else
				{
					System.out.print(xDir[xSign]);
					xTemp++;
				}
			}
		}
		
		System.out.println();
	}
	
	public static int opp(String bin, int original)
	{
		String largeBin = "1" + bin;
		int tempLarge = Integer.valueOf(largeBin, 2);
		
		int a = tempLarge - original;
		
		return (a | (a - original));
	}

}
