package leetcode;

public class No231 {

    public boolean isPowerOfTwo1(int n) {
        if(n==0) return false;
        if(n==1) return true;
        while(n%2==0){
            n = n/2;
            if(n==1) return true;

        }
        return false;
    }

    public boolean isPowerOfTwo(int n) {
        return n>0 && (n&(n-1))==0;
    }

    public boolean isPowerOfTwo2(int n) {
        return n>0 && Integer.bitCount(n)==1;
    }
}
