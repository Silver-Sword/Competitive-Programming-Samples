import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class autocorrect {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in = scan.readLine().split(" ");
		
		n = parse(in[0]);
		int m = parse(in[1]);
		Trie tree = new Trie();
			
		String temp;
		for(int i = 0; i < n; i++)
		{
			temp = scan.readLine();
			tree.addWord(0, temp, temp.length(), i);
		}
			
		for(int i = 0; i < m; i++)
		{
			temp = scan.readLine();
			out.println(tree.query(0, temp, -1));
		}
			
		out.flush();
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Trie
	{
		Trie[] next = new Trie[26];
		boolean flag;
		int priority, flagID, tabID;
		boolean root;
		
		public Trie()
		{
			flag = false;
			priority = -1;
			tabID = -1;
			root = true;
		}
		
		public Trie(int index, String word, int len, int id)
		{
			root = false;
			
			if(index >= len)
			{
				flag = true;
				flagID = id;
				tabID = id;
				return;
			}
			
			flag = false;
			priority = word.charAt(index) - 'a';
			tabID = id;
			next[priority] = new Trie(index + 1, word, len, id);
		}
		
		public void addWord(int index, String word, int len, int id)
		{
			if(index >= len)
			{
				flag = true;
				flagID = id;
				tabID = id;
				return;
			}
				
			if(next[word.charAt(index) - 'a'] == null)
			{
				next[word.charAt(index) - 'a'] = new Trie(index + 1, word, len, id);
			}
			
			else
			{
				next[word.charAt(index) - 'a'].addWord(index + 1, word, len, id);
			}
		}
		
		public long query(int index, String word, int lastPrior)
		{
			if(index >= word.length())
				return 0;
			
			long min = Long.MAX_VALUE;
			if(lastPrior != tabID)
			{
				min = queryID(index, word, tabID) + 1;
			}
			
			if(next[word.charAt(index) - 'a'] != null)
				min = Math.min(min, next[word.charAt(index) - 'a'].query(index + 1, word, tabID) + 1);
			
			min = Math.min(min, word.length() - index);
			return min;
		}

		private long queryID(int index, String word, int target) 
		{
			if(flag && flagID == target )
				return query(index, word, target);
			
			if(index >= word.length())
			{
				return getLengthID(target);
			}
			
			if(word.charAt(index) - 'a' == priority)
				return next[priority].queryID(index + 1, word, target);
			
			else
				return getLengthID(target) + query(index, word, tabID);
		}
		
		public long getLengthID(int target)
		{
			
			if(flag && flagID == target)
				return 0;
			
			return 1 + next[priority].getLengthID(target);
		}
		
	}
	
	/*static class Trie
	{
		Trie[] next = new Trie[26];
		int branches;
		boolean flag;
		int priority;
		int flagID, priorityID;
		boolean root;
		
		public Trie()
		{
			flag = false;
			branches = 0;
			priority = -1;
			priorityID = -1;
			root = true;
		}
		
		public Trie(int index, String word, int len, int id)
		{
			root = false;
			
			if(index >= len)
			{
				flag = true;
				flagID = id;
				branches = 0;
				priorityID = -1;
				return;
			}
			
			branches = 1;
			flag = false;
			priority = word.charAt(index) - 'a';
			priorityID = id;
			next[priority] = new Trie(index + 1, word, len, id);
		}
		
		public void addWord(int index, String word, int len, int id)
		{
			if(index >= len)
			{
				flag = true;
				flagID = id;
				return;
			}
			
			if(priorityID == -1 && !root)
				priorityID = id;
			
			if(next[word.charAt(index) - 'a'] == null)
			{
				branches ++;
				next[word.charAt(index) - 'a'] = new Trie(index + 1, word, len, id);
			}
			
			else
			{
				next[word.charAt(index) - 'a'].addWord(index + 1, word, len, id);
			}
		}
		
		public long query(int index, String word, int lastPrior)
		{
			if(index >= word.length())
				return 0;
			
			long min = Long.MAX_VALUE;
			if(lastPrior != priorityID)
			{
				min = queryID(index, word, priorityID) + 1;
			}
			
			if(next[word.charAt(index) - 'a'] != null)
				min = Math.min(min, next[word.charAt(index) - 'a'].query(index + 1, word, priorityID) + 1);
			
			min = Math.min(min, word.length() - index);
			return min;
		}

		private long queryID(int index, String word, int target) 
		{
			if(flag && flagID == target )
				return query(index, word, target);
			
			if(index >= word.length())
			{
				return getLengthID(target);
			}
			
			if(word.charAt(index) - 'a' == priority)
				return next[priority].queryID(index + 1, word, target);
			
			else
				return getLengthID(target) + query(index, word, priorityID);
		}
		
		public long getLengthID(int target)
		{
			System.out.println("Searching Priority: " + target);
			if(flag && flagID == target)
				return 0;
			
			return 1 + next[priority].getLengthID(target);
		}
		
		
	}*/
}
