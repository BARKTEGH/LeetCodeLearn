package leetcode;

public class No148SortList {

     public ListNode sortList(ListNode head){
         if(head==null||head.next==null){
             return head;
         }

         ListNode fast = head.next;
         ListNode slow = head;

         while(fast!=null && fast.next!=null){
             slow = slow.next;
             fast = fast.next.next;
         }

         ListNode tmp = slow.next;
         slow.next = null;

         ListNode left = sortList(head);
         ListNode right = sortList(tmp);

         return merge(left,right);

     }

     public ListNode merge(ListNode l1,ListNode l2){
         if(l1==null) return l2;
         if(l2==null) return l1;
         ListNode dummy = new ListNode(0);
         ListNode tmp = dummy;
         while(l1!=null&&l2!=null){

             if(l1.val <l2.val){
                 tmp.next = l1;
                 l1 = l1.next;
             }else {
                 tmp.next = l2;
                 l2 = l2.next;
             }
             tmp = tmp.next;
         }

         if(l1!=null){
             tmp.next = l1;
         }
         if(l2!=null){
             tmp.next = l2;
         }

         return dummy.next;
     }


     public static void main(String[] args) {
        int[] nums = {4,2,3,1};
        ListNode head = Utils.newList(nums);
        Utils.printList(head);
         No148SortList sortList = new No148SortList();
         ListNode head1 = sortList.sortList(head);
        Utils.printList(head1);

    }
}
