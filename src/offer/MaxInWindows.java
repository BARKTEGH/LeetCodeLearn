package offer;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaxInWindows {
    public ArrayList<Integer> maxInWindows(int[] num, int size){
        ArrayList<Integer> ret = new ArrayList<>();
        if(num==null||num.length==0||num.length<size) return ret;

        Queue<Integer> queue = new PriorityQueue<>((o1, o2) ->o2-o1);

        for(int i=0;i<size;i++){
            queue.add(num[i]);
        }
        ret.add(queue.peek());
        for(int j=size;j<num.length;j++){
            queue.remove(num[j-size]);
            queue.add(num[j]);
            ret.add(queue.peek());
        }
        return ret;
    }

    public static void main(String[] args) {
        MaxInWindows maxInWindows = new MaxInWindows();
        int[] num = {2,3,4,2,6,2,5,1};
        System.out.println(maxInWindows.maxInWindows(num,3));
    }
}
