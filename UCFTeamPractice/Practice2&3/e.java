import java.util.Scanner;

public class e {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in); 

    char[] arr = in.next().toCharArray(); 

    long ans = 0;
    boolean didExchange = false;
    for(int i=0; i<arr.length; i++) {
      int d = (int)(arr[i] - '0');
      int exchange = 10-d+1 + (didExchange ? -2 : 0);
      int direct = d;
      if(exchange <= direct) {
        ans += exchange;
        didExchange = true;
      } else {
        ans += direct; 
        didExchange = false;
      }
    }
    System.out.println(ans);

  }
}
