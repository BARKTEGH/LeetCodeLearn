package offer;

import leetcode.ListNode;

public class DeleteDuplication {
    public void deleteDuplication(ListNode head){

        ListNode h = head, pre = new ListNode(0);
        pre.next = h;
        ListNode post = null;

        while(h != null && (post = h.next) != null){
            if(h.val!=post.val){
                pre = h;
            }else {
                // post 不断向后移动
                while(post != null && h.val == post.val) {
                    post = post.next;
                }
                if(pre.next == head) {
                    // 如果是从头结点开始就一直重复
                    // 注意，如果从头结点就开始重复，需要修改head
                    head = post;
                }
                pre.next = post;
            }
            h = post;

        }
    }
}
