package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No51 {

    private boolean[] colUesd;
    // 45 度对角线标记数组的长度为 2 * n - 1，(r, c) 的位置所在的数组下标为 r + c
    private boolean[] diagonals45Used;
    //  135度对角线标记数组的长度是 2 * n - 1，(r, c) 的位置所在的数组下标为 n - 1 - (r - c)
    private boolean[] diagonals135Used;
    private char[][] nQueens;
    List<List<String>> solves = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if(n<=0) {
            return solves;
        }
        colUesd = new boolean[n];
        diagonals45Used = new boolean[2*n-1];
        diagonals135Used = new boolean[2*n-1];
        nQueens = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(nQueens[i], '.');
        }
        backtracking(0,n);
        return  solves;
    }


    public void backtracking(int row,int n){
        if(row==n){
            List<String> list = new ArrayList<>();
            for (char[] chars:nQueens){
                list.add(new String(chars));
            }
            solves.add(list);
            return;
        }
        for(int col=0;col<n;col++){
            int diag45Index = row+col;
            int diag135Index = n-1-(row-col);
            if(colUesd[col]||diagonals45Used[diag45Index]||diagonals135Used[diag135Index]){
                continue;
            }
            colUesd[col] = diagonals45Used[diag45Index] = diagonals135Used[diag135Index] =true;
            nQueens[row][col] = 'Q';
            backtracking(row+1,n);
            nQueens[row][col] = '.';
            colUesd[col] = diagonals45Used[diag45Index] = diagonals135Used[diag135Index] =false;
        }
    }

    @Test
    public void test(){
        solveNQueens(8);
        for(List<String> solve:solves){
            System.out.println(solve.toString());
        }
    }


}
