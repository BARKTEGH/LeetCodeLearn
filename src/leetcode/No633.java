package leetcode;

/**
 * Input: 5
 * Output: True
 * Explanation: 1 * 1 + 2 * 2 = 5
 * 题目描述：判断一个数是否为两个数的平方和。
 */
public class No633 {
    public boolean judgeSquareSum(int c) {
        int max = (int) Math.sqrt(c);
        int i = 0;
        while(i<=max){
            int s = i*i + max*max;
            if(s==c){
                return true;
            }else if(s<c){
                i++;
            }else {
                max--;
            }
        }
        return false;
    }
}
