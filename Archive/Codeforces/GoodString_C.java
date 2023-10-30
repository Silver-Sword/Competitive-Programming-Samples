import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class GoodString_C {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			char[] str = scan.readLine().toCharArray();
			n = str.length;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			
			StringBuilder s1 = new StringBuilder();
			StringBuilder s2 = new StringBuilder();
			
			s1.append(str);
			s2.append(str);
			
			s1.deleteCharAt(0);
			s1.append(str[0]);
			
			s2.deleteCharAt(n-1);
			s2.insert(0, str[n-1]);
			
			long min = recur(0, map, s1, s2, n);
			System.out.println(min);
		}
	}

	public static int recur(int index, HashMap<String, Integer> map, StringBuilder s1, StringBuilder s2, int len) 
	{
		if(s1.toString().equals(s2.toString()))
			return 0;
		
		String key = s1.toString();
		if(map.containsKey(key))
			return map.get(key);
		
		int a = 0, b = 0;
		for(int i = index; i < len; i++)
		{
			if(s1.charAt(i) != s2.charAt(i))
			{
				//delete from s1 (remove index+2 from s2)
				char temp = s1.charAt(i);
				removeChar(i, s1, s2, len);
				a = recur(index, map, s1, s2, len-1) + 1;
				addChar(i, temp, s1, s2, len);
				
		
				//delete from s2 (remove index-2 from s1)
				if(len == 2)
				{
					temp = s1.charAt((i-1+len)%len);
					removeChar((i-1+len)%len, s1, s2, len);
					a = recur(Math.max(i-1, 0) % len, map, s1, s2, len-1) + 1;
					addChar((i-1+len)%len, temp, s1, s2, len);
				}
				
				else
				{
					temp = s1.charAt((i-2+len)%len);
					removeChar((i-2+len)%len, s1, s2, len);
					a = recur(Math.max(i-2, 0) % len, map, s1, s2, len-1) + 1;
					addChar((i-2+len)%len, temp, s1, s2, len);
				}
				
				break;
			}
		}
		
		map.put(key, Math.min(a, b));
		return map.get(key);
	}
	
	public static void removeChar(int index1, StringBuilder s1, StringBuilder s2, int len)
	{
		if(len == 2)
		{
			s1.deleteCharAt(index1);
			s2.deleteCharAt((index1+1) % 2);
			return;
		}
		s1.deleteCharAt(index1);
		s2.deleteCharAt((index1+2)%len); 
	}
	
	public static void addChar(int index1, char c, StringBuilder s1, StringBuilder s2, int len)
	{
		if(len == 2)
		{
			s1.insert(index1, c);
			s2.insert((index1+1) % 2, c);
			return;
		}
		
		s1.insert(index1, c);
		s2.insert((index1+2)%len, c); 
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
