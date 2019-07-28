package other;

import java.util.Arrays;

public class Package {
    public void  package01(int[] weight,int[] value, int W,int n){
        int[][] dp = new int[n+1][W+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=W;j++){
                if(weight[i-1]>j){
                    //当前物品i的重量比背包容量j大，装不下，肯定就是不装
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weight[i-1]]+value[i-1]);
                }
            }
        }
        //如果是找出最大的价值到此结束
        //return dp[n][W];

        //找出是第几个物品
        System.out.println(dp[n][W]);
        int max = dp[n][W];
        int j = W;
        boolean[] used = new boolean[n];
        for(int i=n;i>0;i--){
            //说明第i件一定
            if(dp[i][j]>dp[i-1][j]){
                j = j-weight[i-1];
                used[i-1] = true;
            }
        }
        System.out.println(Arrays.toString(used));

    }
    //0-1背包优化，使用一维数组来存储,使用逆序实现
    public int  package01Perfect(int[] weight,int[] value, int W,int n){
        int[] dp = new int[W+1];
        for(int i=1;i<=n;i++){
            for(int j=W;j>=weight[i-1];j--){
                dp[j] = Math.max(dp[j],dp[j-weight[i-1]]+value[i-1]);
            }
        }
        return dp[W];
    }

    //多维背包
    public int manyPackage(int[] weight,int[] value, int[] In,int W,int I){
        int[][] dp = new int[I+1][W+1];
        for(int n=1;n<=weight.length;n++){
            for(int i=I;i>=In[n-1];i--){
                for(int j=W;j>=weight[n-1];j--){
                    dp[i][j] = Math.max(dp[i][j],dp[i-In[n-1]][j-weight[n-1]]+value[n-1]);
                }
            }
        }
        return dp[I][W];

    }

    //完全背包，可重复取
    public int completePackage(int[] weight,int[] value,int W){
        int N = weight.length;
        int[] dp = new int[W+1];
        for(int i=1;i<=N;i++){
            for(int j=weight[i-1];j<=W;j++){
                dp[j] = Math.max(dp[j-weight[i-1]]+value[i-1],dp[j]);
            }
        }
        return dp[W];
    }




    public static void main(String[] args) {
        Package aPackage = new Package();
        int[] weight = new int[]{ 6, 3, 5, 4, 6};
        int[] value =  new int[]{2, 2, 6, 5, 4};
        int n=5,W=10;
        aPackage.package01(weight,value,W,n);
        System.out.println(aPackage.package01Perfect(weight,value,W,n));
        System.out.println(aPackage.manyPackage(weight,value,weight,W,W));
        System.out.println("完全背包"+aPackage.completePackage(weight,value,W));
    }
}
