package leetcode;

public class No130 {

    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int m, n;
    /*
    从四周出发，将链接边的点变为T，然后反转o为X，最后将*变为o
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
        return;
        }

        m = board.length;
        n = board[0].length;
        //将于边相连O点变为T；
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            dfs(board, 0, i);
            dfs(board, m - 1, i);
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else  if(board[i][j] == 'T'){
                    board[i][j] = 'O';
                }
            }
        }




    }


    public void dfs(char[][] board,int r,int c){
        if(r<0||r>=m || c<0||c>=n || board[r][c]!='O'){
            return;
        }
        board[r][c] = 'T';
        for(int[] d:direction){
            dfs(board,r+d[0],c+d[1]);
        }
    }
}
