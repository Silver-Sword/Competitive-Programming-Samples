import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Queue {

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			int index = -1;
			int[] line = new int[n];
			boolean bad = false;
			
			for(int i = 0; i < n; i++)
			{
				line[i] = scan.nextInt();
				if(!bad && line[i] == 1)
				{
					if(index == -1 )
						index = i;
					else 
					{
						if(i - index < 6)
						{
							bad = true;
						}
						
						index = i;
						
							
					}
				}
				
			}
			
			System.out.println(bad ? "NO" : "YES");
		}

	}

}
