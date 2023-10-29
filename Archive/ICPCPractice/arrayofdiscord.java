import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class arrayofdiscord 
{
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		n = Integer.parseInt(scan.readLine());
		String[] in = scan.readLine().split(" ");
		long[] ray = new long[n];
		int[] len = new int[n];
		char[][] str = new char[n][];
		
		for(int i = 0; i < n; i++)
		{
			ray[i] = Long.parseLong(in[i]);
			len[i] = in[i].length();
			str[i] = in[i].toCharArray();
		}
		
		boolean ans = update(str, ray, len);
		if(ans)
		{
			printArray(str, out);
		}
		
		else
		{
			out.println("impossible");
		}

		out.flush();
	}

	private static boolean update(char[][] str, long[] ray, int[] len) 
	{
		for(int i = 0; i < n -1; i++)
		{
			if(len[i] == len[i+1])
			{
				if(len[i+1] == 1 && ray[i] != 0)
				{
					str[i+1][0] = '0';
					ray[i+1] = 0;
					return true;
				}
				
				if(bigger(i, i+1, ray, str, len))
					return true;
				else if(smaller(i+1, i, ray, str, len))
					return true;
			}
		}
		
		return false;
	}

	// assumes first index cannot be 0
	private static boolean smaller(int changeIndex, int compIndex, long[] ray, char[][] str, int[] len) 
	{
		char min = len[changeIndex] == 1 ? '0' : '1';
		
		// to make the ith bigger, go until the first nonequal 
		for(int i = 0; i < len[changeIndex]; i++)
		{
			// it is already max value, make sure the remainder of the number is greater
			if(str[compIndex][i] == min)
			{
				// the first string is less after this index
				if(compare(i+1, changeIndex, compIndex, str) < 0)
				{
					str[changeIndex][i] = min;
					return true;
				}
							
				// otherwise, do nothing, not valid
			}
						
			else
			{
				str[changeIndex][i] = min;
				return true;
			}
				
			if(str[changeIndex][i] != str[compIndex][i])
			{
				// nothing has been valid, and this cannot be updated
				break;
			}
					
			if(i == 0)
				min = '0';
		}
				
				// not found
				return false;
	}

	private static boolean bigger(int changeIndex, int compIndex, long[] ray, char[][] str, int[] len) 
	{
		// to make the ith bigger, go until the first nonequal 
		for(int i = 0; i < len[changeIndex]; i++)
		{
			// it is already max value, make sure the remainder of the number is greater
			if(str[compIndex][i] == '9')
			{
				// the first string is greater after this index
				if(compare(i+1, changeIndex, compIndex, str) > 0)
				{
					str[changeIndex][i] = '9';
					return true;
				}
					
				// otherwise, do nothing, not valid
			}
				
			else
			{
				str[changeIndex][i] = '9';
				return true;
			}
			
			if(str[changeIndex][i] != str[compIndex][i])
			{
				// nothing has been valid, and this cannot be updated
				break;
			}
		}
		
		// not found
		return false;
	}

	private static int compare(int start, int changeIndex, int compIndex, char[][] str) 
	{
		for(int i = start; i < str[changeIndex].length; i++)
		{
			if(str[changeIndex][i] != str[compIndex][i])
				return str[changeIndex][i] - str[compIndex][i];
		}
		
		return 0;
	}

	private static void printArray(char[][] ray, PrintWriter out) 
	{
		printNum(ray[0], out);
		for(int i = 1; i < n; i++)
		{
			out.print(" ");
			printNum(ray[i], out);
		}
		out.println();
	}

	private static void printNum(char[] str, PrintWriter out) 
	{
		for(int i = 0; i < str.length; i++)
			out.print(str[i]);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
