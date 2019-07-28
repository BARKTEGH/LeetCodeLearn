package leetcode;


/*
给定一个整数（32位有符号整数），请编写一个函数来判断它是否是4的幂次方。

示例1：

输入： 16
 输出： true
示例2：

输入： 5
 输出： false

 这种数在二进制表示中有且只有一个奇数位为 1，例如 16（10000）。
 */
public class No342 {

    public boolean isPowerOfFour(int num) {
        return num>0 && (num&(num-1)) ==0 && (num & 0b01010101010101010101010101010101) != 0;
    }


}

