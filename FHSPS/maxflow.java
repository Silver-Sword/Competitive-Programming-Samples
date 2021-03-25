import java.util.ArrayList;
import java.util.Scanner;

public class maxflow {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			Stream[] streams = new Stream[n];
			
			for(int i = 0 ;i < n; i++)
			{
				streams[i] = new Stream(scan.nextInt());
				
				if(i == 0)
				{
					scan.nextInt();
					continue;
				}
				streams[scan.nextInt() - 1].feeders.add(streams[i]);
			}
			
			System.out.println(streams[0].maxFlow());
		}

	}
}
	
	class Stream
	{
		int max;
		ArrayList<Stream> feeders;
		
		public Stream(int m)
		{
			max = m;
			feeders = new ArrayList<Stream>();
		}
		
		public int maxFlow()
		{
			if(feeders.isEmpty())
				return max;
			
			int total = 0;
			
			for(int i = 0; i < feeders.size(); i++)
				total += feeders.get(i).maxFlow();
			
			return Math.min(max, total);
		}
	}


