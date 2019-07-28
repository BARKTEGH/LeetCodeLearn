package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

public class No23 {
    /**
     * 使用堆
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        Queue<ListNode> queue = new PriorityQueue<>((a,b)->a.val-b.val);
        for(int i=0;i<k;i++){
            queue.offer(lists[i]);
        }
        
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        while(!queue.isEmpty()){
            ListNode min = queue.poll();
            tmp.next = min;
            tmp = tmp.next;
            if(min.next!=null) queue.offer(min.next);
        }
        return head.next;
    }


    public ListNode merge2List(ListNode la,ListNode lb){
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        while (la!=null&&lb!=null){
            if(la.val<=lb.val){
                tmp.next = la;
                la = la.next;
            }else {
                tmp.next = lb;
                lb = lb.next;
            }
            tmp = tmp.next;
        }
        if(la!=null){
            tmp.next = la;
        }
        if(lb!=null){
            tmp.next = lb;
        }
        return head.next;
    }

    public ListNode mergeKlist2(ListNode[] lists){
        int n=lists.length;
        if(lists.length==0)
            return null;
        while(n>1){
            //System.out.println(n);
            for(int i=0;i<n/2;i++){
                lists[i]=merge2List(lists[2*i],lists[2*i+1]);
            }
            if(n%2==1){
                lists[n/2]=lists[n-1];
            }
            n=n-n/2;
           // System.out.println("t "+n);
        }
        return lists[0];
    }
}
