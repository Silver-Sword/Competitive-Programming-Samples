import java.util.*;
import java.io.*;

public class rullete 
{
  
	static int JACK=11, QUEEN=12, KING=13, ACE=14;
	static int[] ranks, suits, rankFreq, suitFreq;
	static long score;
	static int numApplied, lastApplied;
	public static void main(String[] args) 
	{
	    FS in = new FS(System.in);
	    PrintWriter out = new PrintWriter(System.out);
	    
	    rankFreq = new int[15];
	    suitFreq = new int[4];
	
	    HashMap<Character, Integer> getSuitInd = new HashMap<>();
	    getSuitInd.put('D', 0);
	    getSuitInd.put('H', 1);
	    getSuitInd.put('C', 2);
	    getSuitInd.put('S', 3);
	
	    HashMap<Character, Integer> getRankInd = new HashMap<>();
	    getRankInd.put('J', 11);
	    getRankInd.put('Q', 12);
	    getRankInd.put('K', 13);
	    getRankInd.put('A', 14);
	
	    ranks = new int[5];
	    suits = new int[5];
	    score = 0;
	    for(int i=0; i<5; i++) 
	    {
	    	String raw = in.next();
      
	    	char suitc = raw.charAt(raw.length()-1);
	    	int suitInd = getSuitInd.get(suitc); 
	    	suitFreq[suitInd]++;
	    	suits[i] = suitInd;

	    	char rankc = raw.charAt(0);  // going to special case 10
	    	int rankInd = -1;
	    	if(getRankInd.containsKey(rankc)) 
	    	{
	    		rankInd = getRankInd.get(rankc);
	    	} 
	    	else 
	    	{
	    		rankInd = (int)(rankc - '0');
	    	}
	    	
	    	if(rankInd == 1) 
	    		rankInd = 10; // special case 10
	    	rankFreq[rankInd]++;
	    	ranks[i] = (rankInd > 10 ? 10 : rankInd);
	    	score += ranks[i];
	    }

	    numApplied = 0;

	    // Rule 1
	    numApplied++;
	    score++;
	    score += rankFreq[JACK]*getScore(ranks[0]);

	    // Rule 2
	    score *= 2;
	    numApplied++;

	    // Rule 3
	    boolean allSuits = true; 
	    for(int i=0; i<4; i++) {
	    	if (suitFreq[i] == 0) 
	    		allSuits = false;
	    }	
	    if(allSuits) {
	    	score *= 2;
	    	numApplied++;
	    }
	    
	    // Rule 4 - 11
	    for(int rule = 4; rule < 12; rule++)
	    	rule(rule);

	    // Rule 12 
	    if (rankFreq[2] > 0) {
	    	rule(lastApplied);

	    }

	    // Rule 13
	    /*if (rankFreq[2] > 0) {
	    	long temp = score;
	    	long toAdd = 1;
	    	
	    	for(int i = 2; i <= temp; i++)
	    	{
	    		if(temp % i == 0)
	    		{
	    			toAdd *= i;
	    			do { temp /= i; } while(temp % i == 0);
	    		}
	    	}
	    	
	    	score += toAdd;
	    }*/
	    if(rankFreq[2] > 0)
	    {
	    	score *= 2;
	    }

	    out.println(score);
	    out.flush();
  }
	
	public static void rule(int id)
	{
		
		if(id == 4)
		{
			lastApplied = id;
			// Rule 4
		    int numRed = suitFreq[0] + suitFreq[1];
		    int numBlack = suitFreq[2] + suitFreq[3];
		    score += Math.abs(numRed - numBlack);
		    numApplied++;
		}
		
		else if(id == 5)
		{
		    // Rule 5
		    if (score % 2 == 0) {
		    	lastApplied = id;
		    	numApplied++;
		    	int toAdd = 0;
		    	for(int i=1; i<=score; i++) 
		    	{
		    		if (score % i == 0) 
		    			toAdd += i;
		    	}	
		    	score += toAdd;
		    }
		}
		
		else if(id == 6)
		{
		    // Rule 6
		    if (rankFreq[7] == 4) {
		    	numApplied++;
		    	lastApplied = id;
		    	score -= 121;
		    }
		}
		
		else if(id == 7)
		{
		    // Rule 7
		    if (score >= 0) {
		    	numApplied++;
		    	lastApplied = id;
		    	int minScore = 100;
		    	for(int i=0; i<5; i++) {
		    		int val = getScore(ranks[i]);
		    		minScore = Math.min(val, minScore);
		    	}
		    	score += minScore;
		    }
		}
		
		else if(id == 8)
		{
		    // Rule 8
		    if (score < 0) {
		    	numApplied++;
		    	lastApplied = id;
		    	score *= -1;
		    }
		}
		
		else if(id == 9)
		{
		    // Rule 9
		    if (suitFreq[0] >= 3) {
		    	numApplied++;
		    	lastApplied = id;
		    	score += 1;
		    	// Swapping 6, 9 ; 2, 5
		    	for(int i=0; i<5; i++) {
		    		if(ranks[i] == 6) ranks[i] = 9;
		    		else if(ranks[i] == 9) ranks[i] = 6;
		    		else if(ranks[i] == 2) ranks[i] = 5;
		    		else if(ranks[i] == 5) ranks[i] = 2;
		    	}
	
		    	int temp = rankFreq[6];
		    	rankFreq[6] = rankFreq[9];
	     		rankFreq[9] = temp;
	
	     		temp = rankFreq[2];
	     		rankFreq[2] = rankFreq[5];
	     		rankFreq[5] = temp;
		    }
		}
		
		else if(id == 10)
		{
		    // Rule 10
		    // Looking for straight 
		    boolean hasStraight = checkStraight();
		    
		    if (hasStraight && rankFreq[ACE] > 0) {
		    	numApplied++;
		    	lastApplied = id;
		    	score += rankFreq[ACE]*5;
		    }
		}
		
		else if(id == 11)
		{
		    // Rule 11
		    if (numApplied > 8) {
		    	numApplied++;
		    	lastApplied = id;
		      int numOnes = 0;
		      long temp = score;
		      while (temp > 0) {
		        if(temp % 2 != 0) numOnes++;
		        temp /= 2;
		      }
		      score += numOnes;
		    }
		}
	}

	private static boolean checkStraight() 
	{
		int start = 0;
		while(rankFreq[start] == 0)
			start++;
		
		if(start > 10)
			return false;
		
		for(int i = 0; i < 5; i++)
			if(rankFreq[i+start] <= 0)
				return false;
		return true;
	}

  static int getScore(int rankInd) {
    if (rankInd <= 10) return rankInd; 
    return 10;
  }

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
}