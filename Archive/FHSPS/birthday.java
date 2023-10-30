import java.util.Scanner;
import java.util.TreeSet;

public class birthday {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 1; t <= tt; t++)
		{
			int n1 = scan.nextInt();
			int n2 = scan.nextInt();
			
			int[] ray = new int[n1];
			int[] fave = new int[n2];
			TreeSet<Integer> tree = new TreeSet<Integer>();
			int count = 0;
			
			for(int i = 0; i < n1; i++)
				ray[i] = scan.nextInt();
			
			for(int i = 0; i < n2; i++)
				tree.add(scan.nextInt());
			
			for(int i = 0; i < n1; i++)
				if(tree.contains(ray[i]))
					count++;
			
			System.out.println("Birthday #" + t + ": " + count);
		}
		scan.close();
	}

}
