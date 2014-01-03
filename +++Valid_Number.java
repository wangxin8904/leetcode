



// Attempt 1
public class Solution {
	
	// http://blog.csdn.net/doc_sgl/article/details/13505069
    // integer: "01" true, "-0" false, "-1" true;
    // natural num: "+.8" true   "0.00" true, ".1" true, "3." true
    // exponent: "aeb" from wiki a [1,10), b is an integer larger than 0
	//		however case is different here.
	//      base can be decimal and negative, expo must be integer
	//     "6e6.5" false, "2e0" true
	//      "0e1" true, "0 0" false
	//		"2e10" true, "-.3e6" true
	//		"46.e3" true, " 4e3." false
	//		"32.e-80123" true
    
	private boolean isInt(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '.') 
				return false;
		}
		
		return true;
	}
	
    public boolean isNumber(String s) {
        s = s.trim();
        boolean exponent = false;
        boolean decimal = false;
        int e = -1;
        int dot = -1;
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if( !(c >= '0' && c <= '9' || c == 'e' || c == '.' 
            		|| c == '-' || c == '+') ) {
                return false;
            }
            if(exponent && c == 'e') return false;
            if(!exponent && c == 'e') {
                exponent = true;
                e = i;
            }
            
        }
        
        if(exponent) {
            if(e == 0 || e == s.length() - 1) return false;
            String base = s.substring(0,e);
            String expo = s.substring(e + 1, s.length());
            
            if(!isNumber(base) ) return false;
            //if(!checkBase(s.substring(0,e))) return false;
            
            if(!isNumber(expo)) return false;
            if(! isInt(s.substring(e + 1, s.length()))) return false;
            
            return true;
        }
        
        // natural number
        if(s.length() == 0) return false;  // "" false
        
        // -/+ only at the beginning
        boolean negative = false;
        if(s.charAt(0) == '-') {
        	negative = true;
            if(s.length() == 1) return false;
        	s = s.substring(1, s.length());
        } else if(s.charAt(0) == '+') {
        	negative = false;
        	if(s.length() == 1) return false;
        	s = s.substring(1, s.length());
        }
        
        // check only one '.', only one '-/+'
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(decimal && c == '.') return false;
            if(!decimal && c == '.') {
                decimal = true;
                dot = i;
            }
            if(c == '-' || c == '+') return false;
        }
        
        // '.' is false
        if(decimal && s.length() == 1) {
        	return false;
        }
  
        return true;
    }
    

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution so = new Solution();
		
		ArrayList<String> flag = new ArrayList<String>();
		flag.add("0");
		flag.add("01");
		flag.add("10");
		flag.add("2.4");
		flag.add("0.77");
		flag.add("1. 4");
		flag.add("2e10");
		flag.add("-2e-10");
		flag.add(" ");
		flag.add("1e-");
		flag.add(".1");
		flag.add("-.");
		flag.add("-.1e-.1");
		flag.add("01");
		flag.add("3.");
		flag.add("+.8");
		flag.add("4e6.5");
		flag.add("46.e3");
		
		for(int i = 0; i < flag.size(); i++) {
			System.out.println(flag.get(i) + "  |  " + so.isNumber(flag.get(i)));	
		}
	}
}




