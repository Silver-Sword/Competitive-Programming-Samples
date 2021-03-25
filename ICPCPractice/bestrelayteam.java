import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class bestrelayteam {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		n = Integer.parseInt(scan.readLine());
		Runner[] run = new Runner[n];
		
		for(int i = 0; i < n; i++)
		{
			in = scan.readLine().split(" ");
			run[i] = new Runner(in);
		}
		
		Arrays.sort(run);
		
		double min = Double.MAX_VALUE;
		int[] index = {3, 0, 1, 2};
		
		double sum = run[0].genSpeed + run[1].genSpeed + run[2].genSpeed;
		
		for(int i = 3; i < n; i++)
		{
			if(sum + run[i].firstSpeed < min)
			{
				min = sum + run[i].firstSpeed;
				index[0] = i;
			}
		}
		
		sum += run[3].genSpeed;
		for(int i = 0; i < 3; i++)
		{
			if(sum + run[i].firstSpeed - run[i].genSpeed < min)
			{
				min = sum + run[i].firstSpeed - run[i].genSpeed;
				index[0] = i;
				for(int temp = 1, j = 0; j < 4; j++)
				{
					if(j == i) continue;
					index[temp] = j;
					temp ++;
				}
			}
		}
		
		out.printf("%.3f\n", min);
		for(int i = 0; i < 4; i++)
			out.println(run[index[i]].name);
		out.flush();
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Runner implements Comparable<Runner>
	{
		double firstSpeed, genSpeed;
		String name;
		
		public Runner(String[] runner)
		{
			name = runner[0];
			firstSpeed = Double.parseDouble(runner[1]);
			genSpeed = Double.parseDouble(runner[2]);
		}

		@Override
		public int compareTo(Runner r) 
		{
			if(genSpeed == r.genSpeed) return Double.compare(firstSpeed, r.firstSpeed);
			return Double.compare(genSpeed, r.genSpeed);
		}
		
		public String toString()
		{
			return name;
		}
	}
}
