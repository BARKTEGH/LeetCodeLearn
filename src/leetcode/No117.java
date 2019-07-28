package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class No117 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }
        public Node(int _val){
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    //无法通过
    public Node connect(Node root) {
        if(root==null) return root;

        LinkedList<Node> list = new LinkedList<>();
        list.add(root);
        Node temp,preNode=null;

        while(!list.isEmpty()){
            int size = list.size();
            preNode = list.pollFirst();
            if(preNode.left!=null) list.addLast(preNode.left);
            if(preNode.right!=null) list.addLast(preNode.right);
            for(int i=1;i<size;i++){
                temp = list.pollFirst();
                preNode.next = temp;
                preNode = temp;
                if(temp.left!=null) list.addLast(temp.left);
                if(temp.right!=null) list.addLast(temp.right);
            }
        }
        return root;
    }

    @Test
    public void test(){
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        root.left = node2;
        root.right = node3;
        connect(root);


    }




    //正确版
    LinkedList<Node>list=new LinkedList<>();
    void getNode(Node root,int i,int h){
        if(i<h&&root!=null){
            getNode(root.left,i+1,h);
            getNode(root.right,i+1,h);
        }else if(i==h&&root!=null)
            list.addLast(root);
    }
    public Node connect2(Node root) {
        int i=0;
        Node pre=null,p=null;
        while(i==0||!list.isEmpty()){
            if(i==0)list.addLast(root);
            pre=list.pollFirst();
            while(!list.isEmpty()){
                p=list.pollFirst();
                pre.next=p;
                pre=p;
            }
            getNode(root,0,++i);
        }
        return root;
    }
}

