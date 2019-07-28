package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class No139 {


    /**
     * 回溯算法，超出时间限制
     */
    private HashSet<String> hashSet = new HashSet<>();
     public boolean wordBreak(String s, List<String> wordDict) {
         hashSet.addAll(wordDict);
        return dfs(s);
    }



    public boolean dfs(String s){
         if(s.length()==0) return true;
         for(int i=1;i<=s.length();i++){
             if(hashSet.contains(s.substring(0,i))){
                 if(dfs(s.substring(i)))
                     return true;
             }
         }
         return false;
    }

    /**
     * 动态规划，dp[i] 表示字符串s前i个字符能否有wordDict构成
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        hashSet.addAll(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                if(dp[j]&&hashSet.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("leet");
        strings.add("code");
        No139 no139 = new No139();
        System.out.println(no139.wordBreak1("leetcode",strings));

    }
}
