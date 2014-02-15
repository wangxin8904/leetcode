// dp
// http://blog.unieagle.net/2012/09/29/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Ainterleaving-string%EF%BC%8C%E4%BA%8C%E7%BB%B4%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92/
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        
        dp[0][0] = true;
        for(int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1) );
        }
        for(int i = 1; i <= s2.length(); i++) {
            dp[0][i] = dp[0][i-1] && (s2.charAt(i - 1) == s3.charAt(i - 1) );
        }

        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
            	int p1 = i - 1, p2 = j - 1, p3 = i + j - 1;
                dp[i][j] = (dp[i][j-1] && s2.charAt(p2) == s3.charAt(p3) ) ||
                    (dp[i-1][j] && s1.charAt(p1) == s3.charAt(p3) );
                
            }
        }

        return dp[s1.length()][s2.length()];
    }
}



/*     attempt 2 -- TLE

	private boolean flag = false;
	private int one = 0, two = 0, three = 0;
	
	private void dfs(String s1, String s2, String s3) {
		if(flag) return;
		if(three == s3.length()) {
			flag = true;
			return;
		}
		
		
		char c = s3.charAt(three++);
		if(one < s1.length() && c == s1.charAt(one)) {
			one++;
			dfs(s1,s2,s3);
			one--;
		}
		if(two < s2.length() && c == s2.charAt(two)) {
			two++;
			dfs(s1,s2,s3);
			two--;
		}
		three--;
	}
    
    public boolean isInterleave(String s1, String s2, String s3) {
    	one = 0;
    	two = 0;
    	three = 0;
    	if(s1.length() + s2.length() != s3.length()) return false;
    	
    	flag = false;
        dfs(s1,s2,s3);
        return flag;
    }
*/


//       attemp 1 -- TLE
//	// brute force traversal
//    private boolean flag = false; 
//    private void dfs(String cur, String s1, String s2, String s3) {
//        if(flag) return;
//        if(s1.length() == 0 && s2.length() == 0) {
//            flag = s3.equals(cur);
//            return;
//        }
//        
//        if(s1.length() == 0) {
//            cur = cur + s2.substring(0,1);
//            dfs(cur, s1, s2.substring(1), s3);
//            cur = cur.substring(0, cur.length()-1);
//        } else if(s2.length() == 0) {
//            cur = cur + s1.substring(0,1);
//            dfs(cur, s1.substring(1), s2, s3);
//            cur = cur.substring(0, cur.length() - 1);
//        } else {
//            cur = cur + s2.substring(0,1);
//            dfs(cur, s1, s2.substring(1), s3);
//            cur = cur.substring(0, cur.length()-1);
//            
//            cur = cur + s1.substring(0,1);
//            dfs(cur, s1.substring(1), s2, s3);
//            cur = cur.substring(0, cur.length() - 1);
//        }
//    }
//    
//    public boolean isInterleave(String s1, String s2, String s3) {
//        flag = false;
//        String cur = "";
//        
//        dfs(cur, s1, s2, s3);
//        
//        return flag;
//    }
