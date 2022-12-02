import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class rearrange {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			in = scan.readLine().split(" ");
			n = parse(in[0]);
			int q = parse(in[1]);
			
			Trie dict = new Trie();
			String temp;
			for(int i = 0; i < n; i++)
			{
				temp = scan.readLine();
				
				dict.addWord(0, temp, temp.length());
			}
			
			out.println("Gift Exchange #" + (t+1) + ":");
			
			int[] placement = new int[26];
			for(int i = 0; i < 26; i++)
				placement[i] = i;
			char order[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			
			for(int query = 0; query < q; query++)
			{
				in = scan.readLine().split(" ");
				
				if(parse(in[0]) == 1)
				{
					char a = in[1].charAt(0);
					char b = in[2].charAt(0);
					
					swap(a, b, placement, order);
				}
				
				else
				{
					out.println(dict.getWord(parse(in[1]) - 1, order));
				}
			}
			
			out.println();
			out.flush();
		}
	}
	
	public static void swap(char a, char b, int[] placement, char[] order) 
	{
		int p1 = placement[a - 'a'];
		int p2 = placement[b - 'a'];
		
		char temp = order[p1];
		order[p1] = order[p2];
		order[p2] = temp;
		
		placement[a - 'a'] = p2;
		placement[b - 'a'] = p1;
	}

	static class Trie
	{
		Trie[] next = new Trie[26];
		int[] count = new int[26];
		boolean flag;
		String result;
		
		public Trie()
		{
			flag = false;
			result = "";
		}
		
		public Trie(int index, String word, int len)
		{
			if(index >= len)
			{
				flag = true;
				result = word;
				return;
			}
			
			flag = false;
			char c = word.charAt(index);
			
			next[c - 'a'] = new Trie(index + 1, word, len);
			count[c - 'a'] ++;
			
		}
		
		public void addWord(int index, String word, int len)
		{
			if(index >= len)
			{
				flag = true;
				result = word;
				return;
			}
			
			char c = word.charAt(index);
			
			if(next[c - 'a'] == null)
				next[c - 'a'] = new Trie(index + 1, word, len);
			else
				next[c - 'a'].addWord(index + 1, word, len);
			
			count[c - 'a'] ++;
		}
		
		public String getWord(int key, char[] order)
		{
			if(key == 0 && flag)
				return result;
			
			if(flag)
				key --;
			
			for(int i = 0; i < 26; i++)
			{
				if(next[order[i] - 'a'] == null) continue;
				
				if(count[order[i] - 'a'] > key)
					return next[order[i] - 'a'].getWord(key, order);
				
				else
					key -= count[order[i] - 'a'];
			}
			
			return null;
		}
	}

	

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
