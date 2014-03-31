// http://n00tc0d3r.blogspot.com/2013/05/wildcard-matching.html
// http://xoomer.virgilio.it/acantato/dev/wildcard/wildmatch.html

public class Solution {
    // http://liaoxl.github.io/blog/20131124/leetcode-wm/

    private boolean check(String s, String p) {
        
        // 
    	int sp = 0, pp = 0;
    	int star_s = -1;
    	int star_p = -1;
        while(sp < s.length() ) {
        	
        	if(pp < p.length() 
        			&& (p.charAt(pp) == '?' || s.charAt(sp) == p.charAt(pp))) {
        		sp++;
        		pp++;
        		continue;
        	}
        	
        	if(pp < p.length() && p.charAt(pp) == '*') {
        		// check the later part of p == all *
        		while(pp < p.length() && p.charAt(pp) == '*') {
        			pp++;
        		}
        		if(pp == p.length()) return true;
        		
        		star_s = sp;
        		star_p = pp;
        		
        		continue;
        	}
        	
        	if( (pp == p.length() || (pp < p.length() && p.charAt(pp) != s.charAt(sp)) ) &&
        			star_p != -1) {
        		pp = star_p;
        		sp = ++star_s;
        		continue;
        	}
        	
            return false;
        }
        
        //if(sp != s.length()) return false;
        
        String temp = p.substring(pp, p.length());
        return temp.matches("[*]*");
    }
    
    public boolean isMatch(String s, String p) {
        return check(s, p);
    }			
	
}


/* dfs TLE

public class Solution {
	
    private boolean dfs(String s, String p, int st, int pt) {
        if(pt == p.length()) {
            return (st == s.length());
        }
        
        if(p.charAt(pt) != '*') {
            if(st < s.length() && (p.charAt(pt) == '?' || p.charAt(pt) == s.charAt(st))) {
                return dfs(s, p, st + 1, pt + 1);
            }
            return false;
        } else {
            while(st <= s.length()) {
                if(dfs(s, p, st, pt + 1)) {
                    return true;
                }
                st++;
            }
            return false;
        }
    }
    
    public boolean isMatch(String s, String p) {
        return dfs(s, p, 0, 0);
    }	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution so = new Solution();
		
		boolean flag = so.isMatch("babaaababaabababbbbbbaabaabbabababbaababbaaabbbaaab", "***bba**a*bbba**aab**b");
		System.out.print(flag);
	}
}
*/