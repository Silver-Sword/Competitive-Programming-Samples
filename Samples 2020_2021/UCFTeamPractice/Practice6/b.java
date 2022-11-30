import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class b {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter (System.out);
		String[] in = scan.readLine().split(" ");
		int b = parse(in[0]);
		int k = parse(in[1]);
		int d = parse(in[2]);
		int p = parse(in[3]);
	
		ArrayList<Buff> direct = new ArrayList<Buff>();
		ArrayList<Buff> percent = new ArrayList<Buff>();
		in = scan.readLine().split(" ");
		for(int i = 0; i < d; i++)
			direct.add( new Buff(i + 1, parse(in[i])) );
		
		in = scan.readLine().split(" ");
		for(int i = 0; i < p; i++)
			percent.add ( new Buff(i + 1, parse(in[i])) ); 
		
		if(k == 0)
		{
			out.println("0 0");
			out.println();
			out.println();
		}
		
		else if(k >= d + p)
		{
			out.println(d + " " + p);
			
			if(d >= 1)
				out.print(1);
			for(int i = 2; i <= d; i++)
				out.print(" " + i);
			
			out.println();
			
			if(p >= 1)
				out.print(1);
			for(int i = 2; i <= p; i ++)
				out.print(" " + i);
			out.println();
		}
		
		else
		{
			getAns(b, k, d, p, direct, percent, out);
		}
		
		out.flush();
		
	}

	private static void getAns(int b, int k, int d, int p, ArrayList<Buff> direct, ArrayList<Buff> percent, PrintWriter out) 
	{
		Collections.sort(direct);
		Collections.sort(percent);
		
		double max = 0;
		int maxD = 0;
		int maxP = 0;
		
		int countD = 0;
		int countP = 0;
		long directSum = 0;
		long percentSum = 100; 
		
		double temp;
		
		while(countD < direct.size() && countD < k)
		{
			directSum += direct.get(countD).value;
			countD ++;
		}
		
		while(countD + countP < k && countP < percent.size() )
		{
			percentSum += percent.get(countP).value;
			countP ++;
		}
		
		maxD = countD;
		maxP = countP;
		max = ((b + directSum) * (percentSum)) / 100.0;
		
		while(countD > 0 && countP < percent.size())
		{
			countD --;
			directSum -= direct.get(countD).value;
			percentSum += percent.get(countP).value;
			countP ++;
			
			temp = ((b + directSum) * percentSum) / 100.0;
			
			if(temp > max)
			{
				max = temp;
				maxD = countD;
				maxP = countP;
			}
		}
		
		out.println(maxD + " " + maxP);
		
		if(maxD > 0)
			out.print(direct.get(0).index);
		for(int i = 1; i < maxD; i++)
			out.print(" " + direct.get(i).index);
		out.println();
		
		if(maxP > 0)
			out.print(percent.get(0).index);
		for(int i = 1; i < maxP; i++)
			out.print(" " + percent.get(i).index);
		out.println();
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Buff implements Comparable<Buff>
	{
		int index;
		long value;
		
		public Buff(int i, long v)
		{
			index = i;
			value = v;
		}

		@Override
		public int compareTo(Buff b) {
			if(b.value == value)
				return index - b.index;
			return Long.compare(b.value, value);
		}
		
		
	}
}
