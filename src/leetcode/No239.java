package leetcode;

import org.junit.Test;

import java.util.Arrays;

public class No239 {
     public int[] maxSlidingWindow(int[] nums, int k) {
        if(k>=nums.length) return new int[]{maxNum(nums,0,nums.length)};
        int[] list = new int[nums.length-k+1];
        int K_max = maxNum(nums,0,k);
        list[0] = K_max;
        for(int i=1;i<=nums.length-k;i++){
            if(nums[k+i-1]>=K_max){
                list[i] = nums[k+i-1];
                K_max = nums[k+i-1];
            }else if(nums[k+i-1]<K_max && nums[i-1]!=K_max){
                list[i] = K_max;
            }else {
                K_max = maxNum(nums,i,i+k);
                list[i] = K_max;
            }
        }
        return list;
    }

    public int maxNum(int[] nums,int start,int end){
         int max =nums[start];
         for(int i=start;i<end;i++){
             if(nums[i]>max) max = nums[i];
         }
         return max;
    }
    @Test
    public void test(){
         int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
         int[] list =maxSlidingWindow(nums,2);
        System.out.println(Arrays.toString(list));
    }

}
