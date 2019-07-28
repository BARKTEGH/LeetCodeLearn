package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class No47 {

    /**
     * 思路：先排序，然后对每个元素访问前判断他前一个元素是否跟他相等，如果相等，只能他前一个元素访问过才能访问。
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> arrayLists = new ArrayList<>();
        if(nums==null||nums.length==0)  return arrayLists;
        Arrays.sort(nums);
        List<Integer> temp = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(arrayLists,temp,visited,nums);
        return arrayLists;
    }


    public void dfs(List<List<Integer>> arrayLists ,List<Integer> temp,boolean[] visited,int[] nums){
        if(temp.size()==nums.length){
            arrayLists.add(new ArrayList<>(temp));
            return ;
        }
        for(int i=0;i<nums.length;i++){
            if(i!=0&&nums[i]==nums[i-1]&&!visited[i-1]){
                continue;
            }
            if(visited[i]){
                continue;
            }
            visited[i] =true;
            temp.add(nums[i]);
            dfs(arrayLists,temp,visited,nums);
            temp.remove(temp.size()-1);
            visited[i] = false;
        }


    }


}
