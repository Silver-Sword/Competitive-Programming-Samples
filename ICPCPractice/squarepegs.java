import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class squarepegs {
	static int n;
	static double EPS = 1e-7;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		int m = parse(in[1]);
		int k = parse(in[2]);
		
		PriorityQueue<Double> plots = new PriorityQueue<Double>(Collections.reverseOrder());
		in = scan.readLine().split(" ");
		for(int i = 0; i < n; i++)
			plots.add(Double.parseDouble(in[i]));
		
		
		PriorityQueue<Double> house = new PriorityQueue<Double>(Collections.reverseOrder());
		
		in = scan.readLine().split(" ");
		for(int i = 0; i < m; i++)
			house.add(Double.parseDouble(in[i]));
		
		in = scan.readLine().split(" ");
		double rad;
		double constant = Math.sqrt(2);
		for(int i = 0; i < k; i++)
		{
			rad = Double.parseDouble(in[i]) / constant;
			house.add(rad);
		}
		
		
		int count = 0;
		double currentPlot = plots.poll();
		double currentHouse;
		while(!house.isEmpty())
		{
			currentHouse = house.poll();
			
			if(compare(currentHouse, currentPlot) < 0)
			{
				count++;
				
				if(plots.isEmpty()) break;
				
				currentPlot = plots.poll();
			}
		}
		
		System.out.println(count);
	}

	private static int compare(double house, double plot) 
	{
		if(Math.abs(house - plot) <= EPS)
			return 0;
		
		return Double.compare(house, plot);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
