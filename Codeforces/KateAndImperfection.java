import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class KateAndImperfection {

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int n = scan.nextInt();
		int[] temp = new int[n + 1];
		
		int max = 1;
		
		for(int i = 1; i < n; i++)
		{
			if(i * 2 <= n)
			{
				System.out.print(i + " ");
				max = i;
			}
			else
			{
				System.out.print(max  + " ");
			}
		}
	}

}
