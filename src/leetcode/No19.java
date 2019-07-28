package leetcode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No19 {

    /**
     * 思路：通过先走n步，在两个指针同时走
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead = new ListNode(-1);
        newHead.next = head;

        ListNode fast = newHead;
        while(n-->0){
            fast = fast.next;
        }
        ListNode slow = newHead;
        while (fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return newHead.next;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        ListNode head = Utils.newList(nums);
        Utils.printList(head);
        No19 no19 = new No19();
        no19.removeNthFromEnd(head,2);
        Utils.printList(head);

    }
}
