package leetcode;

public class No85 {

    public int maximalRectangle(char[][] matrix) {

        int m= matrix.length;
        int n=matrix[0].length;
        int[][] dp= new int[m+1][n+1];
        for(int i=0;i<m;i++){
            dp[i][0] = matrix[i][0]=='1'?1:0;
        }

        for(int i=0;i<n;i++){
            dp[0][i] = matrix[0][i]=='1'?1:0;
        }
        int max = 0;
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][j] =='1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[j-1][i]);
                    max = Math.max(dp[i][j],max);
                }
            }
        }
        return max*max;
    }
}
