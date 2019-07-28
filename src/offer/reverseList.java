package offer;

public class reverseList {
     public class ListNode {
          int val;
           ListNode next = null;

           ListNode(int val) {
               this.val = val;
           }
        }

     public ListNode ReverseList(ListNode head) {

        if(head==null) return null;
        ListNode reversedHead=null;
        ListNode current=head;
        ListNode tmp=null;
        ListNode pre=null;
        while(current!=null){
            tmp=current.next;
            current.next=pre;
            if(tmp==null)
                reversedHead=current;
            pre=current;
            current=tmp;

         }
         return reversedHead;

    }
}
