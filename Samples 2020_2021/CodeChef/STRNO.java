import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class STRNO {

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int X = scan.nextInt(), K = scan.nextInt();
			
			if(X <= K)
			{
				System.out.println(0);
				continue;
			}
			
			if(K > 11)
			{
				System.out.println(0);
				continue;
			}
			
			if(K == 1)
			{
				System.out.println(X > 1 ? 1 : 0);
				continue;				
			}
			
			int min = 1;
			for(int i = 2; i <= K; i++)
				min *= K;
			
			min += 2;
			
			if((X % 2 == 0 && X < min) || (X % 2 == 1 && X < min - 1))
			{
				System.out.println(0);
				continue;
			}
			
			if((X%2 == 0 && X == min) || (X % 2 == 1 && X == min - 1))
			{
				System.out.println(1);
				continue;
			}
			
			if(X % (K+1) == 0)
			{
				System.out.println(1);
				continue;
			}
		}

	}

}
