public class Solution {

	private long helper(int dividend, int divisor) {        
		long a = dividend;
		long b = divisor;
        
        boolean minus = false;
        if(a < 0) {
            minus = (!minus);
            a = -a;
        }
        if(b < 0) {
            minus = (!minus);
            b = -b;
        }
		
		long ans = 0;
		while(true) {
			long cur = b;
			long res = 1;
			if(a < cur) break;
			
			while(cur << 1 < a) {
				cur = cur << 1;
				res = res << 1;
			}
			
			a -= cur;
			ans += res;
		}

		
        if(!minus)
            return ans;
        else 
            return -ans;
	}
	
    public int divide(int dividend, int divisor) {        
        return (int)helper(dividend, divisor);
    }


}
