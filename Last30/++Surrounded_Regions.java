// Attempt 2
// fisherlei.blogspot.com/2013/.../leetcode-surrounded-regions-solution.html
public class Solution {
 
	public void solve(char[][] board) {
		if(board == null || board.length < 1 || board[0].length < 1) return;
		boolean[][] flags = new boolean[board.length][board[0].length];
		
        ArrayList<Integer> X = new ArrayList<Integer>();
        ArrayList<Integer> Y = new ArrayList<Integer>();
        
        // find all 'O' connects to border
        int j = 0, i = 0;
        for(j = 0; j < board[0].length; j++) {
            if(board[0][j] == 'O') {
                X.add(0);
                Y.add(j);
            }
            if(board[board.length-1][j] == 'O') {
                X.add(board.length-1);
                Y.add(j);
            }
        }
        for(j = 0; j < board.length; j++) {
            if(board[j][0] == 'O') {
                X.add(j);
                Y.add(0);
            }
            if(board[j][board[0].length-1] == 'O') {
                X.add(j);
                Y.add(board[0].length-1);
            }
        }
        
        int k = 0;
        while(k < X.size()) {
            int r = X.get(k);
            int c = Y.get(k);
            board[r][c] = 'D';
            
            if(r > 0 && board[r-1][c] == 'O') { X.add(r-1); Y.add(c); }
            if(c > 0 && board[r][c-1] == 'O') { X.add(r); Y.add(c-1); }
            if(r < board.length - 1 && board[r+1][c] == 'O') { X.add(r + 1); Y.add(c); }
            if(c < board[0].length - 1 && board[r][c+1] == 'O') {X.add(r); Y.add(c+1); }
            
            k++;
        }
        
        // reverse
        for(i = 0; i < board.length; i++) {
            for(j = 0; j < board[0].length; j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
                else if(board[i][j] == 'D') board[i][j] = 'O';
            }
        }
	}
}



// Attempt 1, BFS TLE
/*

public class Solution {
 
	class Unit {
		int row; int col;
		Unit(int i, int j) {
			row = i;
			col = j;
		}
	}

	private void bfs(int r, int c, boolean[][] flags, char[][] board, char c) {
		boolean border = false;
		
		LinkedList<Unit> queue = new LinkedList<Unit>();
		ArrayList<Unit> pos = new ArrayList<Unit>();
		queue.offer(new Unit(r, c));
		
		while(!queue.isEmpty()) {
			Unit cur = queue.poll();
			pos.add(cur);
			flags[cur.row][cur.col] = true;
			
			// four 
			int i = cur.row; int j = cur.col;
 			if(i == 0) border = true;
// 			else {
// 				if(board[i - 1][j] == 'O' && !flags[i-1][j]) queue.add(new Unit(i-1,j));
// 			}
			if(i == flags.length - 1) border = true;
			else {
				if(board[i + 1][j] == 'O'  && !flags[i+1][j]) queue.add(new Unit(i+1,j));
			}
			if(j == 0) border = true;
// 			else {
// 				if(board[i][j-1] == 'O' && !flags[i][j-1]) queue.add(new Unit(i,j-1));
// 			}
			if(j == flags[0].length - 1) border = true;
			else {
				if(board[i][j+1] == 'O' && !flags[i][j+1]) queue.add(new Unit(i,j+1));
			}	
		}
		
		//if(border) return;
		for(int i = 0; i < pos.size(); i++) {
			Unit temp = pos.get(i);
			board[temp.row][temp.col] = c;
		}
	}

	public void solve(char[][] board) {
		if(board == null || board.length < 1 || board[0].length < 1) return;
		boolean[][] flags = new boolean[board.length][board[0].length];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'O' && !flags[i][j]) {
					bfs(i, j, flags, board);
				}
			}
		}
	}
}



*/   
