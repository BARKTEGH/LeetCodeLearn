package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class No90 {

    private List<List<Integer>> subsets;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        subsets = new ArrayList<>();
        if(nums==null||nums.length==0) return subsets;
        List<Integer> subset = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        for(int i=0;i<=nums.length;i++){
            dfs(subset,nums,visited,i,0);
        }
        return subsets;
    }

    public void dfs(List<Integer> subset,int[] nums,boolean[] visited,int size,int start){
        if(subset.size()==size){
            subsets.add(new ArrayList<>(subset));
            return;
        }
        for(int i=start;i<nums.length;i++){
            if(i!=0&&nums[i]==nums[i-1]&&!visited[i-1]){
                continue;
            }if(visited[i]){
                continue;
            }
            visited[i] = true;
            subset.add(nums[i]);
            dfs(subset,nums,visited,size,i+1);
            subset.remove(subset.size()-1);
            visited[i] = false;
        }
    }

    @Test
    public void test(){
        int[] candidates = new int[] {1,2,2,2,2,3};
        subsetsWithDup(candidates);
        for(List<Integer> list:subsets){
            System.out.println(list.toString());
        }
    }

}
