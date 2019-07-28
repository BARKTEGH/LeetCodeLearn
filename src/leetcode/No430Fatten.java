package leetcode;

import java.util.Stack;

public class No430Fatten {

    public class Node{
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(){}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    public Node flatten(Node head) {
        if(head==null) return head;

        Stack<Node> stack = new Stack<>();

        Node nHead = new Node(-1,null,null,null);
        Node nTmp  = nHead;
        Node tmp = head;

        while(tmp!=null || !stack.isEmpty()){
            //如果节点为空且栈不为空，意味子节点遍历完毕，弹出栈节点，进行上一层遍历
            if(tmp==null && !stack.isEmpty()){
                tmp = stack.pop();
            }

            Node nNode = new Node(tmp.val,null,null,null);
            nTmp.next = nNode;
            nNode.prev = nTmp;
            nTmp = nNode;
            System.out.println(nNode.val);

            //如果该节点存在子节点，那么在栈中保存该节点的next节点
            //转入子节点进行遍历
            if(tmp.child!=null){
                if(tmp.next!=null){
                    stack.add(tmp.next);
                }
                tmp = tmp.child;
            }else {
                tmp = tmp.next;
            }
        }

        //断开第一个节点的连接
        nHead.next.prev = null;
        return nHead.next;
    }
}
