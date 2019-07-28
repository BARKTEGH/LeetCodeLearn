package leetcode;
/*
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。

示例 1：

输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
示例 2：

输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶

*/

import org.junit.Test;

import java.util.Arrays;

/*
解题思路：容易发现迭代式A(n) = A(n-1) + A(n-2)
但如果直接使用递归来解回发现超时，因为递归的深度太深，递归开销太大
这个时候可以发现每次递归都有重复的递归，例如9会要8和7,8会要7和6，我们把这些数据存储下来
 */
public class No70ClimbingStairs {
    private int[] local;
    public int climbStairs(int n){
        local = new int[n + 1];
        Arrays.fill(local,-1);
        return getClimStairs(n);
    }

    private int getClimStairs(int n){
        if(n==1){
            return 1;
        }
        if (n==2){
            return 2;
        }
        if (local[n] == -1){
            local[n] = getClimStairs(n-1) + getClimStairs(n-2);
        }
        return local[n];
    }

    /**
     * 非递归版本
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if(n==1){
            return 1;
        }
        if (n==2){
            return 2;
        }
        int pre1= 2;
        int pre2 = 1;
        int cur=0;
        for(int k=3;k<=n;k++){
            cur = pre1+pre2;
            pre2 = pre1;
            pre1 =cur;
        }
        return cur;
    }

    @Test
    public void test(){
        for(int i=3;i<15;i++){
            System.out.println(climbStairs(i));
        }
    }

}
