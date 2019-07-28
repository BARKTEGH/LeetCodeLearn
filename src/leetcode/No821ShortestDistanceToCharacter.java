package leetcode;/*
给定一个字符串 S 和一个字符 C。返回一个代表字符串 S 中每个字符到字符串 S 中的字符 C 的最短距离的数组。

示例 1:

输入: S = "loveleetcode", C = 'e'
输出: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
说明:

字符串 S 的长度范围为 [1, 10000]。
C 是一个单字符，且保证是字符串 S 里的字符。
S 和 C 中的所有字母均为小写字母。
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class No821ShortestDistanceToCharacter {

    public int[] shortestToChar(String S, char C) {
        int[] a=new int[S.length()];
        int start = 0;
        int nextSpace =S.indexOf(String.valueOf(C),start);
        int distance=1;
        if(nextSpace != -1) {
            distance=nextSpace-start;
            for(int i=start;i<=nextSpace;i++){
                a[i]=distance--;
            }
        }
        start = nextSpace+1;
        nextSpace =S.indexOf(String.valueOf(C),start);
        while(nextSpace!=-1){
            distance=(nextSpace-start)/2;
            int ldistance=1;
            for(int i=start;i<nextSpace;i++){
                int mid=(nextSpace+start-1)/2;
                if(i<=mid){ a[i]=ldistance++; }
                else{ a[i]=distance--; }

       }
         start = nextSpace+1;
        nextSpace =S.indexOf(String.valueOf(C),start);
    }
    if(S.lastIndexOf(String.valueOf(C))!=(S.length()-1)){
        distance=1;
        for(int i=(S.lastIndexOf(String.valueOf(C))+1);i<S.length();i++){
            a[i]=distance++;
        }
    }
    return a;

}



    @Test
    public void test(){
        String s= "loveleetcode";
        char c = 'e';
        System.out.println(Arrays.toString(shortestToChar(s,c)));
    }
}


