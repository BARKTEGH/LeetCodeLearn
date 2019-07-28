package leetcode;

import java.util.ArrayList;
import java.util.List;

public class No17 {

    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
     public List<String> letterCombinations(String digits) {
         List<String> strings = new ArrayList<>();
         if (digits == null || digits.length() == 0) {
            return strings;
        }
         dfs(digits,strings,new StringBuffer());
         return strings;
     }

    private void dfs(String digits,List<String> strings,StringBuffer stringBuffer){
         if(stringBuffer.length()==digits.length()){
             strings.add(stringBuffer.toString());
             return;
         }
         int cur = digits.charAt(stringBuffer.length())-'0';
         for(char c: KEYS[cur].toCharArray()){
             stringBuffer.append(c);
             dfs(digits,strings,stringBuffer);
             stringBuffer.deleteCharAt(stringBuffer.length()-1);
         }
    }
}
