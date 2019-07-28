package leetcode;/*
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

输入: 123
输出: 321
 示例 2:

输入: -123
输出: -321
示例 3:

输入: 120
输出: 21
注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */

import org.junit.Test;

import java.sql.SQLOutput;

public class No7ReverseInteger {

    public int reverse(int x) {
        boolean ismiu = false;
        if(x==0){return x; }
        if(x<0){ismiu = true; x = Math.abs(x);}
        StringBuffer stringBuffer = new StringBuffer();
        while(x>0){
            int a = x%10;
            stringBuffer.append(a);
            x = x / 10;
        }
        try{
        x = Integer.valueOf(new String(stringBuffer)).intValue();}
        catch (Exception e){
            return 0;
        }
        if(ismiu==true){
            x = -x;
        }
//        if(x>(Math.pow(2,31)-1))
//            return 0;
        return x;
    }



    public int method2(int x){
       long rev = 0l;
        while(x != 0){
            rev = rev * 10 + x % 10;
            if(rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE){
                return 0;
            }
            x /= 10;
        }
        return (int)rev;
    }

    public int method3(int x){
        int flag = 1;
    	if(x<0){
    		flag=0;
    	}

    	 x=Math.abs(x);
    	StringBuffer sb = new StringBuffer(x+"");
    	sb=sb.reverse();
    	String ss = sb.toString();

    	try{
    		x =  Integer.valueOf(ss);
    	}catch(Exception e){
    		return 0;
    	}

    	if(flag==0){
    		x=x*(-1);
    	}
    	return x;
    }

      @Test
    public void test(){
        int x = 153;
        x = reverse(x);
        int y = method2(x);
        int z =method3(x);
        System.out.println(x);
        System.out.println(y);
          System.out.println(z);
    }

}
