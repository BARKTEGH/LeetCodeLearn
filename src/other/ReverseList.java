package other;

public class ReverseList {

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }

        /**
         * 遍历，将当前节点的下一个节点缓存后更改当前节点指针
         */
        public static void reverse2(ListNode head, int m, int n) {

            //m<n
            int count = 1;
            ListNode temp = head;
            ListNode tail_1 = null;
            ListNode head_2 = null;
            ListNode tail_2 = null;
            ListNode head_3 = null;
            while (temp != null) {
                if (count == m - 1) {
                    tail_1 = temp;
                }
                if (count == m) {
                    head_2 = temp;
                }
                if (count == n) {
                    tail_2 = temp;
                }
                if (count == n + 1) {
                    head_3 = temp;
                }
                count++;
                temp = temp.next;
            }
            if(tail_1!=null) tail_1.next = null;
            tail_2.next = null;


            if (head_2 == null)
                return ;
            ListNode pre = head_2;// 上一结点
            ListNode cur = head_2.next;// 当前结点
            ListNode tmp;// 临时结点，用于保存当前结点的指针域（即下一结点）
            while (cur != null) {// 当前结点为null，说明位于尾结点
                tmp = cur.next;
                cur.next = pre;// 反转指针域的指向
                // 指针往下移动
                pre = cur;
                cur = tmp;
            }
            // 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
            head_2.next = head_3;
            if(tail_1!=null) {tail_1.next = pre;}
            else {
                head = pre;
            }
            while(head!=null){
                System.out.println(head.val);
                head = head.next;
            }

    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        reverse2(listNode1,2,7);
    }

}
