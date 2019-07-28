package leetcode;



import java.util.TreeSet;

public class No220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t){
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=0;i<nums.length;i++){
            Integer bigger = set.ceiling(nums[i]);
            if(bigger!=null&&bigger<=nums[i]+t) return true;
            Integer smaller = set.floor(nums[i]);
            if(smaller!=null&&smaller+t<=nums[i]) return true;
            set.add(nums[i]);
            if(set.size()>k) set.remove(nums[i-k]);
        }
        return false;
    }
}
