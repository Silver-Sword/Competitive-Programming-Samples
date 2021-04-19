import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class lockers {
	static int n, k;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		n = parse(in[0]);
		k = parse(in[1]);
		
		char[] str = scan.readLine().toCharArray();
		
		int start = getFirstIndex(str);
		
		char[] ray = orderStr(str, start);
		Comp comp = new Comp(ray);
		TreeSet<Integer> sub = new TreeSet<Integer>(comp);
		ArrayDeque<Integer> index = new ArrayDeque<Integer>();
		ArrayDeque<Character> ch = new ArrayDeque<Character>();
		
		int next = k;
		int i = 1;
		
		while(i < n)
		{
			while(!ch.isEmpty() && ch.peekLast() >= ray[i])
			{
				index.removeLast();
				ch.removeLast();
			}
			
			ch.add(ray[i]);
			index.add(i);
			
			if(i >= next)
			{
				next = index.pop() + k;
				sub.add(next - k);
				ch.pop();
			}
			
			i++;
		}
		
		int last = sub.last();
		for(i = 0; i < k; i++)
		{
			out.print(ray[(i + last) % n]);
		}
		
		out.println();
		out.flush();
	}

	private static char[] orderStr(char[] str, int start) 
	{
		char[] result = new char[str.length];
		
		for(int i = 0; i < n; i++)
		{
			result[i] = str[ (i + start) % n ];
		}
		
		return result;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Comp implements Comparator<Integer>
	{
		char[] str;
		
		public Comp(char[] s)
		{
			str = s;
		}
		
		@Override
		public int compare(Integer a, Integer b) 
		{
			for(int i = 0; i < k; i++)
			{
				if(str[a] < str[b])
					return -1;
				
				else if(str[b] > str[a])
					return 1;
			}
			
			return 0;
		}
		
	}
	
	public static int getFirstIndex(char[] str)
	{
		char[] word = new char[str.length * 2];
		for(int i = 0; i < n; i ++)
		{
			word[i] = str[i];
			word[i+n] = str[i];
		}
		
		int[] sa = suffixArray(word);
		
		for(int i=0; i<sa.length; i++) {
		      if(sa[i] < n)
		    	  return sa[i];
		    }
		
		return 0;
	}
	
	
	// sort suffixes of S in O(n*log(n))
	static int[] suffixArray(char[] S) {
		    int n = S.length;
		    Integer[] order = new Integer[n];
		    for (int i = 0; i < n; i++)
		      order[i] = n - 1 - i;

		    // stable sort of characters //%
		    Arrays.sort(order, (a, b) -> Character.compare(S[a], S[b]));

		    int[] sa = new int[n];
		    int[] classes = new int[n];
		    for (int i = 0; i < n; i++) {
		      sa[i] = order[i];
		      classes[i] = S[i];
		    }
		    // sa[i] - suffix on i'th position after sorting by first len characters //%
		    // classes[i] - equivalence class of the i'th suffix after sorting by first len characters //%

		    for (int len = 1; len < n; len *= 2) {
		      int[] c = classes.clone();
		      for (int i = 0; i < n; i++) {
		        // condition sa[i - 1] + len < n simulates 0-symbol at the end of the string //%
		        // a separate class is created for each suffix followed by simulated 0-symbol //%
		        classes[sa[i]] = i > 0 && c[sa[i - 1]] == c[sa[i]] && sa[i - 1] + len < n && c[sa[i - 1] + len / 2] == c[sa[i] + len / 2] ? classes[sa[i - 1]] : i;
		      }
		      // Suffixes are already sorted by first len characters //%
		      // Now sort suffixes by first len * 2 characters //%
		      int[] cnt = new int[n];
		      for (int i = 0; i < n; i++)
		        cnt[i] = i;
		      int[] s = sa.clone();
		      for (int i = 0; i < n; i++) {
		        // s[i] - order of suffixes sorted by first len characters //%
		        // (s[i] - len) - order of suffixes sorted only by second len characters //%
		        int s1 = s[i] - len;
		        // sort only suffixes of length > len, others are already sorted //%
		        if (s1 >= 0)
		          sa[cnt[classes[s1]]++] = s1;
		      }
		    }
		    return sa;
		  }

}
