package leetcode;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


public class No61RotateRight {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)
            return null;
        //先快慢指针走k步，找到需要旋转的节点前一个节点，断开，然后将头结点与尾结点连接起来
        //优化，先统计链表长度，对k取模，防止k太大在不停旋转
        ListNode fast = head;
        int len = 0;
        while(fast!=null){
            len++;
            fast = fast.next;
        }

        k = k%len;
        fast = head;
        while(k-->0){
            fast = fast.next;
            if(fast == null){
                fast = head;
            }
        }
        ListNode slow = head;
        while(fast.next!=null){
            fast =fast.next;
            slow = slow.next;
        }


        if(slow.next==null){
            return head;
        }
        ListNode nHead = slow.next;
        slow.next = null;
        slow = nHead;
        while(slow.next!=null){
            slow=slow.next;
        }
        slow.next = head;
        return nHead;
    }

    public static void main(String[] args) {
        int[] list = {1,2,3};
        ListNode head = Utils.newList(list);
        Utils.printList(head);
        No61RotateRight no61RotateRight = new No61RotateRight();
        ListNode nHead = no61RotateRight.rotateRight(head,4);
        Utils.printList(nHead);
    }
}
