import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class a {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		int k = parse(in[1]);
		
		Person[] ray = new Person[n];
		for(int i = 0; i < n; i++)
			ray[i] = new Person(i, scan.readLine().split(" "));
		
		Arrays.sort(ray);
		
		PriorityQueue<Integer> exitTime = new PriorityQueue<Integer>();
		LinkedList<Integer> shutOff = new LinkedList<Integer>();
		
		int score = 0;
		
		for(int i = 0; i < n; i++)
		{
			while(!exitTime.isEmpty() && exitTime.peek() <= ray[i].enter)
			{
				shutOff.add(exitTime.poll() + k);
			}
			
			while(!shutOff.isEmpty() && shutOff.peek() < ray[i].enter)
				shutOff.pop();
			
			if(!shutOff.isEmpty() && shutOff.peek() >= ray[i].enter)
			{
				score++;
				shutOff.pop();
			}
			
			exitTime.add(ray[i].exit);
		}
		
		out.println(score);

		out.flush();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Person implements Comparable<Person>
	{
		int id, enter, exit;
		
		public Person(int i, String[] s)
		{
			id = i;
			enter = parse(s[0]);
			exit = enter + parse(s[1]);
		}

		@Override
		public int compareTo(Person p) 
		{
			if(enter != p.enter) return enter - p.enter;
			
			if(exit != p.exit) return exit - p.exit;
			
			return id - p.id;
				
		}
		
		public String toString()
		{
			return id + ": " + enter + "-" + exit;
		}
	}
}
