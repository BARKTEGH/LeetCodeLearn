package leetcode;

public class No44 {

    public boolean isMatch(String s, String p) {
        int sLen= s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen+1][pLen+1];
        dp[0][0] = true;
        for(int j=1;j<=pLen;j++){
            if(p.charAt(j-1)=='*'){
                dp[0][j] = dp[0][j-1];
            }
        }
        for(int i=1;i<=sLen;i++){
            for(int j=1;j<=pLen;j++){
                if(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='?'){
                    dp[i][j] = dp[i-1][j-1];
                }
                if(p.charAt(j-1)=='*'){
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[sLen][pLen];
    }

    public boolean isMatch2(String s, String p) {
        if(s.equals(p)) return true;
        if(s.length()==0&&p.length()==0) return true;
        boolean firstMatch = p.charAt(0)==s.charAt(0)||p.charAt(0)=='?';
        if(firstMatch){
            return isMatch2(s.substring(1),p.substring(1));
        }else if(p.charAt(0)=='*'){
            return isMatch2(s,p.substring(1))||isMatch2(s.substring(1),p.substring(1))||isMatch2(s.substring(1),p);
        }
        return false;
    }


    public static void main(String[] args) {
        No44 no44 = new No44();
        System.out.println(no44.isMatch2("aab","*a*b"));
    }
}
