package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class No312 {
    /**
     * 动态规划
     * dp[i][j] 表示戳破i+1到j-1号气球的最大收益
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {

        //填充数组两端 1 nums  1
        ArrayList<Integer> list = new ArrayList<>(nums.length + 2);
        list.add(1);
        for(int i=0;i<nums.length;i++){
            list.add(nums[i]);
        }
        list.add(1);

        int len = list.size();

        int[][] dp = new int[len][len];

        for(int range = 2;range<len;range++){
            for(int i=0;i<len-range;i++){
                int j= i+range;
                for(int k=i+1;k<j;k++){
                    dp[i][j] = Math.max(dp[i][j],list.get(i)*list.get(k)*list.get(j)+dp[i][k]+dp[k][j]);
                }
            }
        }
        return dp[0][len-1];
    }

    public static void main(String[] args) {
        No312 no312 = new No312();
        int[] nums = new int[]{3,1,5,8};
        System.out.println(no312.maxCoins(nums));
    }
}
