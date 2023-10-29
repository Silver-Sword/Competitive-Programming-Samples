import java.util.Scanner;

public class Nukes {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int bombard = scan.nextInt();
		int max = scan.nextInt();
		int num = scan.nextInt();
		
		//double temp = (Math.log(bombard) / Math.log(max + 1));
		//if(temp != (int) temp)
		//	temp ++;
		
		//temp = temp < num ? num : temp;
		//temp = num
		
		if(bombard > Math.pow(max + 1, num - 1))
		{
			bombard %= Math.pow(max + 1, num);
		}
		
		System.out.println(digits(bombard, max + 1, num - 1));

		
		System.out.println();
	}
	
	public static String digits(int num, int base, int maxDigit)
	{
		
		if(maxDigit < 1)
			return String.valueOf(num);
		
		return  digits(num % (int)Math.pow(base, maxDigit), base, maxDigit - 1) + " " + String.valueOf(num / (int) Math.pow(base, maxDigit));
	}
}
