package leetcode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class No301 {

    /**
     *
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> res  = new ArrayList<>();
        if(s==null) return res;
        int left = 0;
        int right = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                left++;
            }
            if(s.charAt(i)==')'){
                if(left>0) left--;
                else {
                    right++;
                }
            }
        }

        dfs(res,s,0,left,right);

        System.out.println(res);
        return res;
    }

    private void dfs(List<String> res,String s,int start,int left,int right){
        if(left==0&&right==0){
            if(check(s)){
                res.add(s);
            }
            return;
        }
        if(left<0||right<0){
            return;
        }

        for(int i=start;i<s.length();i++){
            //去重，这个去重有点问题
            if(i-left>=start&&i>0&&s.charAt(i)==s.charAt(i-1)) continue;
            if(left>0&&s.charAt(i)=='(') dfs(res,s.substring(0,i)+s.substring(i+1),i,left-1,right);
            if(right>0&&s.charAt(i)==')') dfs(res,s.substring(0,i)+s.substring(i+1),i,left,right-1);

        }
    }

    private boolean check(String s){
        int cnt=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') cnt++;
            else if(s.charAt(i)==')'){
                cnt--;
                if(cnt<0) return false;
            }
        }
        return cnt==0;
    }


    /**
     *思路是假设右括号比左括号多，算法设计成只删除右括号的情况。
     *
     * 如果左括号比右括号多怎么办？该算法的巧妙之外就是把这种情况也转换成了删除“右括号”的情况（翻转字符串）。而左右括号的定义是在参数 par 中定义的。
     *
     * 作者：ivan_allen
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/301-shan-chu-wu-xiao-de-gua-hao-di-gui-by-ivan_all/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public List<String> removeInvalidParentheses2(String s){
        List<String> res  = new ArrayList<>();
        if(s==null) return res;

        remove(s,new char[]{'(',')'},0,0,res);


        System.out.println(res);
        return res;


    }

    public void remove(String s,char[] pair,int start,int pre,List<String> res){
        int count =0;
        for(int i=start;i<s.length();i++){
            if(s.charAt(i)==pair[0]) count++;
            if(s.charAt(i)==pair[1]) count--;
            if(count>=0) continue;
            //右括号多出来，删掉pre(上次删掉的位置）到现在多了一个右括号的位置i
            for(int j=pre;j<=i;j++){
                if(s.charAt(j)==pair[1]&&(j==pre||s.charAt(j-1)!=pair[1])){
                    remove(s.substring(0,j)+s.substring(j+1),pair,i,j,res);
                }
            }
            return;
        }
        String s1 = new StringBuilder(s).reverse().toString();
        if(pair[0]=='('){
            pair[1] = '(';
            pair[0] = ')';
            remove(s1,pair,0,0,res);
        }else {
            res.add(s1);
        }


    }

    public static void main(String[] args) {
        No301 no301 = new No301();
        no301.removeInvalidParentheses("((()");
    }



}
