package leetcode;

public class No485 {
    public int findMaxConsecutiveOnes(int[] nums) {
         int max = 0;
        int nowMax = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1){
                nowMax++;
            }else{
                max = max > nowMax ? max : nowMax;
                nowMax = 0;
            }
        }

        return max > nowMax ? max : nowMax;
    }
}
