import java.util.Scanner;

public class July20_2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int n = scan.nextInt();
			int[] point = {0, 0};
			
			for(int i = 0; i < n; i++)
			{
				int a = pow(scan.nextInt()), b = pow(scan.nextInt());
				
				if(a >= b)
					point[0]++;
				if(b >= a)
					point[1]++;
				
			}
			
			if(point[0] == point[1])
				System.out.println(2 + " " + point[0]);
			else if(point[0] > point[1])
				System.out.println(0 + " " + point[0]);
			else
				System.out.println(1 + " " + point[1]);
		}

	}
	
	public static int pow(int num)
	{
		if(num < 10)
			return num;
		
		return (num % 10) + pow(num /  10);
	}
}
