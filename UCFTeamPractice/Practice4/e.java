import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class e {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		n = Integer.parseInt(scan.readLine());
		in = scan.readLine().split(" ");
		Room[] students = new Room[n];
		long sum = 0;
		
		for(int i = 0; i < n; i++)
		{
			students[i] = new Room(i+1, parse(in[i]));
			sum += students[i].val;
		}
		
		Arrays.sort(students);
		sum -= students[n - 1].val;
		
		if(sum < students[n-1].val)
			out.println("impossible");
		
		else
		{
			out.print(students[n - 1].id);
			for(int i = n - 2; i >= 0; i--)
				out.print(" " + students[i].id);
			out.println();
		}
		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Room implements Comparable<Room>
	{
		int id, val;
		
		public Room(int i, int v)
		{
			id = i;
			val = v;
		}

		@Override
		public int compareTo(Room r) {
			if(val == r.val)
				return id - r.id;
			
			return val - r.val;
		}
	}
}
