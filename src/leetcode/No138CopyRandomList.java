package leetcode;

public class No138CopyRandomList {

    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }


    public Node copyRandomList(Node head) {

        if (head == null) {
          return null;
        }

        Node tmp  = head;
        Node next = head;
        //在每个节点后面添加一个节点
        while(tmp!=null){
            next = tmp.next;
            Node nNode = new Node(tmp.val,next,null);
            tmp.next = nNode;
            tmp = next;
        }

        //赋予随机指针
        tmp = head;
        while (tmp != null) {
             tmp.next.random = tmp.random!=null?tmp.random.next:null;
             tmp = tmp.next.next;
        }

        tmp = head;
        Node nHead = head.next;
        Node tmp2 = nHead;

        //拆分出两个链表
        while(tmp!=null){
            tmp.next = tmp.next.next;
            tmp2.next =  (tmp2.next!=null)  ?tmp2.next.next:null;
            tmp = tmp.next;
            tmp2 = tmp2.next;

        }

        return nHead;

    }
}
