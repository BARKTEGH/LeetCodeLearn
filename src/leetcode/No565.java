package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class No565 {
    public int arrayNesting(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int longM = 0;
        for(int i= 0;i<nums.length;i++){
            if(!set.contains(i)){
                int k = i;
                int count =0;
                while(!set.contains(k)){
                    count++;
                    set.add(k);
                    k = nums[k];
                }
                longM = Math.max(count,longM);
            }
        }
        return longM;
    }

    @Test
    public void test(){
        int[] nms= new int[]{5,4,0,3,1,6,2};
        System.out.println(arrayNesting(nms));
    }
}
