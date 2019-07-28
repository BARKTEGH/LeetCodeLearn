package leetcode;

public class No303 {

    private int[] sums;
    public void NumArray(int[] nums) {
        sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return  sums[j + 1] - sums[i];
    }

}
