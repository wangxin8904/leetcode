
// Attempt 2, DP
public class Solution {
    
	// case 1: "" empty 
	// case 2: digits may be "0"-"9"  
	//  "01" should return 0 
    public int numDecodings(String s) {
        if(s.length() < 1) return 0;
        
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        
        for(int i = 1; i < dp.length; i++) {
            String temp = s.substring(i - 1 , i);
            int t = Integer.parseInt(temp);
            if(t >= 1 && t <= 26) {
                dp[i] += dp[i - 1];
            }
            
            
            if(i > 1) {
                temp = s.substring(i - 2 , i);
                if(temp.charAt(0) == '0') continue;
                t = Integer.parseInt(temp);
                if(t >= 1 && t <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        
        return dp[dp.length - 1];
    }
}


// Attempt 1, recursion
// TLE "9371597631128776948387197132267188677349946742344217846154932859125134924241649584251978418763151253"

/*

public class Solution {
    private int num;
    
    private void dfs(String s, ArrayList<Integer> pos) {
        int lastPos = -1;
        if(pos.size() > 0) 
            lastPos = pos.get(pos.size() - 1);
        
        if(lastPos == s.length() - 1) {
            num++;
            return;
        }
        
        // one more
        pos.add(lastPos + 1);
        dfs(s, pos);
        pos.remove(pos.size() - 1);
        
        // check if two more
        if(lastPos + 2 >= s.length()) return;
        
        String temp = s.substring(lastPos + 1, lastPos + 2 + 1);
        int t = Integer.parseInt(temp);
        if(t > 26) return;
        
        pos.add(lastPos + 2);
        dfs(s, pos);
        pos.remove(pos.size() - 1);
        
    }
    
    public int numDecodings(String s) {
        num = 0;
        ArrayList<Integer> pos = new ArrayList<Integer>();
        
        dfs(s, pos);
        
        return num;
    }
}   

*/


