package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No39 {
    private List<List<Integer>> arrayLists;
     public List<List<Integer>> combinationSum(int[] candidates, int target) {
            arrayLists = new ArrayList<>();
            if(candidates==null||candidates.length==0) return arrayLists;
            Arrays.sort(candidates);
            List<Integer> temp = new ArrayList<>();
            int start=0,sum=0;
            dfs(temp,candidates,target,start,sum);
            return arrayLists;
    }


    public void dfs(List<Integer> temp,int[] candidates,int target,int start,int sum){
         if(sum>target){
             return;
         }
        if(sum==target){
            arrayLists.add(new ArrayList<>(temp));
            return ;
        }
        for(int i=start;i<candidates.length;i++){
            temp.add(candidates[i]);
            dfs(temp,candidates,target,i,sum+candidates[i]);
            temp.remove(temp.size()-1);
        }
    }

    @Test
    public void test(){
         int[] c =new int[] {2,3,5};
         combinationSum(c,8);
         for(List<Integer> list:arrayLists){
            System.out.println(list.toString());
        }
    }
}
