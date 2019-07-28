package leetcode;

import java.util.Stack;

/**
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 */
public class No234IsPalindrome {
    /**
     * 使用栈来保存链表翻转，空间复杂度o(n)
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
       int count=0;
        Stack<Integer> stack=new Stack<>();
        ListNode n=head;
        while (n!=null){
            stack.push(n.val);
            n=n.next;
            count++;
        }
        for (int i = 1; i <=count/2 ; i++) {
            if (head.val!=stack.peek()){
                return false;
            }
            else {
                head=head.next;
                stack.pop();
            }
        }
        return true;
    }

    /**
     * 从中间断开链表，翻转来比较
     * 空间复杂度0(1)
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head){
        if(head==null) return true;

        ListNode slow =head;
        ListNode fast =head.next;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode right = slow.next;
        slow.next = null;

        ListNode rightR = reverse(right);

        while(rightR!=null){
            if(rightR.val!=head.val){
                return false;
            }else {
                rightR = rightR.next;
                head = head.next;
            }
        }
        return true;
    }

    private  ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode tmp = head;
        while (tmp!=null){
            ListNode next = tmp.next;
            tmp.next = pre;
            pre = tmp;
            tmp = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,2,1};
        ListNode head = Utils.newList(nums);
        Utils.printList(head);

        No234IsPalindrome palindrome = new No234IsPalindrome();
        System.out.println(palindrome.isPalindrome2(head));
    }
}
