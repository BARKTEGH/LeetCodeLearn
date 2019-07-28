package leetcode;

public class No35 {

    //二分查找
    public  int searchInsert(int[] nums, int target) {

        if(nums==null||nums.length==0) return 0;
        if(target>nums[nums.length-1]) return nums.length;
        if(target<nums[0]) return 0;
        int h = nums.length-1;
        int l =0;
        while (l<h){
            int mid = l +(h-l)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                l=mid+1;
            }else {
                h=mid;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        No35 no35 = new No35();
        int[] nums = new int[]{1,3,5,6};
        System.out.println(no35.searchInsert(nums,4));

    }


}
