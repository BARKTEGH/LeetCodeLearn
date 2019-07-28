package leetcode;

import java.util.List;
import java.util.Stack;

public class No445AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while(l1!=null){
            stack1.add(l1.val);
            l1 = l1.next;
        }
        while(l2!=null){
            stack2.add(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode head=null;
        while(!stack1.isEmpty()||!stack2.isEmpty()){
            int a = (stack1.isEmpty())?0:stack1.pop();
            int b = (stack2.isEmpty())?0:stack2.pop();
            int sum = a+b+carry;
            carry = sum/10;
            ListNode cur=new ListNode(sum%10);
            //务必要注意，对结果还要进行一次反转，结果也是从高位到低位
            cur.next=head;
            head=cur;
        }
        if(carry!=0){
            ListNode cur=new ListNode(carry);
            cur.next=head;
            head=cur;
        }
        return head;

    }
}
