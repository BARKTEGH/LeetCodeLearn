package leetcode;

import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;

/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.
 */
public class No14LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==1){
            return strs[0];
        }else if(strs.length == 0){
            return "";
        }
        int len = strs.length;
        boolean flag = false;
        int k = 0;
        StringBuffer stringBuffer = new StringBuffer("") ;
        while (flag == false){
            if(strs[0].length()==k){
                    break;
            }
            char temp = strs[0].charAt(k);
            for(int i=1;i<len;i++){
                if(strs[i].length()==k){
                    flag = true;
                    break;
                }
                char temp1 = strs[i].charAt(k);
                if(temp != temp1){
                    flag = true;
                    break;
                }
            }
            if (flag ==false){
                stringBuffer.append(temp);
            }
            k++;
        }
        return new String(stringBuffer);
    }

    //5ms 思路：对数组中没个字符串进行查找两者从开始的部分，保留这部分
    public String longestCommonPrefix2(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        String prefix = strs[0];
            for(int i = 1; i < strs.length; i++) {
                while(strs[i].indexOf(prefix) != 0 && prefix.length() > 0) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                }
            }
        return prefix;
    }

    @Test
    public void test(){
        String[] strs = new String[] {"asssss","ass"};
        String str = longestCommonPrefix(strs);
        System.out.println(str);
    }
}
