package leetcode;

import javafx.util.Pair;


import java.util.Arrays;
import java.util.Stack;

public class No1019NextLargerNodes {

    /**
     * 采用栈保存比之前位置小的元素，遇到更大的就弹出，就能更新最大值了
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {
        if(head==null){
            return new int[]{0};
        }

        int index = 0;
        ListNode tmp =head;
        Stack<Pair<Integer,Integer>> stack = new Stack<>();
        while(tmp!=null){
            index++;
            tmp = tmp.next;
        }

        int[] res = new int[index];
        stack.add(new Pair<>(0,head.val));
        index = 1;
        tmp =head.next;
        while (tmp!=null){
            while(!stack.isEmpty() && stack.peek().getValue()<tmp.val){
                Pair<Integer,Integer> num = stack.pop();
                res[num.getKey()] = tmp.val;
            }

            stack.add(new Pair<>(index,tmp.val));
            tmp = tmp.next;
            index++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        ListNode head = Utils.newList(nums);
        Utils.printList(head);
        No1019NextLargerNodes no1019NextLargerNodes = new No1019NextLargerNodes();
        System.out.println(Arrays.toString(no1019NextLargerNodes.nextLargerNodes(head)));

    }
}
