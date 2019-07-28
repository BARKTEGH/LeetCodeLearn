package leetcode;

public class No79 {

    private int m, n;
    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        m = board.length;
        n = board[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (backtracking( board, word,0,r,c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean backtracking(char[][] board,String word,int index,int row,int col){
        if(index==word.length()){
            return true;
        }
        if(row<0||row>=m||col<0||col>=n||board[row][col]!=word.charAt(index)||board[row][col]=='0'){
                return false;
        }
        char temp =  board[row][col];
        board[row][col] = '0';
        for(int[] d:direction){
            if(backtracking(board,word,index+1,d[0]+row,d[1]+col)){
                return true;
            }
        }
        board[row][col] = temp;
        return false;
    }
}
