package leetcode;

public class No650 {

    /**
     * 本题因为只能复制全部而不能复制部分，所有不能像凑数一样操作，最后缺一个'A'在去补。必须把目标拆分成n*m的形式，先凑够n，然后copy一次Paste（m-1）次，即操作m次，总操作数为n+m。要使n+m最小，则要n,m最小。当n,m为质数时，因为无法继续分解，所以最小，即为所求结果。当n或m不为质数，把n，m继续分解成质数，把最后所得的结果全部相加即可。
     *
     * 作者：koukou-11
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/zhi-yin-shu-qiu-he-by-koukou-11/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int minSteps(int n) {
        if(n==1) return 0;
        for(int i=2;i<=n;i++){
            if(n%i==0) return i+ minSteps(n/i);
        }
        return n;
    }


    public int minSteps2(int n) {
       int[] dp = new int[n + 1];

        int h = (int) Math.sqrt(n);
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j <= i; j++) {
                if (i % j == 0) {
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        No650 no650 = new No650();
        System.out.println(no650.minSteps2(16));
    }
}
