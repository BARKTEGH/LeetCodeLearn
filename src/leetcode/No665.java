package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class No665 {
     public boolean checkPossibility(int[] nums) {
        int count = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i;j<nums.length;j++){
                if(nums[j]<nums[i]){
                    count++;
                    break;
                }
            }
            if(count>=2)
                return false;

        }
        return true;
    }

    @Test
    public void test(){
         int[] nums = new int[]{2,3,3,2,4};
        System.out.println(checkPossibility(nums));
         int[] num = new int[]{1,2,3,1};
        System.out.println(test1(num));
    }

    public boolean test1(int[] nums){
        Set map = new HashSet();
        for(int num:nums){
            if(map.contains(num))
                return true;
            else {
                map.add(num);
            }
        }
        return false;
    }

}
