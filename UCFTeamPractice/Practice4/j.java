import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class j {
	static int n, q, s;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		q = parse(in[1]);
		s = parse(in[2]);
		
		int[] sensor = new int[s];
		in = scan.readLine().split(" ");
		for(int i = 0; i < s; i++)
			sensor[i] = parse(in[i]) - 1;
		
		in = scan.readLine().split(" ");
		long[] queuesize = new long[q];
		for(int i = 0; i < q; i++)
			queuesize[i] = parse(in[i]);
		
		long[][] downlink = new long[n][s+1];
		for(int i = 0; i < n; i++)
		{
			in = scan.readLine().split(" ");
			for(int j = 0; j <= s; j++)
				downlink[i][j] = parse(in[j]);
		}
		
		boolean ans = isPossible(sensor, queuesize, downlink);
		
		System.out.println(ans ? "possible" : "impossible");
	}
	
	public static boolean isPossible(int[] sensor, long[] size, long[][] downlink)
	{
		long sum = 0;
		long down = 0;
		
		
		long[] queue = new long[q];
		
		for(int round = 0; round < n; round++)
		{
			long[] waiting = new long[q];
			//add the new data to the waitlist
			for(int i = 1; i <= s; i++)
			{
				waiting[sensor[i-1]] += downlink[round][i];
				sum += downlink[round][i];
			}
			
			//check if the waitlist is greater than the size
			for(int i = 0; i < q; i++)
				if(waiting[i] > size[i])
					return false;
			
			for(int i = 0; i < q; i++)
			{
				if(waiting[i] + queue[i] > size[i])
				{
					long remove = waiting[i] + queue[i] - size[i];
					down -= remove;
					sum -= remove;
					queue[i] = size[i];
				}
				
				else
					queue[i] += waiting[i];
			}
			
			if(down < 0)
				return false;
			
			down += downlink[round][0];
			
			if(down > sum)
				down = sum;
		}
		
		return down >= sum;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
