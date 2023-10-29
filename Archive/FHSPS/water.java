import java.util.LinkedList;
import java.util.Scanner;

public class water {
	static int row, col;
	static int spring;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			col = scan.nextInt();
			row = scan.nextInt();
			scan.nextLine();
			
			char[][] ray = new char[row][col];
			for(int i = 0; i < row; i++)
				ray[i] = scan.nextLine().toCharArray();
			
			LinkedList<Point> p = new LinkedList<Point>();
			for(int i = 0; i < row; i++)
				for(int j = 0; j < col; j++)
					if(ray[i][j] == 's')
						p.add(new Point(i, j));
			
			spring = 0;
			fill(ray, p);
			
			for(int i = 0; i < row; i++)
			{
				for(int j = 0; j < col; j++)
				{
					if(ray[i][j] == 'w')
					{
						spring++;
						p.add(new Point(i, j));
						fill(ray, p);
					}
				}
			}
			
			System.out.println(spring);
		}
	}
	
	public static void fill(char[][] ray, LinkedList<Point> p)
	{
		while(!p.isEmpty())
		{
			Point temp = p.pop();
			if(ray[temp.y][temp.x] == 's')
			{
				spring ++;
				ray[temp.y][temp.x] = 'f';
			}
			
			int[] dy = {0, 1, 0, -1};
			int[] dx = {1, 0, -1, 0};
			
			for(int d = 0; d < 4; d++)
			{
				if(check(temp.y + dy[d], temp.x + dx[d]) && ray[temp.y + dy[d]][temp.x + dx[d]] == 'w')
				{
					ray[temp.y + dy[d]][temp.x + dx[d]] = 'f';
					p.add(new Point(temp.y + dy[d], temp.x + dx[d]));
				}
			}
		}
	}
	
	public static boolean check(int y, int x)
	{
		return (y >= 0 && x >= 0 && x < col && y < row);
	}
	
	

}

class Point
{
	int y, x;
	public Point(int i, int j)
	{
		y = i;
		x = j;
	}
}


