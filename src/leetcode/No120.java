package leetcode;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.List;

public class No120 {
     public int minimumTotal(List<List<Integer>> triangle) {
        int n=triangle.size(),c;
        Integer[] b=triangle.get(n-1).toArray(new Integer[0]);

        for (int i = n-2; i >=0 ; i--) {
            c=0;
            for(Integer num:triangle.get(i)){
                b[c]=num+Math.min(b[c],b[c+1]);
                c++;
            }
        }

        return b[0];
    }




}

