public class Solution {
    
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        
        int level = 0;
        while(level <= n / 2) {
            
            for(int i = level; i < n - 1 - level; i++) {
                // 
                int temp = matrix[level][i]; // temp = upleft
                matrix[level][i] = matrix[n-1-i][level];
                matrix[n-1-i][level] = matrix[n-1-level][n-1-i];
                matrix[n-1-level][n-1 - i] = matrix[i][n-1-level];
                matrix[i][n-1-level] = temp;
            }
            
            level++;
        }
    }
    
}
