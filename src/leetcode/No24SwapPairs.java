package leetcode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No24SwapPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode pre = newHead;
        ListNode next ;
        ListNode tmp;
        while(pre!=null&&pre.next!=null&& pre.next.next!= null){
            next = pre.next.next.next;
            tmp = pre.next;
            pre.next = tmp.next;
            pre.next.next = tmp;
            tmp.next = next;

            pre = tmp;
        }
        return newHead.next;
    }



    public static void main(String[] args) {
        No24SwapPairs no24SwapPairs = new No24SwapPairs();
        int[] list = {1,2,3,4};
        ListNode head = Utils.newList(list);
        Utils.printList(head);
        ListNode listNode = no24SwapPairs.swapPairs(head);
        Utils.printList(listNode);
    }
}
