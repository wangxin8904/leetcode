
// check integer overflow , use long type
public class Solution {
    // +/-
    // "00" "01"
    // "11.345" -> "11"
    // overflow
    // test case "  -0012a42"
	
    public int atoi(String str) {
        str = str.trim();
        if(str.length() < 1) return 0;
        
        boolean negative = false;
        if(str.charAt(0) == '+' ) {
            negative = false;
            str = str.substring(1, str.length());
        }
        if(str.charAt(0) == '-') {
            negative = true;
            str = str.substring(1, str.length());           
        }
        
        int ans = 0;

        int i = 0;
        if(i < str.length() && str.charAt(i) == '0') {
            while(i < str.length() && str.charAt(i) == '0') 
                i++;
            str = str.substring(i, str.length());
        }
        
        for(i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            if(c >= '0' && c <= '9') {
                if(negative) {   //negative
                    int temp = (int)(c - '0');
                    
                    // check overflow  
                    long l = (long)ans * 10 - temp;
                    if(l < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    }
                    
                    ans = ans * 10 - temp; 
                } else {  // positive
                    int temp = (int)(c - '0');
                    
                    // check overflow 
                    long l = (long)ans * 10 + temp;
                    if(l > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                    
                    ans = ans * 10 + temp; 
                }
            } else {
            	return ans;
            }
        }
        
        return ans;
    }
}

