package leetcode;

public class maximumSubarray {

    public int maxSubArray(int[] nums) {
        if(nums.length==1)
            return nums[0];
        int[] sum = new int[nums.length];
        sum[0] = nums[0];

        int leastSum = 0;
        int maxSum = nums[0];

        // 得到从0到i位置求和的数组
        for(int i=1;i<nums.length;i++){
            sum[i]=nums[i]+sum[i-1];
            maxSum = Math.max(maxSum, nums[i]);
        }
        // 从0到最大求和数组之间不断缩减求得最大值
        for(int i=0;i<nums.length;i++){
            if(leastSum>sum[i]){
                leastSum = sum[i];
            } else if(maxSum<sum[i]-leastSum){
                maxSum = sum[i]-leastSum;
            }
        }
        return maxSum;
    }

    public int maxSubArray_7ms(int[] nums) {
        int sum=nums[0];
        int n=nums[0];
        for(int i=1;i<nums.length;i++) {
            if(n>0)
                n+=nums[i];
            else
                n=nums[i];
            if(sum<n)
                sum=n;
        }
        return sum;
    }

}
