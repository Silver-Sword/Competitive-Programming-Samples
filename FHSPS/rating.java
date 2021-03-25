import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;

public class rating {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		String[] in;
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			String county = in[0];
			n = parse(in[1]);
			
			TreeSet<Teacher>[] sets = new TreeSet[3];
			Arrays.setAll(sets, x->new TreeSet<Teacher>());
			
			
			for(int i = 0; i < n; i++)
			{
				Teacher temp = new Teacher(scan.readLine().split(" "));
				
				if(temp.numStudents <= 10)
					sets[0].add(temp);
				else if(temp.numStudents <= 30)
					sets[1].add(temp);
				else
					sets[2].add(temp);
			}
			
			System.out.println(county + " COUNTY");
			
			String[] output = {"SMALL ", "MEDIUM ", "LARGE "};
			
			for(int i = 0; i < 3; i++)
			{
				System.out.println(output[i] + "CLASS RANKING");
				
				for(Teacher temp : sets[i])
					System.out.println(temp.name);
				
				System.out.println();
			}
			
			//System.out.println();
			
		}
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}

class Teacher implements Comparable<Teacher>
{
	int numStudents;
	double passRate;
	double avgExamScore;
	
	String name;
	
	int[] scores;
	
	double epsilon = 1e-8;
	
	public Teacher(String[] s)
	{
		scores = new int[10];
		name = s[0];
		
		
		
		for(int i = 1; i < 11; i++)
		{
			scores[i-1] = Integer.parseInt(s[i]);
			numStudents += scores[i-1];
		}
		
		double passed = 0;
		for(int i = 5; i < 10; i++)
			passed += scores[i];
		
		passRate = passed / numStudents;
		
		
		double total = 0;
		for(int i = 0; i < 10; i++)
			total += (i+1) * scores[i];
		
		avgExamScore = total / numStudents;
		
	}

	@Override
	public int compareTo(Teacher t) {
		if(Math.abs(passRate - t.passRate) <= epsilon)
			return Double.compare( t.avgExamScore, avgExamScore);
		
		return Double.compare( t.passRate, passRate);
	}
}
