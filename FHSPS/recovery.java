import java.util.Scanner;

public class recovery {
	static int q, s;
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 0; t < tt; t++)
		{
			q = scan.nextInt();
			s = scan.nextInt();
			scan.nextLine();
			
			char[][] ray = new char[s][q];
			for(int i = 0; i < s; i++)
				ray[i] = scan.nextLine().toCharArray();
			
			int[] target = new int[q+1];
			for(int i = 0; i < q+1; i++)
				target[i] = scan.nextInt();
			
			System.out.println(recur(0, ray, new int[s], target));
		}
	}
	public static int recur(int index, char[][] ray, int[] count, int[] target) {
		if(index >= q)
			return check(count, target);
		
		//return true + false;
		for(int i = 0; i < s; i++)
			if(ray[i][index] == 'T')
				count[i]++;
		int a = recur(index+1, ray, count, target);
		
		for(int i = 0; i < s; i++)
		{
			if(ray[i][index] == 'F')
				count[i]++;
			else
				count[i]--;
		}
		int b = recur(index+1, ray, count, target);
		
		//reset
		for(int i = 0; i < s; i++)
			if(ray[i][index] == 'F')
				count[i]--;
		
		return a+b;
		
		
	}
	
	public static int check(int[] count, int[] target) {
		int[] compareTo = new int[q+1];
		for(int i = 0; i < s; i++)
			compareTo[count[i]] ++;
		
		for(int i = 0 ; i < q+1; i++)
			if(compareTo[i] != target[i])
				return 0;
		
		return 1;
	}
}
