package leetcode;

import java.util.List;

public class No147InsertionSortList {

    public ListNode insertionSortList(ListNode head) {

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        ListNode insert = dummy;

        while(cur!=null&&cur.next!=null){

            if(cur.val<cur.next.val){
                cur = cur.next;
                continue;
            }

            insert = dummy;

            while(insert.next.val<cur.next.val) {
                insert = insert.next;
            }

            //
            ListNode tmp = cur.next;
            cur.next = tmp.next;
            tmp.next = insert.next;
            insert.next = tmp;

        }

       return dummy.next;
    }

    public static void main(String[] args) {
        int[] nums = {4,2,3,1};
        ListNode head = Utils.newList(nums);
        Utils.printList(head);
        No147InsertionSortList  insertionSortList= new No147InsertionSortList();
        ListNode head1 = insertionSortList.insertionSortList(head);
        Utils.printList(head);
    }
}
