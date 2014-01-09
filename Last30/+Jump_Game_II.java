
public class Solution {
    // see below how to optimize
    // http://blog.csdn.net/pickless/article/details/9776661
    
    public int jump(int[] A) {
        int[] dp = new int[A.length];
        dp[0] = 0;
        for(int i = 1; i < dp.length; i++) dp[i] = -1;
        
        int curRange = 0;
        for(int i = 0; i < dp.length; i++) {
            for(int j = curRange + 1 ; j <= i + A[i]; j++) {
                
                int temp = dp[i] + 1;
                if( j >= dp.length) continue;
                if(dp[j] == -1 || temp < dp[j]) {
                    dp[j] = temp;
                }
            }
            curRange = i + A[i];
        }
        
        return dp[dp.length - 1];
    }
}
