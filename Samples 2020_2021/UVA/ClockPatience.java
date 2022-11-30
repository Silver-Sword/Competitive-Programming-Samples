import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class ClockPatience {
	static int n;
	static String lastCard = "";
	static int count = 0;
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String temp = scan.readLine();
		
		while(!temp.trim().equals("#"))
		{
			String[] cards = temp.split(" ");
			
			LinkedList<String>[] pile = new LinkedList[13];
			Arrays.setAll(pile, x->new LinkedList<String>());
			
			//place cards in list
			for(int i = 0; i < 4; i++)
			{
				if(i != 0) cards = scan.readLine().split(" ");
				
				for(int j = 0; j < 13; j++)
					pile[(12-j)].addLast(cards[j]);
			}
			
			recur(12, pile, "", 0);
			String ans = count + "," + lastCard;
			if(count < 10)
				ans = "0" + ans;
			System.out.println(ans);
			
			temp = scan.readLine();
		}
	}
	
	public static void recur(int index, LinkedList<String>[] pile, String last, int tracker)
	{
		if(pile[index].isEmpty())
		{
			lastCard = last;
			count = tracker;
			return;
		}
		
		//get the next card
		String next = pile[index].remove();
		
		recur(getVal(next), pile, next, tracker+1);
	}

	public static int getVal(String next) 
	{
		char val = next.charAt(0);
		
		if(val >= '2' && val <= '9')
			return (int) (val - '1');
		
		char[] rank = {'A', 'T', 'J', 'Q', 'K'};
		int[] num = {0, 9, 10, 11, 12};
		
		for(int i = 0; i < 5; i++)
			if(val == rank[i])
				return num[i];
		
		return -1;
		
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
}
