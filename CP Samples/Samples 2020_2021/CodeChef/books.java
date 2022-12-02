import java.util.Scanner;

public class books {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		for(int c = 0; c < cases; c++)
		{
			int numStudents = scan.nextInt();
			int numBooks = scan.nextInt();
			
			int[] pages = new int[numBooks];
			for(int i = 0; i < numBooks; i++)
				pages[i] = scan.nextInt();
			
			int[] lower = new int[numStudents];
			int[] upper = new int[numStudents];
			for(int i = 0; i < numStudents; i++)
				lower[i] = scan.nextInt();
			for(int i = 0; i < numStudents; i++)
				upper[i] = scan.nextInt();
			

			int booksAns, pagesAns;
			for(int student = 0; student < numStudents; student++)
			{
				booksAns = 0;
				pagesAns = 0;
				
				for(int book = 0; book < numBooks; book++)
				{
					int pgs = pages[book];
					if(pgs >= lower[student] && pgs <= upper[student])
					{
						booksAns++;
						pagesAns += pgs;
					}
				}
				
				System.out.println(booksAns + " " + pagesAns);
			}
		}
	}
}
