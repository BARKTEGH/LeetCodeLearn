package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No119 {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(rowIndex==1) {
            list.add(1);
            list.add(1);
            return list;
        }
        if(rowIndex==0) {
            list.add(1);
            return list;
        }

        int[] nums = new int[rowIndex+1];
        Arrays.fill(nums,1);
        for(int layyer=2;layyer<=rowIndex;layyer++){
            for(int index=layyer-1;index>0;index--){
                nums[index] = nums[index-1]+nums[index];
            }
        }
        for(int num:nums){
            list.add(num);
        }
        return  list;


    }
}
