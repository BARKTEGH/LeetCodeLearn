package leetcode;

public class No203RemoveElements {

    public ListNode removeElements(ListNode head, int val) {
        if(head==null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode tmp =dummy;
        while(tmp!=null&&tmp.next!=null){
            if(tmp.next.val==val){
                tmp.next = tmp.next.next;
            }else {
                tmp = tmp.next;
            }

        }

        return dummy.next;
    }

    public static void main(String[] args) {
        int[] nums = {1,1};
        ListNode head = Utils.newList(nums);
        Utils.printList(head);
        No203RemoveElements elements = new No203RemoveElements();
        ListNode res = elements.removeElements(head,1);
        Utils.printList(res);
    }

}
