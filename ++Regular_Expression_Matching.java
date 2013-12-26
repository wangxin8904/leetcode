public class Solution {
    
	//  http://blog.csdn.net/pickless/article/details/9043389
    private boolean dfs(String s, String p, int sp, int pp) {
        if(pp == p.length()) {
            return (sp == s.length());
        }
        
        if(pp < p.length() - 1 && p.charAt(pp + 1) == '*') {
            while(sp < s.length() && 
            		(s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '.' )) {
                if(dfs(s, p, sp, pp + 2)) {
                    return true;
                }
                sp++;
            }
            return dfs(s, p, sp, pp + 2);
        }
        else {
            if( sp < s.length() && (p.charAt(pp) == '.' || p.charAt(pp) == s.charAt(sp)) ) {
                return dfs(s, p, sp+1, pp+1);
            } 
        }
        return false;
    }
    
    public boolean isMatch(String s, String p) {
        return dfs(s,p, 0, 0);
    }
    
}
