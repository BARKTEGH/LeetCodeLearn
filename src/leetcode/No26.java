package leetcode;

import org.junit.Test;

import java.util.Arrays;

public class No26 {

    public int removeDuplicates(int[] nums) {
        if(nums.length==1) return 1;
        int count = 0;
        for(int i=1;i<nums.length;i++){
            if(nums[count]!=nums[i]){
                count++;
                nums[count] = nums[i];
            }

        }
        return count+1;
    }

    @Test
    public void test(){
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
