package leetcode;/*
将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
示例 1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
示例 2:

输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:

L     D     R
E   O E   I I
E C   I H   N
T     S     G
 */

public class No6ZigZagConversion {
    //思路第几个字符串位于第几行
    //假如为3行  1-1 2-2 3-3 4-2 5-1 循环
    //位置除以2n-2 余数为0就在第几行
    public String convert(String s, int numRows) {
        if(s.length() <= numRows || numRows==1){
            return s;
        }
        int xunhuanshu = 2*numRows -2;
        String[] strings = new String[numRows];
        for (int k=0; k<numRows; k++) {
            strings[k] = "";
        }

        int i = 0;
        while(i< s.length()){
//            strings[0] += s.charAt(i);
            for(int j=0; j < xunhuanshu; j++){
                if ((i+j)== s.length()){
                    break;
                }
                if(j < numRows){
                    strings[j] += s.charAt(i+j);
                }
                else{
                    strings[xunhuanshu-j] += s.charAt(i+j);
                }
            }
            i += xunhuanshu;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str:strings) {
            stringBuffer.append(str);
        }
      return new String(stringBuffer);
    }
//    @Test
//    public void test1(){
//        String string = "abcdefg";
//        String s = convert(string,3);
//        string += 'a';
//          System.out.println(string);
//    }

    public static void main(String[] args){
        String string = "abcdefg";
        No6ZigZagConversion no6ZigZagConversion = new No6ZigZagConversion();
        String s = no6ZigZagConversion.convert(string,4);
        System.out.println(s);
    }
}
