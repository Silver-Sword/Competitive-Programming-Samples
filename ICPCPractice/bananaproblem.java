import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.TreeSet;

public class bananaproblem 
{
	static int numLadders, height, numRopes, numBananas;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in = scan.readLine().split(" ");
		numLadders = parse(in[0]);
		height = parse(in[1]);
		numRopes = parse(in[2]);
		numBananas = parse(in[3]);
		
		TreeSet<Rope>[] ropes = new TreeSet[numLadders + 1];
		Arrays.setAll(ropes, x->new TreeSet<Rope>());
		
		for(int r = 0;  r < numRopes; r++)
		{
			Rope rope = new Rope(scan.readLine().split(" "));
			ropes[rope.ladder1].add(rope);
			ropes[rope.ladder2].add(rope);
		}
		
		Monkey[] monkey = new Monkey[numLadders];
		for(int i = 0; i < numLadders; i++)
			monkey[i] = new Monkey(i, scan.readLine().split(" "));
		
		Banana[] banana = new Banana[numBananas];
		for(int i = 0; i < numBananas; i++)
			banana[i] = new Banana(scan.readLine().split(" "));
		
		long ans = simulate(banana, ropes, monkey);
		out.println(ans);
		out.flush();
	}

	private static long simulate(Banana[] banana, TreeSet<Rope>[] ropes, Monkey[] monkey) 
	{
		int ans = 0;
		
		long[] travel;
		int id;
		
		long time;
		
		for(Banana b : banana)
		{
			travel = new long[2];
			id = move(b.ladder, b.step, new Rope(b.ladder, b.step), ropes, travel);
			
			time = travel[0] * monkey[id].climbTime + travel[1] * monkey[id].ropeTime;
			
			if(getsBanana(time, b))
				ans++;
		}
		
		return ans;
	}

	private static boolean getsBanana(long time, Banana banana) 
	{
		long low = 0, high = time;
		long mid;
		
		double start;
		while(low <= high)
		{
			mid = (low + high) / 2;
			start = mid * (banana.xi + banana.yi) + 0.5;
			
			// if within range
			if(start <= time && time <= (start + banana.xi))
			{
				return true;
			}
			
			// else if start is too early
			else if(start < time)
			{
				low = mid + 1;
			}
		
			// else start is too late
			else
			{
				high = mid - 1;
			}
		}
		
		return false;
	}

	private static int move(int ladder, int step, Rope rope, TreeSet<Rope>[] ropes, long[] travel)
	{
		Rope next = ropes[ladder].lower(rope);
		
		// reaches floor
		if(next == null)
		{
			travel[0] += step;
			return ladder;
		}
		
		// reaches next rope
		else
		{
			travel[0] += step - next.step;
			travel[1] += next.length;
			return move(next.other(ladder), next.step, next, ropes, travel);
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Rope implements Comparable<Rope>
	{
		int ladder1, ladder2, step;
		
		long length;
		
		public Rope(String[] in)
		{
			length = parse(in[0]);
			ladder1 = parse(in[1]) - 1;
			ladder2 = parse(in[2]) - 1;
			step = parse(in[3]);
		}
		
		public Rope(int ladder, int step)
		{
			ladder1 = ladder;
			this.step = step;
		}

		@Override
		public int compareTo(Rope r) 
		{
			if(step == r.step)
				return ladder1 - r.ladder1;
			
			return step - r.step;
		}
		
		public int other(int ladder)
		{
			return ladder == ladder1 ? ladder2 : ladder1;
		}
	}
	
	static class Monkey
	{
		int index;
		long climbTime, ropeTime;
		
		public Monkey(int i, String[] in)
		{
			index = i;
			climbTime = parse(in[0]);
			ropeTime = parse(in[1]);
		}
		
		public String toString()
		{
			return Integer.toString(index);
		}
	}
	
	static class Banana
	{
		int ladder, step;
		long xi, yi;
		
		public Banana(String[] in)
		{
			ladder = parse(in[0]) - 1;
			step = parse(in[1]);
			xi = parse(in[2]);
			yi = parse(in[3]);
		}
	}
}
