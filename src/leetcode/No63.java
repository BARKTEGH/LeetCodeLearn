package leetcode;

public class No63 {


    private int rows;
    private int cols;
    private int count =0;

    /**
     * 回溯算法，判断是否到达右下角
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid==null||obstacleGrid.length==0||obstacleGrid[0][0]==1) return 0;
        rows = obstacleGrid.length;
        cols = obstacleGrid[0].length;
        if(obstacleGrid[rows-1][cols-1]==1) return 0;
        dfs(obstacleGrid,0,0);
        return count;
    }

    public void dfs(int[][] obstacleGrid,int row,int col){
        if(row==(rows-1)&&col==(cols-1))
            count++;
        if(row>=rows||col>=cols||obstacleGrid[row][col]==1){
            return;
        }
        dfs(obstacleGrid,row+1,col);
        dfs(obstacleGrid,row,col+1);
    }

    /**
     * 动态规划,此处采用原数组来存储每个节点的路径树，可以节省空间
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
            if(obstacleGrid==null||obstacleGrid.length==0||obstacleGrid[0][0]==1) return 0;
            rows = obstacleGrid.length;
            cols = obstacleGrid[0].length;

            obstacleGrid[0][0] = 1;

            // Filling the values for the first column
            for (int i = 1; i < rows; i++) {
                obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
            }

            // Filling the values for the first row
            for (int i = 1; i < cols; i++) {
                obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
            }

            for(int i=1;i<rows;i++){
                for (int j=1;j<cols;j++){
                    if(obstacleGrid[i][j]==0){
                        obstacleGrid[i][j] = obstacleGrid[i][j-1]+obstacleGrid[i-1][j];
                    }else {
                        obstacleGrid[i][j] = 0;
                    }
                }
            }
            return obstacleGrid[rows-1][cols-1];
    }


    public static void main(String[] args) {
        No63 no63 = new No63();
        int[][] ints = new int[][] {{1}};
        System.out.println(no63.uniquePathsWithObstacles(ints));
    }
}
