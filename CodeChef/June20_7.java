import java.util.ArrayList;
import java.util.Scanner;

public class June20_7 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tt = scan.nextInt();
		
		for(int t = 0; t < tt; t++)
		{
			int[] start = new int[3];
			int[] end = new int[3];
			
			for(int i = 0; i < 3; i++)
				start[i] = scan.nextInt();
			for(int i = 0; i < 3; i++)
				end[i] = scan.nextInt();
			
			int max = 3;
			for(int i = 0; i < 3; i++)
				if(start[i] == end[i])
					max--;
			
			
			if(max == 1 || max == 0)
			{
				System.out.println(max);
				continue;
			}
			
			if(max == 2)
			{
				int d0 = -1;
				for(int i = 0; i < 3; i++)
					if(start[i] == end[i])
						d0 = i;
				
				//multiply
				boolean possible = true;
				for(int i = 0; i < 3; i++)
				{
					if(start[i] == 0 && d0 != i)
						possible = false;
					
					else if(start[i] == 0)
						continue;
					
					else if(end[i] % start[i] != 0)
						possible = false;
				}
				
				if(possible)
				{
					int result = 0;
					for(int i = 0; i < 3; i++)
					{
						if(end[i] == 0 || d0 == i)
							continue;
						result = result ^ (end[i] / start[i]);
					}
					
					if(result == 0)
					{
						System.out.println(1);
						continue;
					}
				}
				
				//add
				int result = 0;
				for(int i = 0;i < 3; i++)
					result = result ^ (end[i] - start[i]);
				
				if(result == 0)
					System.out.println(1);
				else
					System.out.println(2);
				
				continue;
				
			}
			
			else
			{
				
				int multiMax = 3;
				//if two or three matches between addition or multiplication
				ArrayList<Integer> dMult = new ArrayList<Integer>();
				for(int i = 0;i < 3; i++)
				{
					if(end[i] == 0)
						dMult.add(0);
					else if(start[i] == 0)
						continue;
					else if(end[i] % start[i] == 0)
						dMult.add(end[i] / start[i]);
				}
				
				if(dMult.size() == 3 && dMult.get(0) == dMult.get(1) && dMult.get(1) == dMult.get(2))
					System.out.println(1);
				else if(dMult.size() > 1)
				{
					for(int i = 0; i < dMult.size(); i++)
						for(int j = i + 1; j < dMult.size(); j++)
							if(dMult.get(i) == dMult.get(j))
								multiMax --;
				}
				
				int addMax = 3;
				int[] dAdd = new int[3];
				for(int i = 0; i < 3; i++)
					dAdd[i] = end[i] - start[i];
				
				if(dAdd[0] == dAdd[1] && dAdd[1] == dAdd[2])
				{
					System.out.println(1);
					continue;
				}
				
				for(int i = 0; i < 3; i++)
					for(int j = i+1; j < 3; j++)
						if(dAdd[i] == dAdd[j])
							addMax --;
				
				if(addMax < 3 || multiMax < 3)
				{
					System.out.println(Math.min(addMax, multiMax));
					continue;
				}
				
				//if Cramer's rule applies
				System.out.println(Cramer(start, end) ? 2 : 3);
				
			}
		}

	}
	
	public static boolean Cramer(int[] start, int[] end)
	{
		int[] m = new int[2];
		int[] b = new int[2];
		for(int i = 0; i < 2; i++)
		{
			if(start[i] - start[i+1] == 0)
			{
				if((end[i] + end[i+1] % 2) != 0)
					return false;
				
				b[i] = (end[i] + end[i+1]) / 2;
				if((end[i] - b[i]) % start[i] != 0)
					return false;
				m[i] = (end[i] - b[i]) / start[i];
			}
					
			else
			{
				int num = end[i] - end[i+1];
				int denom = start[i] - start[i+1];
				
				if(num % denom != 0)
					return false;
				m[i] = num / denom;
				
				num = start[i] * end[i+1] - end[i] * start[i+1];
				if(num % denom != 0)
					return false;
				b[i] = num / denom;
			}
		}
		
		return (m[0] == m[1]) && (b[0] == b[1]);
	}

}

