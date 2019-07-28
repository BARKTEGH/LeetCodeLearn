package leetcode;

import org.junit.Test;

public class No3 {
    public int lengthOfLongestSubstring(String s) {
        if(s==null||s.length()==0) return 0;
        int maxlong = 1;

        int findindex = 0;
        for(int i=0;i<s.length();i++){
            findindex = findrepeatChar(s,s.charAt(i),findindex,i);
            if(i-findindex+1> maxlong){
                maxlong = i-findindex+1;
            }
        }
        return maxlong;
    }

    public int findrepeatChar(String s,char c, int start,int end){
        for(int i=start;i<end; i++){
            if(s.charAt(i)==c){
                return i+1;
            }
        }
        return start;
    }

    @Test
    public void test(){
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
