import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //
        List<Integer> list= new ArrayList<>();
        int temp;
        for(int i=0;i<nums.length;){
            if (nums[i]==i+1 || nums[i]==nums[nums[i]-1]  ) {
                i++;
            }else {
                temp = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[temp-1] = temp ;
                System.out.println(Arrays.toString(nums));
            }

        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+1){
                list.add(i+1);
            }
        }
        return list;
    }

    @Test
    public void test(){
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers(nums).toString());
    }
}
