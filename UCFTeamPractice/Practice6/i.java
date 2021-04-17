import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class i {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String[] in;
		n = parse(scan.readLine());
		
		HashMap<Integer, Switch> switches = new HashMap<Integer, Switch>();
		switches.put(0, new Switch(0, 0, 0));
		Switch[] platformSwitch = new Switch[26];
		long[] platformPath = new long[26];
		int s = 1;
		Switch temp;
		Switch next;
		
		for(int i = 0; i < n; i++)
		{
			in = scan.readLine().split(" ");
			//switch
			if(in[0].charAt(0) == 's')
			{
				temp = switches.get(parse(in[1]));
				next = new Switch( s, ( temp.id + temp.connections ) * 2, temp.depth + 1);
				next.inbound = temp;
				switches.put(s, next);
				temp.connections ++;
				
			}
			
			//platform
			else
			{
				temp = switches.get(parse(in[1]));
				long path = temp.id + temp.connections;
				int id = in[2].charAt(0) - 'a';
				temp.connections ++;
				platformSwitch[id] = temp;
				platformPath[id] = path;
			}
			s++;
		}
		
		int trains = parse(scan.readLine());
		ArrayList<Action> timetable = new ArrayList<Action>();
		
		for(int t = 0; t < trains; t++)
		{
			in = scan.readLine().split(" ");
			addPath(parse(in[0]), in[1].charAt(0), platformSwitch, platformPath, timetable);
		}
		
		Collections.sort(timetable);
		int len = timetable.size();
		out.println(len);
		for(int i = 0; i < len; i++)
			out.println(timetable.get(i).toString());
		out.flush();
	}

	private static void addPath(int start, char end, Switch[] platformSwitch, long[] platformPath, ArrayList<Action> timetable) 
	{
		long path = platformPath[end - 'a'];
		
		platformSwitch[end - 'a'].getAns(path, start, timetable);
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Action implements Comparable<Action>
	{
		long toggle;
		int switchID;
		
		public Action(long t, int s)
		{
			toggle = t;
			switchID = s;
		}
		
		public String toString()
		{
			return switchID + " " + toggle;
		}

		@Override
		public int compareTo(Action o) {
			if(toggle == o.toggle)
				return Integer.compare(switchID, o.switchID);
			return Long.compare(toggle, o.toggle);
		}
		
	}
	
	static class Switch
	{
		int connections;
		long id;
		int index;
		int depth;
		
		int state;
		Switch inbound;
		
		public Switch(int i, long id, int d)
		{
			index = i;
			this.id = id;
			connections = 0;
			state = 0;
			depth = d;
		}
		
		public void addInbound(Switch s)
		{
			inbound = s;
		}
		
		public long getAns(long path, long start, ArrayList<Action> timetable)
		{
			if(inbound == null)
				return 0;
			
			long time = inbound.getAns(path / 2, start, timetable) + 1;
			
			if(state != (path & 1) )
			{
				timetable.add(new Action ( start + time, index ) );
				state = (int) ( path & 1 );
			}
			
			return time ;
		}
		
		public String toString()
		{
			return Integer.toString(index);
		}
	}
}
