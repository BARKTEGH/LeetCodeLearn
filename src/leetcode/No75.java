package leetcode;

import java.util.Arrays;

public class No75 {
    public void sortColors(int[] nums) {
        int zero = 0,cur =0,two = nums.length-1;
        while(cur<=two){
            if(nums[cur]==0){
                swap(nums,zero++,cur++);
            }
            else if(nums[cur]==2){
                swap(nums,two--,cur);
            }else {
                cur++;
            }

        }

        System.out.println(Arrays.toString(nums));
    }

    public void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        No75 no75 = new No75();
        int[] nums = {2,0,2,1,1,0};
        no75.sortColors(nums);
    }
}
