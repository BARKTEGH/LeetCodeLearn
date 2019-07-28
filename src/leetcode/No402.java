package leetcode;

public class No402 {

    //删除a[i]>a[i+1]的i元素
    //如果符合升序，那就删除最后一个
    public String removeKdigits(String num, int k) {
        if(k==0) return num;
        if(num.length()==k) return "0";

        char[] chars = num.toCharArray();
        while(k-->0){
            boolean flag = true;
            for(int i=0;i<num.length()-1;i++){
                if(chars[i]=='#') continue;
                int tmp = i+1;
                while(tmp<chars.length&&chars[tmp]=='#') tmp++;
                if(tmp==chars.length) break;
                 if(chars[i]>chars[tmp]){
                     chars[i] = '#';
                     flag = false;
                     break;
                 }
            }
            if(flag){
                int index = chars.length-1;
                while(index>=0&&chars[index]=='#') index--;
                chars[index] = '#';
            }
        }


        StringBuffer sb= new StringBuffer();
        int i = 0;
        while(i<chars.length&&(chars[i]=='0'||chars[i]=='#')) i++;
        for(;i<chars.length;i++){
            if(chars[i]=='#')continue;
            sb.append(chars[i]);
        }
        return sb.length()==0?"0":sb.toString();
    }

    public static void main(String[] args) {
        No402 no402 = new No402();
        System.out.println(no402.removeKdigits("11111111",3));
    }
}
