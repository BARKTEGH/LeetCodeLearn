package leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/*
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
求在该柱状图中，能够勾勒出来的矩形的最大面积。
以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
示例:
输入: [2,1,5,6,2,3]
输出: 10
 */
public class No84LargestRectangleInHistogram {


    /*
    解法一：暴力搜索
    找出所有可能的矩形，然后求出面积最大的那个。要找出所有可能的矩形，只需要从左到右扫描每个立柱，
    然后以这个立柱为矩形的左边界（假设为第i个），再向右扫面，分别以（i+1, i+2, n）为右边界确定矩形的形状。
    就这个题目来说，时间复杂度为N^2 。
     */

    /*
    解法二：动态规划
    题目情况分析
    通过不断地对多个直方图的观察，发现面积最大的那个矩形好像都包含至少一个完整的bar，那么这条规律适用于所有的直方图吗？
    我们用反证法来证明，假设某个最大矩形中每个竖直块都是所在的bar的一小段，
    那么这个矩形高度增加1后仍然是一个合法的矩形，但新的矩形面积更大，与假设矛盾，所以面积最大的矩形必须至少有一个竖直块是整个bar。
    至此我们找到了面积最大矩形的一个特性：各组成竖直块中至少有一个是完整的Bar。
    有了这条特性，我们再找面积最大的矩形时，就有了一个比较小的范围。
    具体来说就是针对每个bar，我们找出包含这个bar的面积最大的矩形，然后只需要比较这N个矩形即可（N为bar的个数）。
    局部最大矩形
    简单观察一下，就会发现要找到包含某个bar的最大矩形其实很简答，只需要找到高度小于该bar的左、右边界即可

    用left[i]表示第i个柱子可以最多向左延伸至第left[i]个柱子，形成一个矩形，right[i]则表示向右延伸。
    遍历两次，分别计算出这两个数组。
    再遍历一次，即可求出所有的柱子可以形成的最大的矩形面积。
    为了减少边界的判断，可以使用哨兵，在两端添加两个柱子高度都为-1.
     */
    /**
     * 遍历每个柱子可以组成的最大面积
     *
     * @param heights
     * @return 最大的面积
     */
    public static int getMaxRectangle(int heights[]) {
        int ans = 0;
        int n = heights.length;
        int left[] = new int[n + 1];
        int right[] = new int[n + 1];
        processLR(heights, left, right);
        for (int i = 1; i <= n; i++) {
            int tmp = (right[i] - left[i] + 1) * heights[i - 1];
            if (ans < tmp)
                ans = tmp;
        }
        return ans;
    }

    /**
     * 初始化左右两个数组
     *
     * @param heights
     * @param left
     * @param right
     */
    public static void processLR(int heights[], int left[], int right[]) {
        int n = heights.length;
        // 用临时数组，设置两个哨兵
        int tempArr[] = new int[n + 2];
        tempArr[0] = -1;
        for (int i = 1; i <= n; i++)
            tempArr[i] = heights[i - 1];
        tempArr[tempArr.length - 1] = -1;

        // 用left[i]表示第i个柱子可以最多向左延伸至第left[i]个柱子
        for (int i = 1; i <= n; i++) {
            int k = i;
            while (tempArr[i] <= tempArr[k - 1])
                k = left[k - 1];
            left[i] = k;
        }

        // 用right[i]表示第i个柱子可以最多向右延伸至第right[i]个柱子
        for (int i = n; i > 0; i--) {
            int k = i;
            while (tempArr[i] <= tempArr[k + 1])
                k = right[k + 1];
            right[i] = k;
        }
    }
    /*
    用栈维护一个高度递增的bar的集合，也就是说栈底到栈顶部对应的bar的高度越来越大。
    那么对应一个刚读入的bar，我们只需要比较它的高度和栈顶对应bar的高度，
    如果当前bar比较高，则弹出栈顶元素继续比较，直到栈顶bar比它低或者栈为空。
    之后，将当前bar入栈，更新栈内的递增序列。
    我们从左到右扫一遍得到每个bar对应的左边界，然后从右到左扫一遍得到bar的右边界。
    两次扫描过程中，每个bar都只有出栈、入栈操作，所以时间复杂度为O(N)。
    通过这样的预处理，即可以O(N)的时间复杂度得到每个bar的左右边界。
    之后对于每个bar求出包含它的最大面积，也即是由左右边界和bar的高度围起来的矩形的面积。再做N次比较，即可得出最终的结果。
    这里先预处理用两个栈扫描两次得到左、右边界，再计算面积，是按照推导过程一步一步来的。
    当我们写完程序后，再综合看这个问题，可能会发现其实没必要这样分开来做，我们可以在扫描的同时，维护一个递增的栈，
    同时在“合适的”时候计算面积，然后更新最大面积。
     */
    public int largestRectangleArea(int[] heights) {
    int maxArea = 0;
    Deque<Integer> stack = new ArrayDeque<>();

    int i = 0;
    while (i < heights.length || !stack.isEmpty()) {
      if (stack.isEmpty() || (i < heights.length && heights[i] >= heights[stack.peek()])) {
        stack.push(i++);
      } else {
        int top = stack.pop();

        int width = (stack.isEmpty()) ? i : i - 1 - stack.peek();
        int area = width * heights[top];

        maxArea = Math.max(area, maxArea);
      }
    }

    return maxArea;
  }

    @Test
    public void test(){
        int[] heights = new int[] {2,1,5,6,2,3};
        System.out.println(getMaxRectangle(heights));
        System.out.println(largestRectangleArea(heights));
    }

}
