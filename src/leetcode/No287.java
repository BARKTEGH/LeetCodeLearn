package leetcode;

import org.junit.Test;

/**
 * 使用数组中的值作为索引下标进行遍历，遍历的结果肯定是一个环（有一个重复元素） 检测重复元素问题转换成检测环的入口 为了找到环的入口，可以进行如下步骤：
 *
 * 设置两个快慢指针， fast每次走两步，slow每次走一步，最终走了slow走了n步与fast相遇，fast走了2*n，fast可能比slow多饶了环的i圈，得到环的周长为n/i
 * slow指针继续走, 且另设第三个指针每次走一步，两个指针必定在入口处相遇
 * 假设环的入口和起点的距离时m
 * 当第三个指针走了m步到环的入口时
 * slow刚好走了n + m步，换句话说时饶了环i圈（环的周长为n/i）加m步（起点到入口的距离）
 * 得到相遇的是环的入口，入口元素即为重复元素
 */
public class No287 {

    public int findDuplicate(int[] nums) {
         int l = 1, h = nums.length - 1;
         while (l <= h) {
             int mid = l + (h - l) / 2;
             int cnt = 0;
             for (int i = 0; i < nums.length; i++) {
                 if (nums[i] <= mid) cnt++;
             }
             if (cnt > mid) h = mid - 1;
             else l = mid + 1;
         }
         return l;
    }

    @Test
    public void test(){
        int[] cc = new int[]{3,5,6,7,4,1,2,3};
        System.out.println(findDuplicate(cc));
    }


}
