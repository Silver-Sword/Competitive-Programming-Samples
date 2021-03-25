import java.util.Scanner;

public class Expogov2 {
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
			
			
			
		}

	}
	
	public static String ans(int i, int x, int y, String xBin, String yBin, boolean east, boolean north)
	{
		if(i == 0 && xBin.charAt(0) == yBin.charAt(0))
		{
			String attempt1 = ans(1, getSub(x, xBin), y, xBin, yBin, !east, north);
			
			if(!attempt1.isEmpty())
			{
				attempt1 = getXDir(east) + getYDir(north) + attempt1;
				return attempt1;
			}
			
			String attempt2 = ans(1, x)
		}
	}
	
	public static int getSub(int num, String bin)
	{
		String temp = "1" + bin;
		int ans = Integer.valueOf(temp, 2) - num;
		
		return ans - num;
	}
	
	
	public static void print(String yBin, String xBin, int ySign, int xSign, boolean xSub, boolean ySub)
	{
		int len = Math.max(yBin.length(), xBin.length());
		
		for(int x = xBin.length() - len, y = yBin.length() - len, i = 0; i < len; i++, x++, y++)
		{
			if(x < 0 || xBin.charAt(x) != '1')
			{
				if(ySub)
				{
					System.out.print(yDir[(ySign+1) %2]);
					ySub = false;
				}
				else
					System.out.print(yDir[ySign]);
			}
			else
			{
				if(xSub)
				{
					System.out.print(xDir[(xSign+1) % 2]);
					xSub = false;
				}
				else
					System.out.print(xDir[xSign]);
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