package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class No78 {


    private List<List<Integer>> lists;
    public List<List<Integer>> subsets(int[] nums) {
        lists = new ArrayList<>();
        if(nums==null||nums.length==0) return lists;
        List<Integer> temp = new ArrayList<>();
        for(int i=0;i<=nums.length;i++){
             dfs(temp,nums,i,0);
        }
        return lists;
    }

    public void dfs(List<Integer> temp,int[] nums,final int size,int start){
        if(temp.size()==size){
            lists.add(new ArrayList<>(temp));
            return;
        }
        for(int i=start;i<nums.length;i++){
            temp.add(nums[i]);
            dfs(temp,nums,size,i+1);
            temp.remove(temp.size()-1);
        }
    }

     @Test
    public void test(){
        int[] candidates = new int[] {1,2,3};
        subsets(candidates);
        for(List<Integer> list:lists){
            System.out.println(list.toString());
        }
    }
}
