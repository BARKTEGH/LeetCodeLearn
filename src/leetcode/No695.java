package leetcode;

import javafx.util.Pair;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Stack;

public class No695 {

    private final int[][] direct = new int[][] {{-1,0},{1,0},{0,1},{0,-1}};
    private int rows,cols;
    public int maxAreaOfIsland(int[][] grid) {
        if(grid==null||grid.length==0) return 0;
        rows = grid.length;
        cols = grid[0].length;
        int maxArea = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                maxArea = Math.max(maxArea,dfs(grid,i,j));
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid,int row,int col){
        if(row<0||row>=rows||col<0||col>=cols||grid[row][col]==0){
            return 0;
        }
        grid[row][col] = 0;
        int area = 1;
        for(int[] d:direct){
            area += dfs(grid,d[0]+row,d[1]+col);
        }
        return area;
    }

    @Test
    public void test(){
        int[][] ints = new int[][]{{0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                    {0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                            {0,0,0,0,0,0,0,1,1,0,0,0,0}};

        System.out.println(maxAreaOfIsland(ints));
    }

}
