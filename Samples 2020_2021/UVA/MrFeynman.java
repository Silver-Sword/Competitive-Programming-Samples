import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class MrFeynman {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		TreeMap<Double, Long> cubes = new TreeMap<Double, Long>();
		
		for(long i = 1; i * i * i <= 1000000; i++)
		{
			cubes.put((double) i*i*i, i);
		}
		
		
		double n = Double.parseDouble(scan.readLine());
		
		while(n != 0)
		{
			long a = cubes.floorEntry(n).getValue();
			
			double ans = (n - a * a * a) / (3.0 * a * a);
			System.out.printf("%.4f\n", ans+a);
			
			n = Double.parseDouble(scan.readLine());
		}
		
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
