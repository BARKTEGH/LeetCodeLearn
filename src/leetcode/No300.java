package leetcode;

import java.util.Arrays;

public class No300 {

    /**
     * 动态规划
     * dp[i]为已第i位为结尾的最长子序列长度
     * 时间O(n^2)
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums){
        if(nums==null||nums.length==0) return 0;
        int max = 1;
        //dp[i]为已第i位为结尾的最长子序列长度
        int[] dp =new int[nums.length];
        Arrays.fill(nums,1);
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    max = Math.max(max,dp[i]);
                }
            }
        }
        return max;
    }

    /**
     * 贪心+二分
     * dp[i]为长度为i+1的最长上升子序列结尾元素的最小值
     * 就是维护一个每个位置都最小的最长子序列dp
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums){
        if(nums==null||nums.length==0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int pos = 0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>dp[pos]){
                dp[++pos] = nums[i];
            }else {
                int index = partition(dp,0,pos,nums[i]);
                dp[index] = nums[i];
            }
        }
        return pos+1;
    }

    public int partition(int[] nums,int l,int r,int t){
        while(l<r){
            int mid = l+(r-l)/2;
            if(nums[mid]==t) {
                return t;
            } else if(nums[mid]>=t) {
                r= mid;
            }else {
                l = mid+1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        No300 no300 = new No300();
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(no300.lengthOfLIS2(nums));
    }



}
