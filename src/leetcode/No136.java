package leetcode;/*
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例 2:

输入: [4,1,2,1,2]
输出: 4
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;


public class No136 {
    public int singleNumber(int[] nums) {
        if(nums.length==1) return nums[0];
        HashSet<Integer> set = new HashSet<Integer>();
        for (int num :nums){
            if(!set.contains(num)){
                set.add(num);
            }else {
                set.remove(num);
            }
        }
        return (int) set.iterator().next();
    }

    //异或
    public int method2(int[] nums){
        int a =0;
        for(int num:nums){
            a = a^num;
        }
        return a;
    }

    @Test
    public void test(){
        int[] nums = new int[]{4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }
}
