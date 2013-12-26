public class Solution {
    
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        // check null first
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return ans;

        int up = 0, left = 0;
        int right = matrix[0].length - 1, down = matrix.length -1;        
        
        boolean stopFlag = false;
        int r = 0, c = 0;
        while(!stopFlag) {
            // head right
            for( ; c <= right; c++) {
                ans.add(matrix[r][c]);
            }
            up++; c--;
            if(up >= matrix.length || up > down) break;
            r = up;
            
            // head down 
            for( ; r <= down; r++) {
                ans.add(matrix[r][c]);
            }
            right--; r--;
            if(right < 0 || right < left) break;
            c = right;
            
            // head left
            for( ; c >= left; c--) {
                ans.add(matrix[r][c]);
            }
            down--; c++;
            if(down < 0 || down < up) break;
            r = down;
            
            // head up
            for( ; r >= up; r--) {
                ans.add(matrix[r][c]);
            }
            left++; r++;
            if(left >= matrix[0].length || left > right) break;
            c = left;
        }
        
        return ans;
    }
}
