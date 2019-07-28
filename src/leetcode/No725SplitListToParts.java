package leetcode;

/**
 *
 */

public class No725SplitListToParts {

    public ListNode[] splitListToParts(ListNode root, int k) {

        ListNode[] res = new ListNode[k];
        int len = 0;
        ListNode tmp =root;
        while (tmp!=null){
            len++;
            tmp=tmp.next;
        }



        tmp = root;
        if (k >= len) {
            for (int i = 0; i < len; i++) {
                res[i] = new ListNode(tmp.val);
                tmp = tmp.next;
            }
        }else {
             //2. k < 总长度，
            int remain = len % k;
            int preCount = len / k;
            //记录每部分需要储存多少个链表元素
            int[] counts = new int[k];
            for (int i = 0; i < k; i++) {
                counts[i] = remain-- > 0 ? preCount + 1 : preCount;
            }
            //遍历链表，储存元素
            for (int i = 0; i < k; i++) {
                //获取当前部分需要的元素个数
                int num = counts[i];
                res[i] = tmp;
                //调整p的位置
                while (--num > 0) {
                    tmp = tmp.next;
                }
                //截断链表
                ListNode tmp2 = tmp.next;
                tmp.next = null;
                tmp = tmp2;
            }

        }
        return res;


    }
}
