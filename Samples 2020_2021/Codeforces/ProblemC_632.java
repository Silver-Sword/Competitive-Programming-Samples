import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ProblemC_632 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int n = scan.nextInt();
		
		int[] ray = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			ray[i] = scan.nextInt();
		}
		
		int count = 0;
		
		for(int i = 0; i < n; i++)
		{
			if(ray[i] != 0)
				count++;
			
			long sum = ray[i];
			
			for(int j = i + 1; j < n; j ++)
			{
				if(i == 0 && j == n - 1)
					continue;
				
				sum += ray[j];
				
				if(sum != 0)
					count ++;
			}
		}
		
		System.out.println(count);

	}

}
