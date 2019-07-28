package leetcode;

import org.junit.Test;

import java.util.Arrays;

/*
给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。

如果不能形成任何面积不为零的三角形，返回 0。



示例 1：

输入：[2,1,2]
输出：5
示例 2：

输入：[1,2,1]
输出：0
示例 3：

输入：[3,2,3,4]
输出：10
示例 4：

输入：[3,6,2,3]
输出：8
 */
public class No976 {

    public int largestPerimeter(int[] A) {
        if( A.length< 3) return 0;
        Arrays.sort(A);
        for(int i= A.length-3;i>=0;i--){
            if(A[i]+A[i+1]>A[i+2]) return A[i]+A[i+1]+A[i+2];
        }
        return 0;
        
    }

    @Test
    public void test(){
        int[] a = new int[] {2,1,2};
        System.out.println(largestPerimeter(a));
    }

}
