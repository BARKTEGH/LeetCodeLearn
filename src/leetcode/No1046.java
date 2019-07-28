package leetcode;

import java.util.*;

public class No1046 {
    public int lastStoneWeight(int[] stones) {
       while(stones.length>1){
           Arrays.sort(stones);
           int y = stones[stones.length-1];
           int x = stones[stones.length-2];
           if(y==x){
               stones = Arrays.copyOfRange(stones,0,stones.length-2);
           }else {
               stones = Arrays.copyOfRange(stones,0,stones.length-1);
               stones[stones.length-1]  = y-x;
           }
       }
       if(stones.length==1){
           return stones[0];
       }else {
           return 0;
       }
    }
    public int lastStoneWeight1(int[] stones) {
        int k = stones.length;
       while(k>1){
           Arrays.sort(stones);
           int y = stones[k-1];
           int x = stones[k-2];
           if(y==x){
               k=k-2;
           }else {
               stones[k-2] = y-x;
               k--;
           }
       }
       if(k==1){
           return stones[0];
       }else {
           return 0;
       }
    }


    public int lastStoneWeight2(int[] stones){
        PriorityQueue<Integer> queue = new PriorityQueue<>(((o1, o2) -> o2-o1));
        for(int stone:stones){
            queue.add(stone);
        }
        while(queue.size()>1){
            int x = queue.poll();
            int y = queue.poll();
            if(x!=y) {
                queue.add(x-y);
            }
        }
        return queue.size()==1?queue.poll():0;
    }



    public static void main(String[] args) {
        No1046 no1046 = new No1046();
        int[] stones  ={2,7,4,1,8,1};
        System.out.println(no1046.lastStoneWeight1(stones));
    }
}
