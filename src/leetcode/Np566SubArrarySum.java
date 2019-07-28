package leetcode;

import org.junit.Test;
import org.junit.experimental.categories.Categories;

/*
给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :

输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
说明 :

数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
public class Np566SubArrarySum {



    public int subarraySum(int[] nums, int k) {
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            sums[i] = sums[i-1]+nums[i];
        }
        int counter = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if ((sums[j] - sums[i])== k) counter++;
            }
            if(sums[i] == k) counter++;
        }
        return counter;
    }
    @Test
    public void test1(){
        int[] nums = new int[] {1,1,2,3,54,5424,1,2,3,2,3,4,1,1};
        int k =5;
        System.out.println(subarraySum(nums,k));
    }


}
