package leetcode;

import org.junit.Test;

public class No482 {
   public String licenseKeyFormatting(String S, int K) {
        S = S.replace("-","");
        int len = S.length();
        S = S.toUpperCase();
        if(len<K) return S;
        else {
            StringBuffer stringBuffer = new StringBuffer(S);
            int insert = len-K;
            while(insert>0){
                stringBuffer.insert(insert,"-");
                insert = insert-K;
            }
            return stringBuffer.toString();
        }

    }

    @Test
    public void test(){
       String s = "5F3Z-2e-9-w";
       int k =4;
       licenseKeyFormatting(s,k);
    }
}
