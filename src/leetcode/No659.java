package leetcode;

import java.util.HashMap;
import java.util.Map;

public class No659 {
    public boolean isPossible(int[] nums) {
        Map<Integer,Integer> map1 = new HashMap<>(nums.length);
        Map<Integer,Integer> map2 = new HashMap<>(nums.length);
        for( int num:nums){
            map1.put(num,map1.getOrDefault(num,0)+1);
        }
        for(int num:nums){
           if(map1.get(num)==0) {continue;}
           else if(map2.containsKey(num-1) && map2.get(num-1)>0){
               map2.put(num,map2.getOrDefault(num,0)+1);
               map2.put(num-1,map2.get(num-1)-1);
           }else if(map1.containsKey(num+1)&&map1.containsKey(num+2)&&map1.get(num+1)>0&&map1.get(num+2)>0){
                map1.put(num+1,map1.get(num+1)-1);
                map1.put(num+2,map1.get(num+2)-1);
                map2.put(num+2,map2.getOrDefault(num+2,0)+1);
           }else {
               return false;
           }
           map1.put(num,map1.get(num)-1);
        }

        return true;

    }
}
