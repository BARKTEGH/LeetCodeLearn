package leetcode;

public class No382OddEvenList {

    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next ==null){
            return head;
        }

        ListNode one = new ListNode(-1);
        ListNode two = new ListNode(-2);
        ListNode end = one;
        ListNode endT = two;

        while(head!=null){
            end.next = head;
            end = head;

            head = head.next;
            if(head!=null){
                endT.next = head;
                endT = head;
                head = head.next;
            }
        }

        end.next = two.next;
        endT.next = null;
        return one.next;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        ListNode head = Utils.newList(nums);
        Utils.printList(head);

        No382OddEvenList oddEvenList = new No382OddEvenList();
        ListNode node = oddEvenList.oddEvenList(head);
        Utils.printList(node);
    }
}
