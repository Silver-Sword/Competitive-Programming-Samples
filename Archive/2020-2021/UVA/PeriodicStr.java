import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PeriodicStr {
	public static void main(String[] args) throws IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		
		while(tt-- > 0)
		{
			scan.readLine();
			
			String str = scan.readLine();
			int[] process = new int[str.length()+1];
			
			preProcess(str, process);
			
			//for(int i : process)
				//System.out.print(i + " ");
			//System.out.println();
			
			if(process[str.length()] < ((str.length()+1) / 2))
			{
				
				System.out.println(str.length());
			}
			
			else
			{
				int ans = str.length() - process[str.length()];
				
				if(str.length() % ans != 0)
					System.out.println(str.length());
				else
					System.out.println(ans);
			}
			
			if(tt != 0)
				System.out.println();
		}
	}
	
	public static void preProcess(String s, int[] p)
	{
		p[0] = -1;
		
		int i = 0, j = -1;
		
		int len = s.length();
		
		while(i < len)
		{
			while(j >= 0 && s.charAt(i) != s.charAt(j)) j = p[j];
			
			i++;
			j++;
			
			p[i] = j;
		}
	}
	
}
