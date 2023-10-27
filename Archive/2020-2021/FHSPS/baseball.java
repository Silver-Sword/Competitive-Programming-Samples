import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class baseball {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(scan.readLine());
		
		for(int t = 0; t < tt; t++)
		{
			n = Integer.parseInt(scan.readLine());
			
			ArrayList<Player> player = new ArrayList<Player>();
			for(int i = 0; i < n; i++)
			{
				player.add(new Player(scan.readLine()));
			}
			
			Collections.sort(player);
			
			System.out.println("Season #" + (t+1) + ":");
			for(int i = 0; i < n ;i++)
				System.out.println(player.get(i).name);
			
			System.out.println();
		}

	}

}

class Player implements Comparable<Player>
{
	String name;
	
	double batAvg, onBasePercent, sluggingPercent;
	
	int atBats, hits, walks, bases, plateappearance;
	
	public Player(String in)
	{
		String[] temp = in.split(" ");
		
		name = temp[0] + ", " + temp[1];
		atBats = 0;
		hits = 0;
		walks = 0;
		bases = 0;
		
		compute(temp);
		
		batAvg = ((double) hits) / ((double)atBats);
		onBasePercent = ((double)(hits + walks)) / plateappearance;
		sluggingPercent = ((double)bases ) / atBats;
	}
	
	public void compute(String[] in)
	{
		int m = Integer.parseInt(in[2]);
		
		plateappearance = m;
		
		for(int i = 3; i < m + 3; i++)
		{
			increment(in[i]);
		}
	}
	
	public void increment(String s)
	{
		if(s.equals("1B"))
		{
			atBats++;
			hits++;
			bases ++;
			return;
		}
		
		else if(s.equals("2B"))
		{
			atBats++;
			hits ++;
			bases += 2;
			return;
		}
		
		else if(s.equals("3B"))
		{
			atBats++;
			hits++;
			bases += 3;
			
			return;
		}
		
		else if(s.equals("HR"))
		{
			atBats++;
			hits++;
			bases += 4;
			return;
		}
		
		else if(s.equals("BB"))
		{
			walks++;
			return;
		}
		
		else if(s.equals("SAC"))
		{
			return;
		}
		
		else
			atBats++;
		
		
	}

	@Override
	public int compareTo(Player p) {
		if(equals(batAvg, p.batAvg))
		{
			if(equals(onBasePercent, p.onBasePercent))
				return (p.sluggingPercent - sluggingPercent) > 0 ? 1 : -1;
			
			return (p.onBasePercent - onBasePercent) > 0 ? 1 : -1;
		}
		
		return (p.batAvg - batAvg) > 0 ? 1 : -1;
	}
	
	public boolean equals(double a, double b)
	{
		return Math.abs(a - b) < 1e-9;
	}
	
}
