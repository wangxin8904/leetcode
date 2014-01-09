public class Solution {
    private boolean flag = false;
    
    private void check(char[][] board, boolean[] digit, int row, int col) {
        // check row
        for(int i = 0; i < board.length; i++) {
            if(board[row][i] != '.') {
                int temp = (board[row][i] - '1');
                digit[temp] = true;
            }
            
            if(board[i][col] != '.') {
                int temp = board[i][col] - '1';
                digit[temp] = true;
            }
        }
        
        // check small board
        int i = row / 3; 
        int j = col / 3;
        for(int m = 3 * i + 0; m < 3 * i + 3; m++ ) {
            for(int n = 3 * j + 0; n < 3 * j + 3; n++) {
                if(board[m][n] != '.') {
                    int temp = board[m][n] - '1';
                    digit[temp] = true;
                }
            }
        }
        
        
    }
    
    
    private void dfs(char[][] board, int index) {
        
        if(index >= 81) {
            flag = true;
            return;
        }
        
        int row = index / 9;
        int col = index % 9;
        
        if(board[row][col] != '.') {
            dfs(board, index + 1);
        } else {
            boolean[] digit = new boolean[9];
            check(board, digit, row, col);
            for(int i = 0; i < digit.length; i++) {
                if(!digit[i] && !flag) {
                    board[row][col] = (char)('1' + i);
                    dfs(board, index + 1);
                    
                    if(!flag)
                        board[row][col] = '.';
                }
            }
        }
    }
    
    public void solveSudoku(char[][] board) {
        flag = false;
        dfs(board, 0);
    }
}
