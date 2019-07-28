package leetcode;

import javafx.util.Pair;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class No64 {
    /**
     * 空间复杂度为o(m*n)
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {

        if(grid==null||grid.length==0) return 0;
        int m = grid.length,n=grid[0].length;
        int[][] pahtgrid = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0) pahtgrid[i][j] = grid[i][j];
                else if(i==0){
                    pahtgrid[i][j] = pahtgrid[i][j-1]+grid[i][j];
                }else if(j==0){
                    pahtgrid[i][j] = pahtgrid[i-1][j]+grid[i][j];
                }else {
                    pahtgrid[i][j] = Math.min(pahtgrid[i][j-1],pahtgrid[i-1][j])+grid[i][j];
                }
            }
        }
        return pahtgrid[m-1][n-1];
    }

    /**
     * 空间复杂度为o(m),通过复用行实现
     * @param grid
     * @return
     */

    public int minPathSum1(int[][] grid){
        if(grid==null||grid.length==0) return 0;
        int m = grid.length,n=grid[0].length;
        int[] path = new int[n];
        path[0] = grid[0][0];
        for(int j=1;j<n;j++){
            path[j] = path[j-1]+grid[0][j];
        }
        for(int i=1;i<m;i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    path[j] = path[j] + grid[i][j];
                } else {
                    path[j] = Math.min(path[j - 1], path[j]) + grid[i][j];
                }
            }
        }
         return path[n-1];

    }

    @Test
    public void test(){
        int[][] ints = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum1(ints));
    }
}
