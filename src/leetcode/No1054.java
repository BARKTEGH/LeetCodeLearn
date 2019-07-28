package leetcode;

import java.util.*;

public class No1054 {
    public int[] rearrangeBarcodes(int[] barcodes) {
        PriorityQueue<Map.Entry<Integer,Integer>> queue = new PriorityQueue<>(((o1, o2) -> o2.getValue()-o1.getValue()));
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int code:barcodes) map.put(code,map.getOrDefault(code,0)+1);
        for(Map.Entry entry:map.entrySet()){
            queue.add(entry);
        }
        int[] res = new int[barcodes.length];
        for(int i=0;i<barcodes.length;i++){
            Map.Entry entry = queue.poll();
            if(i==0){
                res[i] = (int)entry.getKey();
                entry.setValue((int)entry.getValue()-1);
                queue.add(entry);
                continue;
            }
            if(res[i-1]!=(int)entry.getKey()){
                res[i] = (int)entry.getKey();
                 entry.setValue((int)entry.getValue()-1);
                queue.add(entry);
            }else {
                Map.Entry entry2 = queue.poll();
                res[i] =(int)entry2.getKey();
                entry2.setValue((int)entry2.getValue()-1);
                queue.add(entry);
                queue.add(entry2);
            }

        }
        return res;
    }

    public static void main(String[] args) {
        No1054 no1054 = new No1054();
        int[] nums = {2,2,2,1,5};
        System.out.println(Arrays.toString(no1054.rearrangeBarcodes(nums)));
    }
}
