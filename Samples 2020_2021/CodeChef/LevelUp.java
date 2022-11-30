import java.util.Scanner;

public class LevelUp {
	
	public static String yes = "Chefirnemo" ;
	public static String no = "Pofik";
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int needKnow = scan.nextInt();
			int needPow = scan.nextInt();
			int gainKnow = scan.nextInt();
			int gainPow = scan.nextInt();
			
			if(needKnow <= 1 || needPow <= 1)
				System.out.println(no);
			
			else if(needKnow == 1 && needPow == 1)
				System.out.println(yes);
			
			else if(needKnow == 1 || needPow == 1)
			{
				if(needKnow == 1 && (needPow - 1) % gainPow == 0)
					System.out.println(yes);
				else if(needPow == 1 && (needKnow - 1) % gainKnow == 0)
					System.out.println(yes);
				else 
					System.out.println(no);
			}
			
			else if(needKnow == 2 || needPow == 2)
			{
				if(needKnow == 2 && needPow == 2)
					System.out.println(yes);
				else if(needKnow == 2 && ((needPow - 1) % gainPow == 0 || (needPow - 1) % gainPow == 1))
						System.out.println(yes);
				else
					System.out.println(no);
			}
			
			else if(gainKnow == 1 || gainPow == 1)
			{
				if(gainKnow == 1 && ((needPow - 1) % gainPow == 1 || (needPow - 1) % gainPow == 0))
					System.out.println(yes);
				else if(gainPow == 1 && ((needKnow - 1) % gainKnow == 1 || (needKnow - 1) % gainKnow == 0))
					System.out.println(yes);
				else
					System.out.println(no);
			}
			
			else if(gainKnow > needKnow || gainPow > needPow)
				System.out.println(no);
			
			else if((needKnow - 1) % gainKnow == 0 && (needPow - 1) % gainPow == 0)
				System.out.println(yes);
			
			else if((needKnow - 1) % gainKnow == 1 && (needPow - 1) % gainPow == 1)
				System.out.println(yes);
			
			else
				System.out.println(no);
		}
	}
}
