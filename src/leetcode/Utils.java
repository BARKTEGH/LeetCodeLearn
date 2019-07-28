package leetcode;

import java.util.ArrayList;

public class Utils {
    public static ListNode newList(int[] nums){
        ListNode head = new ListNode(-1);
        ListNode tmp = head;
        for(int i=0;i<nums.length;i++){
            ListNode node = new ListNode(nums[i]);
            tmp.next = node;
            tmp = node;
        }
        return head.next;
    }


    public static void  printList(ListNode head){
        ArrayList<Integer> list = new ArrayList<Integer>();
        while(head!=null){
            list.add(head.val);
            head = head.next;
        }
        System.out.println(list.toString());
    }


}
