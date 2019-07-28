package leetcode;

import org.junit.Test;

import java.util.Arrays;

public class No37 {

    private boolean[][] rowsUsed = new boolean[9][10];
    private boolean[][] colsUsed = new boolean[9][10];
    private boolean[][] cubesUsed = new boolean[9][10];
    private char[][] board;

     public void solveSudoku(char[][] board) {
         this.board = board;
        for(int i=0; i < 9;i++){
            for(int j=0; j < 9;j++){
                if(board[i][j]=='.'){
                    continue;
                }
                int num = board[i][j]-'0';
                rowsUsed[i][num] = true;
                colsUsed[j][num] = true;
                cubesUsed[cubeNum(i,j)][num] = true;
            }
        }
        backtracking(0,0);
    }

    /**
     * 计算格子属于第几个方格区域
     * @param i
     * @param j
     * @return
     */
    private int cubeNum(int i, int j) {
        int r = i / 3;
        int c = j / 3;
        return r * 3 + c;
    }

    public boolean backtracking(int row,int col){
        //找到需要填写的格子
        while(row<9&&board[row][col]!='.'){
            row = col==8?row+1:row;
            col = col==8?0:col+1;
        }
        if(row==9){
            return true;
        }
        for(int num=1;num<10;num++){
            //检测数字冲突
            if(rowsUsed[row][num]||colsUsed[col][num]||cubesUsed[cubeNum(row,col)][num]){
                continue;
            }
            rowsUsed[row][num] = colsUsed[col][num] =cubesUsed[cubeNum(row,col)][num] = true;
            board[row][col] = (char) (num + '0');
            if(backtracking(row,col)){
                return true;
            }

            board[row][col] = '.';
            rowsUsed[row][num] = colsUsed[col][num] =cubesUsed[cubeNum(row,col)][num] = false;
        }
        return false;
    }


    @Test
    public void test(){
        char[][] bs = new char[][]{{'5','3','.','.','7','.','.','.','.'},
                                    {'6','.','.','1','9','5','.','.','.'},
                                    {'.','9','8','.','.','.','.','6','.'},
                                    {'8','.','.','.','6','.','.','.','3'},
                                    {'4','.','.','8','.','3','.','.','1'},
                                     {'7','.','.','.','2','.','.','.','6'},
                                    {'.','6','.','.','.','.','2','8','.'},
                                    {'.','.','.','4','1','9','.','.','5'},
                                        {'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(bs);
        for (char[] c:bs){
            System.out.println(Arrays.toString(c));
        }
    }
}
