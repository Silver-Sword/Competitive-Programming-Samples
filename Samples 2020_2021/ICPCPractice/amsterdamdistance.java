import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class amsterdamdistance {
	static int SLICES, RINGS;
	static double RADIUS;
	static double ringDist;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String[] in = scan.readLine().split(" ");
		SLICES = parse(in[0]);
		RINGS = parse(in[1]);
		RADIUS = Double.parseDouble(in[2]);
		
		ringDist = RADIUS / RINGS;
		
		in = scan.readLine().split(" ");
		Point start = new Point(in[0], in[1]);
		Point end = new Point(in[2], in[3]);
		
		double minDist = Double.MAX_VALUE;
		double dist;
		
		for(int r = Math.max(start.ring, end.ring); r >= 0; r--)
		{
			dist = travelToRing(start, r);
			dist += travelToRing(end, r);
			dist += travelAcrossRing(r, start.slice, end.slice);
			
			if(dist < minDist)
				minDist = dist;
		}
		
		System.out.printf("%.14f\n", minDist);
	}

	private static double travelAcrossRing(int ringID, int sliceStart, int sliceEnd) {
		int numSlices = Math.abs(sliceStart - sliceEnd);
		
		double radians = ( Math.PI * numSlices ) / SLICES;
		double radius = ringDist * ringID;
		
		return radians * radius;
		
	}

	private static double travelToRing(Point point, int ring) 
	{
		return Math.abs(ring - point.ring) * ringDist;
	}

	public static int parse(String num)
	{
		return Integer.parseInt(num);
	}
	
	static class Point
	{
		int slice, ring;
		
		public Point(String s1, String s2)
		{
			slice = parse(s1);
			ring = parse(s2);
		}
	}
}
