package offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class printListFromTailToHead {
    public class ListNode {
          int val;
           ListNode next = null;

           ListNode(int val) {
               this.val = val;
           }
        }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> integers = new ArrayList<>();
        if(listNode == null) return integers;
        while(listNode!=null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while(!stack.isEmpty()){
            integers.add(stack.pop());
        }
        return integers;
    }

}
