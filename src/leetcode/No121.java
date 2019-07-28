package leetcode;

import org.junit.Test;

public class No121 {

    public int maxProfit(int[] prices) {
        if(prices.length==1||prices.length==0) return 0;
        int minIdx=0;
        int max =0;
        for(int i=1;i<prices.length;i++){
            if((prices[i]-prices[minIdx])>max){
                max = prices[i]-prices[minIdx];
            }
            if(prices[i]<prices[minIdx]){
                minIdx = i;
            }
        }
        return max;

    }
    @Test
    public void test(){
        int[] nums = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit(nums));
    }
}
