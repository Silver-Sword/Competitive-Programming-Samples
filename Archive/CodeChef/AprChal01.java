import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class AprChal01 {
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);
		int cases = in.nextInt();
		int[] ray = new int[cases];
		
		for(int c = 0; c < cases; c++)
		{
			ray[c] = in.nextInt();
		}
		
		Arrays.sort(ray);
		int max = ray[cases - 1];
		int second = findSecondMax(max, ray);
		
		System.out.println(second % max);
		
		in.close();
		out.close();
	}
	
	public static int findSecondMax(int max, int[] ray)
	{
		for(int i = ray.length - 2; i >= 0; i--)
			if(ray[i] != max)
				return ray[i];
		return max;
	}
}
