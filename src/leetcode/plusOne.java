package leetcode;

import org.junit.Test;

import java.util.Arrays;

public class plusOne {
    @Test
    public void test(){
        int[] list1= new int[] {9,9,9,9};
        System.out.println(Arrays.toString(plusOnes(list1)));
    }

    public int[] plusOnes(int[] digits) {

        int len = digits.length;
        int i = len-1;
        boolean flag = false;
        while(i!=-1){
            if(digits[i]<9){
                digits[i] +=1;
                flag = true;
                break;
            }else {
                digits[i] = 0;
                i -= 1;
            }
        }
        if(flag==false){
            int[] list = new int[digits.length+1];
            list[0] = 1;
            return list;
        }
        return digits;

    }
}
