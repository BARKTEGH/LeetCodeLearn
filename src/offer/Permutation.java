package offer;

import java.util.ArrayList;
import java.util.Arrays;

public class Permutation {
    private ArrayList<String> ret = new ArrayList<>();

    public ArrayList<String> Permutation(String str) {
        if (str.length() == 0)
            return ret;
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        backtracking(chars, new boolean[chars.length], new StringBuffer());
        return ret;
    }

    public void backtracking(char[] chars,boolean[] hasVisit,StringBuffer s){
        if(chars.length==s.length()){
            ret.add(s.toString());
            return;
        }
        for (int i=0;i<chars.length;i++){
            if(hasVisit[i]){
                continue;
            }
            //去重，相同字符一定要前面字符被访问过
            if(i>0&&chars[i]==chars[i-1]&&!hasVisit[i-1]) continue;
            hasVisit[i] = true;
            s.append(chars[i]);
            backtracking(chars,hasVisit,s);
            s.deleteCharAt(s.length());
            hasVisit[i] = false;
        }

    }
}
