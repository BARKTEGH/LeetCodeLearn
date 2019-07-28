package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class No169 {
    public int majorityElement(int[] nums) {
        if(nums.length==1){return nums[0];}
        Map map = new HashMap();
        for(int num:nums){
            if(map.containsKey(num)){
                map.put(num,((int) map.get(num) + 1));
                if((int) map.get(num) > nums.length/2 ){
                    return num;
                }
            }else {
                map.put(num,1);
            }
        }
        return 0;
    }
    @Test
    public void test(){
        int[] nums = new int[]{2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }
}
