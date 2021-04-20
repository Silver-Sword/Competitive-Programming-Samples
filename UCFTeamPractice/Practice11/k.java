import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class k {
	static int[][] map = { {1, 3}, {0, 2}, {1}, {0, 2}, {1}, {0}, {1}, {0, 4}, {4}, {0}, {}, {0}, {0, 5}, {0}, {3}, {0, 3}, {3}, {3}, {}, {5}, {}, {}, {}, {4}, {4}, {0, 1}, {}, {5}, {2}, {2}, {2}, {2, 4}, {2, 4}, {2}, {2}, {2}, {5}, {}, {0, 1}, {4}, {4}, {}, {}, {}, {5}, {}, {3}, {3}, {0, 3}, {3}, {0}, {0, 5}, {0}, {}, {0}, {4}, {0, 4}, {1}, {0}, {1}, {0, 2}, {1}, {0, 2}, {1, 3}};
	
  public static void main(String[] args) 
  {
	  bruteForceMap();
	  FS in = new FS(System.in);
	  PrintWriter out = new PrintWriter(System.out);
	  //bruteForce();
    
	  String raw = in.next();
	  char[] arr = raw.toCharArray();
	  HashSet<Integer> ans = new HashSet<Integer>();

	  for(int i = 5; i < arr.length; i += 6)
	  {
		  int val = 0;
		  for(int j = 0; j < 6; j++)
			  val = (val * 2) + (arr[j + i - 5] - '0');
		  
		  for(int v : map[val])
		  {
			  ans.add(v + (i - 5));
		  }
	  }
    
	  if(arr.length % 6 != 0)
	  {
		  if(arr[arr.length - 3] == arr[arr.length - 2] && arr[arr.length - 2] == arr[arr.length - 1])
			  ans.add(arr.length - 2);
	  }
    
	  //check(arr, ans);
    
	  out.println(ans.size());
	  for(int a : ans) {
		  out.print((a+1) + " ");
	  }
	  
	  if(ans.size() != 0) out.println();
	  out.flush();
    
    
  }
  
  public static void bruteForceMap()
  {
	  map = new int[64][];
	  for(int i = 0; i < 64; i++)
	  {
		  String s = Integer.toBinaryString(i);
		  while(s.length() < 6) s = "0" + s;
		  char[] ray = s.toCharArray();
		  int[] arr = new int[6];
		  for(int j = 0; j < 6; j++)
			  arr[j] = ray[j] - '0';
		  
		  map[i] = brute(arr);
		  
	  }
	  
	  System.out.print("{ ");
	  
	  for(int i = 0; i < 64; i++)
	  {
		  System.out.print(Arrays.toString(map[i]).replace("[", "{").replace("]", "}") + ", ");
	  }
	  
	  System.out.println("};");
  }
  
  public static int[] brute(int[] ray)
  {
	  if(valueOf(ray) >= 4)
		  return new int[0];
	  
	  for(int i = 0; i < 5; i++)
	  {
		  ray[i] ^= 1;
		  ray[i+1] ^= 1;
		  if(valueOf(ray) >= 4)
			  return new int[] {i};
		  ray[i] ^= 1;
		  ray[i+1] ^= 1;
	  }
	  
	  for(int i = 0; i < 5; i++)
	  {
		  for(int j = i + 1; j < 5; j++)
		  {
			  ray[i] ^= 1;
			  ray[i+1] ^= 1;
			  ray[j] ^= 1;
			  ray[j+1] ^= 1;
			  if(valueOf(ray) >= 4)
				  return new int[] {i, j};
			ray[i] ^= 1;
			ray[i+1] ^= 1;
			  ray[j] ^= 1;
			  ray[j+1] ^= 1;
		  }
	  }
	  
	  System.out.println("NO VALID FOR " + Arrays.toString(ray));
	  System.exit(0);
	  return new int[0];
  }
  
 private static int valueOf(int[] ray) 
 {
	int val = 0;
	
	for(int i = 1; i < 6; i++)
		if(ray[i] != ray[i-1])
			val ++;
	return val;
}

/* 	private static void check(char[] ray, HashSet<Integer> ans) 
  	{
  		int size = ray.length;
	    int value = 1;
	    int target = (size / 3) * 2;
	    
  		for(int a : ans)
  		{
  			ray[a] = op(ray[a]);
  			ray[a+1] = op(ray[a+1]);
  		}
  		
  		for(int i = 1; i < ray.length; i++)
  			if(ray[i] != ray[i-1])
  				value ++;
  		
  		if(value >= target)
  			return;
  		
  		 for(int i=0; i < ray.length; i++) 
  		 {
  			 if (ans.size() == ray.length/3) break; // already reached max capacity
  			 if(value >= target) break;

  			 if(i < ray.length-3 && ray[i] == ray[i+1] && ray[i+2] == ray[i+3]) 
  			 {
  				 // Greedy when you have 0011, 1100, 0000, or 1111
  				 if(!ans.add(i+1))
  					 ans.remove(i+1);
  				 ray[i+1] = op(ray[i+1]);
  				 ray[i+2] = op(ray[i+2]);
  				 value += 2;
  			 }
 	    }
  			
  	}
	
	public static void solve(char[] arr, String raw, HashSet<Integer> ans )
  	{
	 	int size = arr.length;
	    int value = 1;
	    int target = (size / 3) * 2;
	    
	    for(int j = 0; j < 2; j++)
	    {
		    for(int i=0; i<arr.length; i++) 
		    {
		      if (ans.size() == size/3) break; // already reached max capacity
		      if(value >= target) break;
	
		      if(i < arr.length-3 && arr[i] == arr[i+1] && arr[i+2] == arr[i+3]) {
		        // Greedy when you have 0011, 1100, 0000, or 1111
		        ans.add(i+1);
		        arr[i+1] = op(arr[i+1]);
		        arr[i+2] = op(arr[i+2]);
		        value += 2;
		      }
		    }
	    }

  }

  static char op(char a) {
    return (a == '0') ? '1' : '0';
  }*/
  static class FS {
    BufferedReader in;
    StringTokenizer token;
    public FS(InputStream stream) {
      in = new BufferedReader(new InputStreamReader(stream));
    }
    public String next() {
      if(token == null || !token.hasMoreElements()) {
        try{
          token = new StringTokenizer(in.readLine());
        } catch (Exception e) {}
        return next();
      }
      return token.nextToken();
    }
    public int nextInt() {
      return Integer.parseInt(next());
    }
  }
  
  /*public static void bruteForce()
  {
	  map = new int[64][];
	  for(int i = 0; i < 64; i++)
	  {
		  String s = Integer.toBinaryString(i);
		  while(s.length() < 6) s = "0" + s;
		  char[] ray = s.toCharArray();
		  HashSet<Integer> ans = new HashSet<Integer>();
		  
		  solve(ray, "", ans);
		  check(ray, ans);
		    
	  		for(int a : ans)
	  		{
	  			ray[a] = op(ray[a]);
	  			ray[a+1] = op(ray[a+1]);
	  		}
	  		
		  int[] result = new int[ans.size()];
		  int j = 0;
		  for(int a : ans)
		  {
			  result[j] = a;
			  j++;
		  }
		  
		  map[i] = result;
	  }
	  
	  map[4] = new int[] {1, 3};
	  map[59] = map[4];
	  map[8] = map[4];
	  map[55] = map[4];
  }*/
  
  
}
