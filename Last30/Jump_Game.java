public class Solution {
    private int last = 0;
    
    public boolean canJump(int[] A) {
        last = 0;
        
        for(int i = 0; i < A.length; i++) {
            if(last >= A.length - 1) return true;
            if(last < i) return false;
            
            int temp = i + A[i];
            last = temp > last ? temp : last;
        }
        
        if(last >= A.length - 1) return true;
        else return false;
    }
}
