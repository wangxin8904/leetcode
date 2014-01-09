public class Solution {
    
    private boolean check(String S, int start, HashMap<String, Integer> oriMap, String[] L) {
        int len = L[0].length();
        int flagNum = 0;
        
        HashMap<String, Integer> map = new HashMap<String, Integer>(oriMap);
        while(flagNum < L.length) {
        	if(start + len * flagNum + len > S.length()) return false;
        	
            String temp = S.substring(start + len * flagNum, start + len * flagNum + len);
            if(map.containsKey(temp)) {
        
                int idx = map.get(temp);
                if(idx > 1) {
                    map.put(temp, map.get(temp) - 1);
                } else if(idx == 1) {
                    map.remove(temp);
                }
            } else {
                return false;
            }
            
            flagNum++;
        }
        
        return true;
    }
    
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        
        //
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i < L.length; i++) {
        	if(map.containsKey(L[i])) {
        		map.put(L[i], map.get(L[i]) + 1);
        	} else {
        		map.put(L[i], 1);
        	}
        }
        
        for(int i = 0 ; i <= S.length()- L.length * L[0].length() ; i++) {
            if(check(S, i, map, L)) {
                ans.add(i);
            }
        }
    
        return ans;
    }
}
------------------- TLE ---------------

public class Solution {
    
    private boolean check(String S, int start, HashMap<String, Integer> oriMap, String[] L) {
        int len = L[0].length();
        int flagNum = 0;
        
        HashMap<String, Integer> map = new HashMap<String, Integer>(oriMap);
        while(flagNum < L.length) {
        	if(start + len * flagNum + len >= S.length()) return false;
        	
            String temp = S.substring(start + len * flagNum, start + len * flagNum + len);
            if(map.containsKey(temp)) {
        
                int idx = map.get(temp);
                if(idx > 1) {
                    map.put(temp, map.get(temp) - 1);
                } else if(idx == 1) {
                    map.remove(temp);
                }
            } else {
                return false;
            }
            
            flagNum++;
        }
        
        return true;
    }
    
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        
        //
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i < L.length; i++) {
        	if(map.containsKey(L[i])) {
        		map.put(L[i], map.get(L[i]) + 1);
        	} else {
        		map.put(L[i], 1);
        	}
        }
        
        for(int i = 0 ; i < S.length(); i++) {
            if(check(S, i, map, L)) {
                ans.add(i);
            }
        }
    
        return ans;
    }
}
