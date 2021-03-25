import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class g {
    public static void main(String[] args) {
        FS in = new FS(System.in);

        int numCases = in.nextInt();
        while (numCases-- > 0) {
            int x = in.nextInt();

            if (isTrailingZero(x)) {
                System.out.println(solve(x - 1) + 1);
            } else {
                System.out.println(solve(x));
            }
        }
    }

    static int solve(int n) {
        String str = n + "";
        int len = str.length();
        if (len == 1)
            return n - 1;

        int result = 0;
        boolean midZero = midZero(str);
        boolean firstOne = (str.charAt(0) - '0') == 1;
        for (int i = 0; i < len; i++) {
            int digit = str.charAt(i) - '0';
            result += digit;
            
            if (!(midZero && firstOne) && i < len - 1 && digit != 0) {
                result++;
            }
        }

        return result + findPowTen(len - 1) - 1;
    }

    static boolean midZero(String str) {
        for (int i=1; i<str.length()-1; i++) {
            int val = str.charAt(i) - '0'; 
            if (val != 0) return false;
        }
        return true;
    }

    // static int solveTrailingZero(int n) {
    // String str = n + "";
    // int len = str.length();
    // int leading = str.charAt(0) - '0';
    // if (leading == 1) {
    // return findPowTen(len - 1);
    // }

    // System.out.println("isTrailing");
    // int result = findPowTen(len - 1);
    // for (int i = 1; i < len; i++) {
    // if (i == len - 1) {
    // result += 9;
    // } else {
    // result += 10;
    // }
    // }

    // System.out.println(result);
    // // 1999 -> 9991
    // result++;

    // result += (leading - 1 - 1);

    // result++;
    // // 5999 -> 6000
    // result++;

    // return result;
    // }

    static boolean isTrailingZero(int n) {
        String str = n + "";
        int len = str.length();
        if (len == 1)
            return false;

        for (int i = 1; i < len; i++) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }

        return true;
    }

    static int findPowTen(int numZeros) {
        if (numZeros == 0)
            return 0;

        return 9 + ((numZeros - 1) * 10) + findPowTen(numZeros - 1);

    }

    static class FS {
        BufferedReader in;
        StringTokenizer token;

        public FS(InputStream stream) {
            in = new BufferedReader(new InputStreamReader(stream));
        }

        public String next() {
            if (token == null || !token.hasMoreElements()) {
                try {
                    token = new StringTokenizer(in.readLine());
                } catch (Exception e) {
                }
                return next();
            }
            return token.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
/* 

3
5
26
843

4
17
44


3


3
100
101
201

28
29
31


3
2001
2201
2200

60
63
62




5
10000
100000000
123456789
37
1000000000

I think?
96
352
404
19
441



*/