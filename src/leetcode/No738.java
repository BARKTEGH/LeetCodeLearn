package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class No738 {
     public int monotoneIncreasingDigits(int N) {
        if(N<=9) return N;
        ArrayList list = new ArrayList<Integer>();
        while(N/10>0){
            list.add(N%10);
            N= N/10;
        }
        list.add(N);
        int num=0;
        for(int i=list.size()-1;i>0;){
            if((int)list.get(i) > (int)list.get(i-1)){
                return num+((int)list.get(i)-1)* (int)Math.pow(10,i)+(int)Math.pow(10,i)-1;
            }else if( (int)list.get(i) == (int)list.get(i-1)){
                int k = i;
                i--;
                while((int)list.get(i) == (int)list.get(i-1)) i--;
                if((int)list.get(i) < (int)list.get(i-1)){
                    for(int j=i;j<=k;j++){num = num+(int)list.get(j)* (int)Math.pow(10,j);}
                    i--;
                    continue;
                }else {
                    return num+((int)list.get(k)-1)* (int)Math.pow(10,k)+(int)Math.pow(10,k)-1;
                }
            }
            num += (int)list.get(i)* (int)Math.pow(10,i);
            i--;
        }
        return num+(int)list.get(0);
    }
    @Test
    public void test(){
        System.out.println(monotoneIncreasingDigits(668841));
    }
}
