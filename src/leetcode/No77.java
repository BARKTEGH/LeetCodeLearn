package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No77 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> arrayLists = new ArrayList<>();
        if(n<k) return arrayLists;
        List<Integer> temp = new ArrayList<>();
        dfs(arrayLists,temp,n,k,1);
        for(List<Integer> list:arrayLists){
            System.out.println(list.toString());
        }
        return arrayLists;
    }


    public void dfs(List<List<Integer>> arrayLists ,List<Integer> temp,int n,int k,int start){
        if(temp.size()==k){
            arrayLists.add(new ArrayList<>(temp));
            return ;
        }
        for(int i=start;i<=n-(k-temp.size())+1;i++){
            temp.add(i);
            dfs(arrayLists,temp,n,k,i+1);
            temp.remove(temp.size()-1);

        }
    }

    @Test
    public void test(){
        combine(4,2);
    }


}
