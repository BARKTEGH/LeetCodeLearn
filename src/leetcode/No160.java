package leetcode;

public class No160 {
      public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
         next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null){return null;}
        ListNode preA = headA,preB = headB;
        /**
        定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)
        两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度
        **/
         // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
        while(preA!=preB){
            preA = preA==null?headB:preA.next;
            preB = preB==null?headA:preB.next;
        }
        return preA;
    }
}
