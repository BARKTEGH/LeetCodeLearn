package leetcode;
/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
 */


import org.junit.Test;



public class No83RemoveDuplicatesfromSortedList {



    public ListNode deleteDuplicates(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }

        ListNode temp = head;
        while (temp != null){
            if (temp.next == null){
                break;
            }
            //相等，就跳过该节点，继续比较；不相等，temp向后走一个节点
            if (temp.next.val == temp.val){
                temp.next = temp.next.next;
            }else {
                temp = temp.next;
            }

        }
        return head;

    }

    //  采用递归方法
     public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        } else {
            while ((head = head.next).next != null && (head.val == head.next.val)) {
            }
            return deleteDuplicates(head);
        }
    }



    @Test
    public void test(){
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next =node4;
        node4.next = node5;
        ListNode headNew = deleteDuplicates(head);

        while(headNew != null){
            System.out.println(headNew.val);
            headNew = headNew.next;
        }

    }
}
