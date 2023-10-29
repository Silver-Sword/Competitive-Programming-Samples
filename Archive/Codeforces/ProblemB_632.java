import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ProblemB_632 {
	
	static int n;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			n = scan.nextInt();
			int[] a = new int[n];
			int[] b = new int[n];
			
			for(int i = 0; i < n; i++)
				a[i] = scan.nextInt();
			
			for(int i = 0; i < n; i++)
				b[i] = scan.nextInt();
			
			System.out.println(possible(a, b) ? "YES" : "NO");
		}

	}
	
	public static boolean possible(int[] a, int[] b)
	{
		if(a[0] != b[0])
			return false;
		
		boolean[] check = {false, false, false};
		check[a[0] + 1] = true;
		
		for(int i = 1; i < n; i++)
		{
			if(a[i] == b[i])
			{
				
			}
			
			else if(a[i] > b[i])
			{
				if(!check[0])
					return false;
			}
			
			else 
			{
				if(!check[2])
					return false;
			}
			
			check[a[i] + 1] = true;
		}
		
		return true;
		
	}

}
