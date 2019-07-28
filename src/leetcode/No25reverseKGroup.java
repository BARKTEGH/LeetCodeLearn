package leetcode;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例 :
 *
 * 给定这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 说明 :
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class No25reverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode pre = newHead;
        ListNode cur = newHead;


        while(cur.next!=null){
            for(int i =0;i<k&&cur!=null;i++){
                cur = cur.next;
            }
            if(cur==null) break;

            ListNode start = pre.next;
            ListNode next = cur.next;
            //先断开连接
            cur.next =  null;
            //翻转链表
            pre.next = reverse(start);
            start.next = next;
            cur = pre = start;

        }
        return newHead.next;
    }

    public ListNode reverse(ListNode head){
        ListNode dummy = new ListNode(-1);
        ListNode tmp = head;
        while (tmp!=null){
            ListNode next = tmp.next;
            tmp.next = dummy.next;
            dummy.next = tmp;
            tmp = next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        No25reverseKGroup no25reverseKGroup = new No25reverseKGroup();
        int[] list = {1,2,3,4,5,6,7};
        ListNode head = Utils.newList(list);
        Utils.printList(head);
        ListNode listNode = no25reverseKGroup.reverseKGroup(head,3);

//        Utils.printList(no25reverseKGroup.reverse(head));
        Utils.printList(listNode);
    }
}
