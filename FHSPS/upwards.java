import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class upwards {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			char[] ray = scan.readLine().toCharArray();
			char[] ordered = ray.clone();
			Arrays.sort(ordered);
			
			boolean distinct = dist(ordered);
			if(!distinct)
			{
				System.out.println("NO");
				continue;
			}
			
			boolean ans = equals(ray, ordered);
			System.out.println(ans ? "YES" : "NO");
		}
	}
	
	public static boolean dist(char[] ray)
	{
		int n = ray.length;
		
		for(int i = 0; i < n - 1; i++)
			if(ray[i] == ray[i+1])
				return false;
		return true;
	}

	public static boolean equals(char[] ray, char[] ordered) {
		int n = ray.length;
		
		for(int i = 0; i < n; i++)
			if(ray[i] != ordered[i])
				return false;
		return true;
	}
}
