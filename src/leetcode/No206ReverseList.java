package leetcode;

public class No206ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode dummy = new ListNode(-1);

        while(head!=null){
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] num = {1,2,3,4,5};
        ListNode head = Utils.newList(num);
        Utils.printList(head);
        No206ReverseList reverseList = new No206ReverseList();
        ListNode node = reverseList.reverseList(head);
        Utils.printList(node);

    }
}
