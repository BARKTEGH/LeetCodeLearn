package leetcode;

public class No233 {
    public static int NumberOf1Between1AndN_Solution(int n){
        int count =0;
        int divider = 0;
        for(int i=1;i<=n;i*=10){
            divider = i*10;
            count += (n/divider)*i + Math.min(i,Math.max(n%divider-i+1,0));
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(NumberOf1Between1AndN_Solution(10));
    }
}
