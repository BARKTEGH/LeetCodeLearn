package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class No216 {

    private List<List<Integer>> lists;
    public List<List<Integer>> combinationSum3(int k, int n) {
        lists = new ArrayList<>();
        if(n<=0||n>45||((19-k)*k<2*n)) return lists;
        List<Integer> temp = new ArrayList<>();
        dfs(temp,n,k,0,1);
        return lists;
    }

    public void dfs(List<Integer> temp,int n,int k,int sum,int start){
        if(sum>n){
            return;
        }
        if(sum==n&&k==temp.size()){
            lists.add(new ArrayList<>(temp));
            return;
        }else if(sum==n){
            return;
        }
        for(int i=start;i<=9;i++){
            temp.add(i);
            dfs(temp,n,k,sum+i,i+1);
            temp.remove(temp.size()-1);
        }
    }

     @Test
    public void test(){
        combinationSum3(2,18);
        for(List<Integer> list:lists){
            System.out.println(list.toString());
        }
    }
}
