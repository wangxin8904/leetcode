
public class Solution {
    
    // www.cnblogs.com/TenosDoIt/p/3389479.html?
    public int candy(int[] ratings) {
        int[] dp = new int[ratings.length];
        for(int i = 0; i < dp.length; i++)
            dp[i] = 1;
            
        // left to right
        for(int i = 0; i < dp.length - 1; i++) {
            if(ratings[i + 1] > ratings[i] && dp[i+1] <= dp[i]) {
                dp[i + 1] = dp[i] + 1;
            }
        }
        
        // right to left
        for(int i = dp.length - 1; i > 0; i--) {
            if(ratings[i - 1] > ratings[i] && dp[i-1] <= dp[i]) {
                dp[i - 1] = dp[i] + 1;
            }
        }
        
        int total = 0;
        for(int i = 0; i < dp.length; i++) {
            total += dp[i];
        }
        return total;
    }
}

