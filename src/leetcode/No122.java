package leetcode;

import org.junit.Test;

public class No122 {
    public int maxProfit(int[] prices) {
        if(prices.length==1||prices.length==0) return 0;
        int total = 0;
        int minDix = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]<prices[i-1]){
                total += prices[i-1] - prices[minDix];
                minDix = i;
            }
        }
        if(prices[prices.length-1]-prices[minDix]>0){
            total += prices[prices.length-1]-prices[minDix];
        }
        return total;
    }

    @Test
    public void test(){
        int[] nums = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit(nums));
    }
}
