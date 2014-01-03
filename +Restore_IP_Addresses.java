
public class Solution {
    
    // check len <= 12
	// every part must be a valid num "0" or "10", not "01"
    
    private ArrayList<String> ans = null;
    
    private boolean check(String s, ArrayList<Integer> pos) {
        String str = "";
        int start = 0; int end = pos.get(0);
        for(int i = 0; i <= pos.size(); i++) {
            if(i < 1) start = 0;
            else start = pos.get(i - 1) + 1;
            
            if(i < pos.size()) end = pos.get(i) + 1;
            else end = s.length();
            
            String temp = s.substring(start, end);
            
            if(temp.charAt(0) == '0' && temp.length() > 1) {
            	return false;
            }
            
            int n = Integer.parseInt(temp);
            if(n > 255 || n < 0) return false;
            
            str += temp;
            if(i < pos.size()) str += ".";
        }
        ans.add(str);
        return true;
    }
    
    private void dfs(String s, ArrayList<Integer> pos, int rest) {
        if(rest == 0) {
            check(s, pos);
            return;
        }
        
        int lastPos = -1;
        if(pos.size() >= 1) lastPos = pos.get(pos.size() - 1);
        for(int j = lastPos + 1; j < s.length() -1 && j <= lastPos + 3; j++) {
        	//if(j > lastPos + 1 && s.charAt(lastPos + 1) == '0') continue;
        	
            pos.add(j);
            dfs(s, pos, rest - 1);
            pos.remove(pos.size() - 1);
        }
    }
    
    
    public ArrayList<String> restoreIpAddresses(String s) {
        ans = new ArrayList<String>();
        if(s.length() > 12) return ans;
        
        ArrayList<Integer> pos = new ArrayList<Integer>();
        
        dfs(s, pos, 3);
        
        return ans;
    }
}
