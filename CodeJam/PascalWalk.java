import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class PascalWalk {
	
	static LinkedList<Point> walk = new LinkedList<Point>();
	static int target;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int tt = scan.nextInt();
		
		ArrayList<Row> rows = new ArrayList<Row>();
		rows.add(new Row());
		
		for(int t = 1; t <= tt; t++)
		{
			target = scan.nextInt();
			
			recur(0, 0, 0, rows);
			System.out.println("Case #" + t + ":");
			
			while(!walk.isEmpty())
			{
				Point p = walk.pop();
				System.out.println(p.y+1 + " " + (p.x + 1));
			}
			
			int size = rows.size();
			for(int i = 0; i < size; i++)
				rows.get(i).used = new boolean[i+1];
		}
	}
	
	public static boolean recur(int i, int j, int sum, ArrayList<Row> rows)
	{
		rows.get(i).used[j] = true;
		if(j < 0 || j >= rows.get(i).length)
		{
			rows.get(i).used[j] = false;
			return false;
		}
		
		int current = sum + rows.get(i).row[j];
		
		if(current == target)
		{
			walk.addFirst(new Point(i, j));
			rows.get(i).used[j] = false;
			return true;
		}
		
		if(current > target)
		{
			rows.get(i).used[j] = false;
			return false;
		}
		
		if(i + 1 >= rows.size())
			rows.add(new Row(rows.get(rows.size() - 1).row));
		
		if(recur(i+1, i%2==0 ? j+1 : j, current, rows))
		{
			walk.addFirst(new Point(i, j));
			rows.get(i).used[j] = false;
			return true;
		}
		
		else if( (!rows.get(i+1).used[j] && recur(i+1, j, current, rows)) || (!rows.get(i).used[j-1] && recur(i, j-1, current, rows)) || (rows.get(i).used[j+1] && recur(i, j+1, current, rows)))
		{
			walk.addFirst(new Point(i, j));
			rows.get(i).used[j] = false;
			return true;
		}
		
		rows.get(i).used[j] = false;
		return false;
	}

}

class Point
{
	int x, y;
	public Point(int i, int j)
	{
		y = i;
		x = j;
	}
}

class Row
{
	int[] row;
	boolean[] used;
	int length;
	
	public Row(int[] prev)
	{
		length = prev.length + 1;
		row = new int[length];
		row[0] = 1;
		
		for(int i = 1; i < length - 1; i++)
		{
			row[i] = prev[i] + prev[i-1];
		}
		
		row[length - 1] = 1;
		
		used = new boolean[length];
	}
	
	public Row()
	{
		row = new int[1];
		row[0] = 1;
		length = 1;
		used = new boolean[1];
		
	}
}
