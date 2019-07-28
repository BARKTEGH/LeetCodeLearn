package leetcode;

public class No680 {
    public boolean validPalindrome(String s) {
        for(int i=0,j=s.length()-1;i<j;i++,j--){
            if(s.charAt(i)!= s.charAt(j)){
                return isvalidPalindromeStr(s,i,j-1)||isvalidPalindromeStr(s,i+1,j);
            }
        }
        return true;
    }

    private boolean isvalidPalindromeStr(String s ,int i, int j){
        while(i<j){
            if(s.charAt(i++)!=s.charAt(j--)){
                return false;
            }
        }
        return true;
    }
}
