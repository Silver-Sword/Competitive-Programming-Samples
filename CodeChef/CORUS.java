import java.util.Scanner;

public class CORUS {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t= 0; t < tt; t++)
		{
			int n = scan.nextInt();
			int[] types = new int[26];
			int q = scan.nextInt();
			scan.nextLine();
			
			String temp = scan.nextLine();
			for(char c : temp.toCharArray())
				types[c-'a']++;
			
			for(int i = 0; i < q; i++)
			{
				int c = scan.nextInt();
				int sum = 0;
				for(int type : types)
				{
					if(type > c)
						sum += type - c;
				}
				
				System.out.println(sum);
			}
		}

	}

}
