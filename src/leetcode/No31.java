package leetcode;

import java.util.Arrays;

public class No31 {

    public void nextPermutation(int[] nums) {
        if(nums==null||nums.length==0|| nums.length==1) return;
        int r = nums.length-2;
        while (r>=0&&nums[r+1]<=nums[r]){
            r--;
        }
        if(r>=0){
            int i = nums.length-1;
            while (i>=0&&nums[i]<=nums[r]){
                i--;
            }
            swap(nums,r,i);
        }

        reverse(nums,r+1);

    }

    public void reverse(int[] nums,int start){
        int i= start;
        int j= nums.length-1;
        while (i<j){
            swap(nums,i++,j--);
        }
    }


    public void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        No31 no31 = new No31();
        int[] n = {1,2,3};
        no31.nextPermutation(n);
        System.out.println(Arrays.toString(n));
        System.out.println(3&-3);

    }
}
