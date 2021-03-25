import java.util.Scanner;
import java.util.TreeSet;
//unfinished

public class primedigital {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt= scan.nextInt();
		
		
		for(int t = 1; t <= tt; t++)
		{
			long l = scan.nextLong();
			long r = scan.nextLong();
			
			long first = first(l);
			
			long count = count(first, r-1);
			
			System.out.println("Range #" + t + ": " + count);
		}
	}
	
	public static long count(long start, long end)
	{
		char[] first = Long.toString(start).toCharArray();
	
		char[] last = Long.toString(end).toCharArray();
		
		if(first.length < last.length)
		{
			char[] temp = new char[last.length];
			for(int i = last.length - first.length, j = 0; i < last.length; i++, j++)
			{
				temp[i] = first[j];
			}
			for(int j = 0; j < last.length - first.length; j++)
				temp[j] = '0';
			
			first = temp;
		}
		
		//find high
		int high = -1;
		for(int i = last.length - 1; i >= 0; i--)
		{
			if(last[i] > first[i])
				high = i;
		}
		if(high == -1)
			high = last.length;
		
		int index = last.length - 1;
		long count = 0;
		while(increase(first, last, index, high))
			count++;
		
		return count+1;
		
	}
	
	public static boolean increase(char[] num, char[] last, int index, int highestIndex)
	{
		if(index < 0)
			return false;
		if(num[index] == '7')
		{
			num[index] = '2';
			return increase(num, last, --index, highestIndex);
		}

		String replace = "02357";
		num[index] = replace.charAt(replace.indexOf(num[index])+1);
		
		if(index <= highestIndex)
		{
			if(index < highestIndex)
				return false;
			
			if(last[index] < num[index])
				return false;
			
			if(last[index] == num[index])
				if(check(num, last))
					return false;
		}
		
		return true;
	}
	
	public static boolean check(char[] num, char[] last)
	{
		for(int i = 0; i < num.length; i++)
		{
			if(last[i] < num[i])
				return true;
		}
		
		return false;
	}
	
	public static long first(long n)
	{
		long digits = (long) Math.pow(10, Long.toString(n).length() - 1);
		long ans = 0;
		int i = 0;
		while(n > 0)
		{
			long store = n / digits;
			if((store == 2 || store == 3 || store == 5 || store == 7))
			{
				ans += digits * store;
			}
			else
			{
				//if(i == 0)
					//return first((long) (2 * Math.pow(10, Long.toString(n).length())));
				while(!(store == 2 || store == 3 || store == 5 || store == 7))
				{
					store++;
					if(store > 9)
					{
						ans++;
						return first(Long.valueOf(String.valueOf(ans) + String.valueOf(n % digits)));
					}
					
				}
				
				ans += digits * store;
				digits /= 10;
				while(digits > 0)
				{
					ans += digits * 2;
					digits /= 10;
				}
				
				return ans;
			}
			i++;
			
			digits /=10;
			n %= digits;
		}
		
		return digits;
	}


}
