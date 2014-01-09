
public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<Integer>();
        
        for(int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals("+")) {
                int two = s.pop();
                int one = s.pop();
                int res = one + two;
                s.push(res);
            } else if(tokens[i].equals("-")) {
                int two = s.pop();
                int one = s.pop();
                s.push(one - two);
            } else if(tokens[i].equals("*")) {
                int two = s.pop();
                int one = s.pop();
                s.push(one * two);
            } else if(tokens[i].equals("/")) {
                int two = s.pop();
                int one = s.pop();
                s.push(one / two);
            } else {
                int temp = Integer.parseInt(tokens[i]);
                s.push(temp);
            }
        }
        
        int ans = s.peek();
        return ans;
    }
}

