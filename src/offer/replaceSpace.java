package offer;

import org.junit.Test;

public class replaceSpace {
     public String replaceSpace(StringBuffer str) {
         StringBuffer stringBuffer = new StringBuffer();
         for(int i=0;i<str.length();i++){
            if(' '== str.charAt(i) ) stringBuffer.append("%20");
            else { stringBuffer.append(str.charAt(i));}
        }
        System.out.println(stringBuffer);
    	return stringBuffer.toString();
    }

    @Test
    public void test(){
        StringBuffer stringBuffer = new StringBuffer("We Are Happy");
        replaceSpace(stringBuffer);

    }
}
