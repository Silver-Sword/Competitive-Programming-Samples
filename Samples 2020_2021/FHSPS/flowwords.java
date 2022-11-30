import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class flowwords {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		scan.nextLine();
		
		for(int t = 1; t <= tt; t++)
		{
			int lines = scan.nextInt();
			scan.nextLine();
			String input = "";
			
			for(int i = 0; i < lines; i++)
				input += scan.nextLine() + " ";
			
			System.out.println("Paragraph #" + t + ":");
			
			String[] sent = input.split("\\.");
			
			boolean max = true;
			
			for(int i = 0; i < sent.length; i++)
			{
				if(i == sent.length - 1 && (sent[i].isEmpty() || sent[i].equals(" ")))
					break;
				
				boolean flows = flow(sent[i].trim().split(" "));
				if(!flows)
					max = false;
				
				System.out.print("Sentence #" + (i+1) + ": ");
							
				System.out.println(flows ? "Brett Flows!" : "It's Broken.");
				
			}
			
			if(max)
				System.out.println("MAX FLOW");
		}

	}
	
	public static boolean flow(String[] line)
	{
		ArrayList<String> words = new ArrayList<String>();
		Collections.addAll(words, line);
		
		for(int i = 0; i < line.length - 1; i++)
		{
			if(words.get(i).isEmpty())
			{
				words.remove(i);
				i--;
				continue;
			}
			
			if(words.get(i+1).isEmpty())
			{
				words.remove(i+1);
				i--;
				continue;
			}
			
			if(line[i].compareTo(line[i+1]) > 0)
				return false;
		}
		
		return true;
	}
}
