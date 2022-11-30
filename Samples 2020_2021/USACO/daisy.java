import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class daisy {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		n = parse(scan.readLine());
		String[] in = scan.readLine().split(" ");

		int[] ray = new int[n];
		for(int i = 0; i < n; i++)
			ray[i] = parse(in[i]);
		
		int count = 0;
		for(int i = 0; i < n; i++)
		{
			boolean[] seen = new boolean[1001];
			int sum = 0;
			for(int j = i; j < n; j++)
			{
				sum += ray[j];
				seen[ray[j]] = true;
				
				int avg = sum / (j - i + 1);
				if(avg * (j - i + 1) != sum) continue;
				
				if(seen[avg])
					count++;
			}
		}
		
		System.out.println(count);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
