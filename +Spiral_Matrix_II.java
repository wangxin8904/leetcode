public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        int up = 0, down = n - 1;
        int left = 0, right = n - 1;
        
        int r = 0, c = 0;
        int cur = 1;
        
        while(true) {
            // to right
            for( ; c <= right; c++) {
                matrix[r][c] = cur++;
            }
            up++; c--;
            if(up > n - 1 || up > down) break;
            r = up;
        
            // to down 
            for( ; r <= down; r++) {
                matrix[r][c] = cur++;
            }
            right--; r--;
            if(right < 0 || right < left) break;
            c = right;
            
            // to left
            for( ; c >= left; c--) {
                matrix[r][c] = cur++;
            }
            down--; c++;
            if(down < 0 || down < up) break;
            r = down;
            
            // to up
            for( ; r >= up; r--) {
                matrix[r][c] = cur++;
            }
            left++; r++;
            if(left > n - 1 || left > right) break;
            c = left;
        }
        return matrix;
    }
}
