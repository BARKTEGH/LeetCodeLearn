package leetcode;

import org.junit.Test;

import java.util.Arrays;

public class No34 {
    public int[] searchRange(int[] nums, int target) {
        int first = binarySearch(nums, target);
        int last = binarySearch(nums, target + 1) ;
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{first, Math.max(first, last==nums.length-1?last:last-1)};
        }
    }

    private int binarySearch(int[] nums, int target) {
        int l = 0, h = nums.length-1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= target) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    @Test
    public void test(){
        int[] a = new int[]{5,7,7,8,8,8,8,8};
        System.out.println(Arrays.toString(searchRange(a,9)));
    }
}
