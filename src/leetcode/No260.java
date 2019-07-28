package leetcode;

import org.junit.Test;

import java.util.Arrays;

public class No260 {
     public int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int num : nums)
            diff ^= num;
        diff &= -diff;
        // 得到最右一位
        int[] ret = new int[2];
        for (int num : nums) {
            // 保留没有diff这位的数
            if ((num & diff) == 0)
                ret[0] ^= num;
            // 保留带有diff的数
            else ret[1] ^= num;
        }
        return ret;
    }

    @Test
    public void test(){
        int[] nums = new int[]{1,2,1,3,2,5};
        System.out.println(Arrays.toString(singleNumber(nums)));
    }
}
