package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No241 {

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> all = new ArrayList<>();
        for(int i=0;i<input.length();i++){
            char c = input.charAt(i);
            if(c == '+'||c == '-'||c == '*'){
                List<Integer> leftlist = diffWaysToCompute(input.substring(0,i));
                List<Integer> rightlist = diffWaysToCompute(input.substring(i+1));
                for(int left:leftlist){
                    for(int right:rightlist){
                        switch (c){
                            case '+':
                                all.add(left+right);
                                break;
                            case '-':
                                all.add(left-right);
                                break;
                            case '*':
                                all.add(left*right);
                                break;
                            default:
                                    break;
                        }
                    }
                }

            }
        }
        if(all.size()==0){
            all.add(Integer.valueOf(input));
        }
        return all;
    }

    @Test
    public void test(){
        String s = "2-1-1";
        System.out.println(diffWaysToCompute(s).toString());
    }
}
