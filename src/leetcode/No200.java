package leetcode;

import org.junit.Test;

public class No200 {

    private final int[][] direct = new int[][] {{-1,0},{1,0},{0,1},{0,-1}};
    private int rows,cols;

    public int numIslands(char[][] grid) {
        if(grid==null||grid.length==0) return 0;
        rows = grid.length;
        cols = grid[0].length;
        int Area = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
               if(grid[i][j] == '1'){
                   Area++;
                   dfs(grid,i,j);
                }
            }
        }
        return Area;
    }


    private void dfs(char[][] grid,int row,int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == '0') {
            return;
        }
        grid[row][col] = '0';
        for (int[] d : direct) {
            dfs(grid, d[0] + row, d[1] + col);
        }
    }


}
