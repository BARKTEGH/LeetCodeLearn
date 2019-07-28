package other;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TreeAll {

    static List<Integer> list = new ArrayList<>();
    public void inOrder(TreeNode root){
        if(root==null) return ;
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }

    public List<Integer> inOrder1(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        TreeNode tmp = root;
        Stack<TreeNode> stack = new Stack<>();
        while(tmp!=null||!stack.isEmpty()){
            while(tmp!=null){
                stack.push(tmp);
                tmp = tmp.left;
            }
            tmp = stack.pop();
            res.add(tmp.val);
            tmp =tmp.right;
        }
        return res;
    }

    public void preOrder(TreeNode root){
        if(root==null) return;
        list.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public List<Integer> preOrder1(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode tmp;
        while(!stack.isEmpty()){
            tmp=stack.pop();
            res.add(tmp.val);
            if(tmp.right!=null)stack.push(tmp.right);
            if(tmp.left!=null)stack.push(tmp.left);
        }
        return res;
    }

    public void postOrder(TreeNode root){
        if(root==null) return;
        postOrder(root.left);
        postOrder(root.right);
        list.add(root.val);
    }
    public List<Integer> postOrder1(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode tmp;
        while(!stack.isEmpty()){
            tmp=stack.pop();
            res.add(tmp.val);
            if(tmp.left!=null)stack.push(tmp.left);
            if(tmp.right!=null)stack.push(tmp.right);
        }
        Collections.reverse(res);
        return res;
    }





    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(4);
        TreeNode root3 = new TreeNode(0);
        TreeNode root4 = new TreeNode(3);
        TreeNode root5 = new TreeNode(5);
        root.left = root1;
        root.right = root2;
        root1.left = root3;
        root2.left = root4;
        root2.right = root5;
        TreeAll treeAll = new TreeAll();
        treeAll.inOrder(root);
        System.out.println("中序遍历:"+list);
        treeAll.preOrder(root);
        System.out.println("前序遍历:"+list);
        treeAll.postOrder(root);
        System.out.println("后序遍历"+list);

        System.out.println("非递归的中序遍历:"+treeAll.inOrder1(root));
        System.out.println("非递归的前序遍历:"+treeAll.preOrder1(root));
        System.out.println("非递归后序遍历:"+treeAll.postOrder1(root));


    }
}
