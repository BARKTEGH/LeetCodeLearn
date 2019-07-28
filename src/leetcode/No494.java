package leetcode;


import org.junit.Test;

/**
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例 1:
 *
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 * 注意:
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No494 {

    //dfs
    private int res = 0;
    public int findTargetSumWays(int[] nums, int S) {
            dfs(nums,0,S*(-1));
            return res;
    }

    public void dfs(int[] nums, int i,int S){
        if(S==0&&i==nums.length){
            res++;
            return;
        }
        if(i==nums.length){
            return;
        }
        dfs(nums,i+1,S+nums[i]);
        dfs(nums,i+1,S-nums[i]);
    }


    /**
     * 原问题等同于： 找到nums一个正子集和一个负子集，使得总和等于target
     *
     * 我们假设P是正子集，N是负子集 例如： 假设nums = [1, 2, 3, 4, 5]，target = 3，一个可能的解决方案是+1-2+3-4+5 = 3 这里正子集P = [1, 3, 5]和负子集N = [2, 4]
     *
     * 那么让我们看看如何将其转换为子集求和问题：
     *
     *                   sum(P) - sum(N) = target
     * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
     *                        2 * sum(P) = target + sum(nums)
     * 因此，原来的问题已转化为一个求子集的和问题： 找到nums的一个子集 P，使得sum(P) = (target + sum(nums)) / 2
     */


    public int subsetSum(int[] sum,int S){
        int target = S;
        for(int i=0;i<sum.length;i++){
            target+=sum[i];
        }
        if(target%2>0) return 0;
        target = target/2;

        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int n:sum){
            for(int i=target;i>=n;i--){
                dp[i] += dp[i-n];
            }
        }
        return dp[target];
    }

    @Test
    public void test(){
        int[] nums = new int[]{1,1,1,1,1};
        System.out.println(subsetSum(nums,3));
  }

}
