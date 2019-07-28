package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例 1:
 *
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 *
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No135 {

     public int candy(int[] ratings) {
         if(ratings==null||ratings.length==0) return 0;
         if(ratings.length==1) return 1;
         PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[0]-b[0] ));
         for(int i=0;i<ratings.length;i++){
             queue.add(new int[]{ratings[i],i});
         }
         int[] cnt = new int[ratings.length];
         int total=0;

         while(queue.size()>0){
             int[] tmp = queue.poll();
             if(tmp[1]==0){
                 if(tmp[0]>ratings[1]){
                     cnt[0] = cnt[1]+1;
                 }else {
                     cnt[0] = 1;
                 }
             }else if(tmp[1]==ratings.length-1){
                 if(tmp[0]>ratings[ratings.length-2]){
                     cnt[tmp[1]] = cnt[tmp[1]-1]+1;
                 }else {
                     cnt[tmp[1]] = 1;
                 }
             }else if(tmp[0]>ratings[tmp[1]+1]||tmp[0]>ratings[tmp[1]-1]){
                 cnt[tmp[1]] = Math.max(cnt[tmp[1]-1],cnt[tmp[1]+1])+1;
             } else {
                 cnt[tmp[1]] = 1;
             }
             total += cnt[tmp[1]];
         }
         System.out.println(Arrays.toString(cnt));

         return total;
    }

    /**
     * 两趟遍历
     * 从前向后保证从左看评分高的分到的糖果多
     * 从后向前保证从右评分高分到的糖果多
     * 取两个最大值，保证都满足求和
     * @param ratings
     * @return
     */
    public int candy2(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }
        int len = ratings.length;
        int[] nums = new int[len];
        //从前往后
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                nums[i] = nums[i - 1] + 1;
            }
        }
        //从后往前
        int sum = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && nums[i] <= nums[i + 1]) {
                nums[i] = nums[i + 1] + 1;
            }
            sum+= nums[i];
        }
        //每人最少发一个糖 所以要+len
        return sum + len;

    }



    public static void main(String[] args) {
        No135 no135 = new No135();
        int[] candy = {5,3,2};
        System.out.println(no135.candy(candy));
    }
}
