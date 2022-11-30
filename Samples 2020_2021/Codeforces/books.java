import java.util.Arrays;
import java.util.Scanner;

public class books {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		
		Book[] books = new Book[n];
		for(int i = 0; i < n; i++)
		{
			books[i] = new Book(scan.nextInt(), scan.nextInt(), scan.nextInt());
		}
		
		Arrays.sort(books);
		
		

	}

}

class Book implements Comparable<Book>
{
	boolean alice, bob;
	int min;
	
	public Book(int m, int a, int b)
	{
		min = m;
		alice = a == 1;
		bob = b == 1;
	}

	@Override
	public int compareTo(Book book) {
		if(alice == book.alice && bob == book.bob)
		{
			return min - book.min;
		}
		
		if(alice == book.alice)
			return bob ? -1 : 1;
		else
			return alice ? -1 : 1;
	}
}
