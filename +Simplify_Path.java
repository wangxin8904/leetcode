
public class Solution {
    public String simplifyPath(String path) {
        String[] str = path.split("/");
        
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < str.length; i++) {
            if(str[i].equals("") || str[i].equals(".")) {
                continue;
            }
            else if(str[i].equals("..")) {
                if(!stack.isEmpty()) {
                    stack.pop();
                } else {
                    
                }
            }
            else {
                stack.push(str[i]);
            }
        }
        
        String ans = "";
        ArrayList<String> v = new ArrayList<String>();
        while(!stack.isEmpty()) {
            String cur = stack.peek();
            stack.pop();
            
            v.add(cur);
        }
        for(int i = v.size() - 1; i >= 0; i--) {
        	ans += "/" + v.get(i);
        }
        
        if(ans.equals("")) {
        	return "/";
        } else
        	return ans;
    }
}

