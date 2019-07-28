package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No40 {
    private List<List<Integer>> lists;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        lists = new ArrayList<>();
        if(candidates==null||candidates.length==0) return lists;
        Arrays.sort(candidates);
        List<Integer> temp = new ArrayList<>();
        boolean[] visited = new boolean[candidates.length];
        dfs(temp,candidates,visited,target,0,0);
        return lists;
    }

    public void dfs(List<Integer> temp,int[] candidates,boolean[] visited,int target,int sum,int start){
        if(sum>target){
            return;
        }
        if(sum==target){
            lists.add(new ArrayList<>(temp));
            return;
        }

        for(int i=start;i<candidates.length;i++){
            if(i!=0&&candidates[i]==candidates[i-1]&&!visited[i-1]){
                continue;
            }
            if(visited[i]){
                continue;
            }
            visited[i] = true;
            temp.add(candidates[i]);
            dfs(temp,candidates,visited,target,sum+candidates[i],i+1);
            visited[i] = false;
            temp.remove(temp.size()-1);
        }
    }

    @Test
    public void test(){
        int[] candidates = new int[] {10,1,2,7,6,1,5};
        combinationSum2(candidates,8);
        for(List<Integer> list:lists){
            System.out.println(list.toString());
        }
    }

}

