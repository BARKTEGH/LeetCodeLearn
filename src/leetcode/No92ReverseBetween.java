package leetcode;

public class No92ReverseBetween {

     public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode tmp = dummy;
        dummy.next = head;
        ListNode start = dummy;
        ListNode end = dummy;
        int count = 0;
        while(tmp!=null){
            count++;
            if(count==m){
                start = tmp;
                end = tmp.next;
            }
            if(count>m&&count<=n+1){
                ListNode tmp2 = tmp.next;
                ListNode next = start.next;
                start.next = tmp;
                tmp.next = next;

                end.next = tmp2;
                tmp = tmp2;
                continue;
            }
            tmp = tmp.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {

         int[] list = {1,2,3,4,5,6,7};
        ListNode head = Utils.newList(list);
        Utils.printList(head);
        No92ReverseBetween between = new No92ReverseBetween();
        ListNode l = between.reverseBetween(head,2,4);
        Utils.printList(l);
    }
}
