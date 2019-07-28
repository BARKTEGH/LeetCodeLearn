package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class No49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> lists = new ArrayList<>();

        Map<String,Integer> map = new HashMap<>();
        int index =0;
        for(String s:strs){
            String temp = getChar(s);
            if(map.containsKey(temp)){
                lists.get(map.get(temp)).add(s);
            }else{
                List<String> templ = new ArrayList<>();
                templ.add(s);
                lists.add(templ);
                map.put(temp,index);
                index++;
            }
        }
        return lists;
    }

    public String getChar(String s){
        int[] nums = new int[26];
        for(int i=0;i<s.length();i++){
            nums[s.charAt(i)-'a'] = nums[s.charAt(i)-'a'] +1;
        }
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<26;i++){
            if(nums[i]!=0){
                for(int k=0;k<nums[i];k++){sb.append((char)('a'+i));}
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        No49 no49 = new No49();
        String[] strings = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        no49.groupAnagrams(strings);

    }
}
