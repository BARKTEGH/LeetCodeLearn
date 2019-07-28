package leetcode;

import java.util.ArrayList;
import java.util.List;

public class No417 {

    private int m, n;
    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> ret = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return ret;
        }
        m = matrix.length;
        n = matrix[0].length;
        boolean[][] canReachP = new boolean[m][n];
        boolean[][] canReachA = new boolean[m][n];

        for(int i=0;i<m;i++){
                dfs(matrix,i,0,canReachP);
                dfs(matrix,i,n-1,canReachA);
        }
        for(int j=0;j<n;j++){
            dfs(matrix,0,j,canReachP);
            dfs(matrix,m-1,j,canReachA);
        }
        for(int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if(canReachA[i][j]&&canReachP[i][j])
                    ret.add(new int[]{i,j});
            }
        }
        return ret;

    }

     private void dfs(int[][] grid, int i, int j,boolean[][] canReach) {
        if(canReach[i][j]){
            return;
        }
        canReach[i][j] = true;
        for(int[] d:direction){
            int nrow = d[0]+i;
            int ncol = d[1]+j;
            if(nrow<0||nrow>=m||ncol<0||ncol>=n||
            grid[nrow][ncol]<grid[i][j]){
                continue;
            }
            dfs(grid,nrow,ncol,canReach);
        }
    }
}
