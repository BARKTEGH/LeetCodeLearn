package leetcode;

import org.junit.Test;

/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 */
public class No5LongestPalindromicSubString {

    /*
    方法四：中心扩展算法
    事实上，只需使用恒定的空间，我们就可以在 O(n^2)O(n2) 的时间内解决这个问题。
    我们观察到回文中心的两侧互为镜像。因此，回文可以从它的中心展开，并且只有 2n - 12n−1 个这样的中心。
    为什么会是 2n - 12n−1 个，而不是 nn 个中心？
    原因在于所含字母数为偶数的回文的中心可以处于两字母之间（
    例如 \textrm{“abba”}“abba” 的中心在两个 \textrm{‘b’}‘b’ 之间）。
     */
     public String longestPalindrome(String s) {
        if(s ==null || s.length() < 1 ) return "";
        int start = 0;
        int end = 0;
        for(int i=0;i<s.length();i++){
            int len1 = expandAroundCenter(s,i,i);
            int len2 = expandAroundCenter(s,i,i+1);
            int len = Math.max(len1,len2);
            if(len > end-start){
                start = i - (len-1)/2;
                end = i+len/2;
            }
        }
        return s.substring(start,end+1);
    }
    public int expandAroundCenter(String s,int left,int right){
         while(left >=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
             left--;
             right++;
         }
         return right-left-1;
    }

    @Test
    public void test4(){
         String s = "asdsdasdasdadfd";
         System.out.println(longestPalindrome(s));
    }
}
