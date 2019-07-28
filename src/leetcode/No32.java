package leetcode;

import java.util.Stack;

public class No32 {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.empty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }

    public int longestValidParentheses(String s){
        int maxLen = 0;
        for(int i=0;i<s.length();i++){
            for(int j=i+2;j<=s.length();j+=2){
                if(isValid(s.substring(i,j))){
                    maxLen = Math.max(maxLen,j-i);
                }
            }
        }
        return maxLen;
    }


    public int longestValidParentheses2(String s){
        int maxLen = 0;
        int[] dp = new int[s.length()];
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='('){
                    dp[i] = (i>2?dp[i-2]:0) +2;
                }else if(i-dp[i-1]>0 && s.charAt(i-dp[i-1]-1)=='('){
                    //形式为(()) (())
                    // (())为dp[i-dp[i-1]-2
                    //  (()) 第二个为dp[i-1] +2
                    dp[i] = dp[i-1] + 2 + ((i-dp[i-1]-2>=0)?dp[i-dp[i-1]-2]:0) ;
                }
                maxLen = Math.max(maxLen,dp[i]);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        No32 no32 = new No32();
        System.out.println(no32.longestValidParentheses2("(())))"));
    }
}
