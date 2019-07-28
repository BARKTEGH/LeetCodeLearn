package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class No131 {

    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<List<String>>();
        if (s == null || s.length() == 0)
            return list;
        ArrayList<String> temp = new ArrayList<String>();
        dfs(s,list,temp,0,s.length());
        return list;
    }

    public  void dfs(String s,List<List<String>> list,ArrayList<String> temp,int start,int end){
        if(start == end){
            list.add(new ArrayList<String>(temp));
        }
        for(int i=start+1;i<=end;i++){
            if(isPalindrome(s.substring(start,i))){
                temp.add(s.substring(start,i));
                dfs(s,list,temp,i,end);
                temp.remove(temp.size()-1);
            }

        }
    }


    public static boolean isPalindrome(String s){
          int i=0,j=s.length()-1;
         while(i<j){
              if(s.charAt(i) != s.charAt(j)){
                  return false;
              }
             i++;
             j--;
          }
          return true;
      }

      @Test
    public void test(){
        String s = "a";
        partition(s);
      }
}
