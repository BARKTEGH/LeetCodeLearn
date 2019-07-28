package leetcode;

public class No55CanJump {

    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for(int i=0;i<nums.length-1;i++){
            if(dp[i]==false) continue;
            for(int j=i;j<=nums[i]+i&& j<nums.length;j++){
                dp[j] = dp[i];
            }
        }

        return dp[nums.length-1];
    }

    public boolean canJump2(int[] nums) {
        if(nums.length==1&&nums[0]==0) return true;
        int start = 0;
        int len = nums.length;
        while (start<len){
            int maxStep = start;
            int idx = start;
            if(start+nums[start]>=len-1) return true;
            for(int i=start;i<=nums[start]+start && i<len;i++){
                if(nums[i]+i>maxStep){
                    maxStep = nums[i]+i;
                    idx = i;
                }
            }
            if(idx==start)break;
            start = idx;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] num = new int[]{3,2,1,0,4};
        No55CanJump jump = new No55CanJump();
        System.out.println(jump.canJump(num));
    }
}
