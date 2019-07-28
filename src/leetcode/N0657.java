package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

public class N0657 {
    public boolean judgeCircle(String moves) {
        if(moves==null||moves.length()==0||moves.length()==1) return false;
        int l=0,r=0,u=0,d=0;
        for(int i=0;i<moves.length();i++){
            char c = moves.charAt(i);
            if(c=='U') u++;
            if(c=='R')r++;
            if(c=='L')l++;
            if(c=='D')d++;
        }
        if(l==r&&u==d){
            return true;
        }
        return false;
    }

    @Test
    public void test(){
        String s="LL";
        System.out.println(judgeCircle(s));
    }

    public int consecutiveNumbersSum(int N) {
        if(N==1) return 1;
        int i = 2;
        int count = 3;
        int sum = 1;
        while(count<=N){
            if(N%i==count%i)sum++;
            i++;
            count+=i;
        }
        return sum;
    }

    @Test
    public void test1(){
        System.out.println(consecutiveNumbersSum(8));
    }
}
