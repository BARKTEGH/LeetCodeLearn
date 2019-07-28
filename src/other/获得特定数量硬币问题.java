package other;

public class 获得特定数量硬币问题 {

    public static String getCoin(int n){
        if(n==0) return "";
        StringBuffer sb = new StringBuffer();
        while(n>0){
            if(n%2==0){
                sb.append('2');
                n = (n-2)/2;
            }else {
                sb.append('1');
                n = (n-1)/2;
            }
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getCoin(10));
        System.out.println(1>>2);
    }
}
