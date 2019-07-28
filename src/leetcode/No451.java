package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class No451 {
    public String frequencySort(String s) {
        Map<Character, Integer> stringIntegerMap = new HashMap<>();
        for(char c:s.toCharArray()){
            stringIntegerMap.put(c,stringIntegerMap.getOrDefault(c,0)+1);
        }

        List<Character>[] list = new ArrayList[s.length()+1];
        for(char key:stringIntegerMap.keySet()){
            int f = stringIntegerMap.get(key);
            if(list[f]==null){
                list[f] = new ArrayList<>();
            }
            list[f].add(key);
        }

        StringBuilder str = new StringBuilder();
        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i] == null) {
                continue;
            }
            for (char c : list[i]) {
                for (int j = 0; j < i; j++) {
                    str.append(c);
                }
            }
        }
        return str.toString();
    }
}
