package leetcode;

import java.util.PriorityQueue;

public class No1005 {

    public int largestSumAfterKNegations(int[] A, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int a:A){
            queue.add(a);
        }
        while(K-->0){
            Integer samll = queue.poll();
            queue.add(-1*samll);
        }
        int sum=0;
        int i=0;
        for(int q:queue){
            sum+=q;
        }
        return sum;
    }
}
