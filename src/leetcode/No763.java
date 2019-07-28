package leetcode;

import java.util.ArrayList;
import java.util.List;

public class No763 {

    public List<Integer> partitionLabels(String S) {
        List<Integer> ret = new ArrayList<>();
        int[] lastIndexForChar = new int[26];
        for (int i = 0; i < S.length(); i++) {
            lastIndexForChar[S.charAt(i) - 'a'] = i;
        }
        int firstIndex = 0;
        while (firstIndex < S.length()) {
            int lastFirst = firstIndex;
            for (int i = firstIndex; i < S.length() && i <= lastFirst; i++) {
                int index = lastIndexForChar[S.charAt(i) - 'a'];
                if (index > lastFirst) {
                    lastFirst = index;
                }
            }
            ret.add(lastFirst - firstIndex + 1);
            firstIndex = lastFirst + 1;
        }
        return ret;

    }




    public static void main(String[] args) {
        No763 no763 = new No763();
        System.out.println(no763.partitionLabels("abc"));

    }
}
