package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No279 {

    public int numSquares(int n) {
        List<Integer> squares = generateSquares(n);
        int path = 0;
        Queue<Integer>  queue= new LinkedList<>();
        boolean[] marked = new boolean[n+1];
        queue.add(n);
        marked[n] = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            path ++;
            while (size-- >0){
                int a = queue.poll();
                for (int square: squares
                     ) {
                    int next =a -square;
                    if(next<0) break;;
                    if(next==0) return path;
                    if(marked[next]) continue;
                    marked[next] = true;
                    queue.add(next);

                }
            }
        }
        return n;
    }

    public List<Integer> generateSquares(int n){
        List<Integer> list = new ArrayList<>();
        int square = 1;
        int diff =3;
        while(square<=n){
            list.add(square);
            square += diff;
            diff +=2;
        }
        return list;
    }
}
