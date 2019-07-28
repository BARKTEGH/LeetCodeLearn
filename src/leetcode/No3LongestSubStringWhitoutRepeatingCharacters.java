package leetcode;

import com.sun.xml.internal.fastinfoset.util.CharArray;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class No3LongestSubStringWhitoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        Set<Character> set = new HashSet<>();
        int ans =0,i=0,j=0;
        while(i<len && j<len){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans,j-i);
            }
            else {
                set.remove(s.charAt(i++));
            }
            System.out.println(set);
        }
        return ans;
    }
    @Test
    public void test(){
        String ss = "asvds";
        int c = lengthOfLongestSubstring(ss);
    }
}
