// AI Jeopardy
// KTH Challenge 2020

// Problem Description
/*
    Given an integer X, output an n and k for which n choose k = X.
    If there are multiple options, print the n and k for which n is smaller, then
    for which k is smaller.

    X <= 10^100
*/

// Solution Description
/*
    You only need to check for k up to 100, since (200 choose 100) > 10^100.
    Since n choose k = n! / ((n-k)! k!), you can multiply X by k! and binary search
    for n.
*/

import java.util.*;
import java.math.*;
public class jeopardy {

    static int calc(BigInteger n, int k, BigInteger X)
    {
        BigInteger ret = BigInteger.ONE;

        for(int i = 0; i < k; i++)
        {
            ret = ret.multiply(n);
            n = n.subtract(BigInteger.ONE);
            if(ret.compareTo(X) > 0) return 1;
        }

        return ret.compareTo(X);
    }

    static BigInteger findN(BigInteger target, int k)
    {
        BigInteger lo = BigInteger.valueOf(k), hi = target.divide(BigInteger.valueOf(2)), mid;

        while(lo.compareTo(hi) <= 0)
        {
            mid = (lo.add(hi).divide(BigInteger.valueOf(2)));
            int comp = calc(mid, k, target);

            if(comp == 0)
            {
                return mid;
            }

            else if(comp < 0)
            {
                lo = mid.add(BigInteger.ONE);
            }

            else
                hi = mid.subtract(BigInteger.ONE);
        }

        return BigInteger.valueOf(-1);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BigInteger X = new BigInteger(scan.next());
        
        if(X.equals(BigInteger.ONE))
        {
            System.out.println("0 0");
        }

        else
        {
            BigInteger bestN = X.add(BigInteger.ZERO); int bestK = 1;
            for(int k = 2; k <= 200; k++)
            {
                X = X.multiply(BigInteger.valueOf(k));
                BigInteger N = findN(X, k);

                if(N.equals(BigInteger.valueOf(-1))) continue;

                if(N.compareTo(bestN) < 0 || (N.compareTo(bestN) == 0 && k < bestK))
                {
                    bestN = N.add(BigInteger.ZERO);
                    bestK = k;
                }
            }

            System.out.println(bestN.toString() + " " + Integer.toString(bestK));
        }

        scan.close();
    }
}