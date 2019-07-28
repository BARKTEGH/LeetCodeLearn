package leetcode;

import org.junit.Test;

import java.util.*;

public class No347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
         TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : nums) {
            if (!treeMap.containsKey(num)) {
                treeMap.put(num, 1);
            } else {
                treeMap.put(num, treeMap.get(num) + 1);
            }
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                (a, b) -> treeMap.get(a) - treeMap.get(b)
        );
        for (int key : treeMap.keySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(key);
            } else if (treeMap.get(key) > treeMap.get(priorityQueue.peek())) {
                System.out.println(priorityQueue.peek());
                priorityQueue.remove();
                priorityQueue.add(key);
            }
        }

        LinkedList<Integer> linkedList = new LinkedList<>();
        while (!priorityQueue.isEmpty()) {
            linkedList.add(priorityQueue.remove());
        }
        return linkedList;
    }
    @Test
    public void test(){
        int[] nums = new int[] {1,1,1,2,2,3,3,3,3,3,3,3,3,1,1,1,1,1,1,1,1,2,2,2,2,2,2,4,4,4,4,4,4,4,4,4,4,4,4,4};
        System.out.println(topKFrequent(nums,2));
    }
}
