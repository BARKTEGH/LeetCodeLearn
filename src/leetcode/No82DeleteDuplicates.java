package leetcode;

/*
给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

示例 1:

输入: 1->2->3->3->4->4->5
输出: 1->2->5
示例 2:

输入: 1->1->1->2->3
输出: 2->3
 */
public class No82DeleteDuplicates {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode keep = dummy;
        OUT:
        while (true) {
            if (pre.val == cur.val) {
                while (pre.val == cur.val) {
                    cur = cur.next;
                    if (cur == null) {
                        keep.next = null;
                        break OUT;
                    }
                }
                pre = cur;
                cur = cur.next;
            } else {
                keep.next = pre;
                keep = pre;
                pre = cur;
                cur = cur.next;
            }
            if (cur == null) {
                keep.next = pre;
                keep = pre;
                keep.next = null;
                break;
            }
        }
        return dummy.next;
    }
}
