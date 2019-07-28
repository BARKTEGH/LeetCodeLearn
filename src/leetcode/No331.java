package leetcode;

import java.util.Stack;

public class No331 {
     public boolean isValidSerialization2(String preorder) {
        int n = preorder.length();
        int num =0 ;
        for(int i=preorder.length()-1;i>=0;i--){
            if(preorder.charAt(i)==','){continue;}
            if(preorder.charAt(i)=='#') {num++;}
            else{
                while(i>=0 && preorder.charAt(i)!= ',')//节点数字可能有多位
                    i--;
                if(num >= 2)//#的个数>=2，消除2个#，消除一个节点数字并转换成#，即num-1
                    num--;
                else
                    return false;//#的个数不足2，证明false
            }
        }
        if(num!=1) return false;
        return true;
    }


    //未通过测试
    public boolean isValidSerialization(String preorder){
        Stack<String> stack  = new Stack<>();
        String[] str = preorder.split(",");
        for(int i=0;i<str.length;i++){
            if(!str[i].equals("#")){
                stack.push(str[i]);
            }else{
                if(stack.isEmpty()) return false;
                stack.push("#");
                while("#".equals(stack.peek())&&stack.size()>1) {
                    stack.pop();
                    String  s = stack.peek();
                    if(s.equals("#")){
                        stack.pop();
                        String s1 = stack.pop();
                        if(s1.equals("#")) return false;
                        stack.push("#");
                        continue;
                    }else {
                        stack.push("#");
                        break;
                    }
                }

            }
        }
        if(stack.size()==1&&"#".equals(stack.peek())) return true;
        return false;
    }

    public static void main(String[] args) {
        No331 no331 = new No331();
        System.out.println(no331.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }
}
