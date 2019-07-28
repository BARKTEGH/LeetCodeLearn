package offer;

import java.util.Arrays;

/**
 * 编写一段程序来查找第 n 个超级丑数。
 *
 * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
 *
 * 示例:
 *
 * 输入: n = 12, primes = [2,7,13,19]
 * 输出: 32
 * 解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 * 说明:
 *
 * 1 是任何给定 primes 的超级丑数。
 *  给定 primes 中的数字以升序排列。
 * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
 * 第 n 个超级丑数确保在 32 位有符整数范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-ugly-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No313 {
    public int nthSuperUglyNumber(int n, int[] primes) {

        int[] dp = new int[n];
        Arrays.fill(dp,Integer.MAX_VALUE);
        int[] sizeof = new int[primes.length];
        dp[0] = 1;
        for(int i=1; i<n;i++){
            for(int k=0;k<primes.length;k++){
                int tmp = dp[sizeof[k]]*primes[k];
                if(tmp<dp[i]&&tmp>dp[i-1]){
                    dp[i] = tmp;

                }
            }
            for(int j=0;j<sizeof.length;++j)
            {
                if(dp[i]/dp[sizeof[j]]==primes[j])
                {
                    sizeof[j]++;
                }
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        No313 no313 = new No313();
        int[] primes = {3,5,7,11,19,23,29,41,43,47};
        System.out.println(no313.nthSuperUglyNumber(15,primes));
    }
}
