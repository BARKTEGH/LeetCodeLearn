package offer;

import leetcode.TreeNode;

import java.util.Stack;

public class KthNode {
    /**
     * 给定一棵二叉搜索树，请找出其中的第k小的结点。
     * 中序遍历,递归写法
     */
    private int cnt=0;
    private TreeNode knode;
    public TreeNode KthNode(TreeNode pRoot, int k)
    {
        inOrder(pRoot,k);
        return knode;
    }

    public void inOrder(TreeNode root,int k){
        if(root==null||cnt>=k){
            return;
        }
        inOrder(root.left,k);
        cnt++;
        if(cnt==k){
            knode=root;
        }
        inOrder(root.right, k);
    }

    /**
     * 非递归
     */
    public TreeNode KthNode2(TreeNode pRoot, int k)
    {
        int cnt=0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp = pRoot;
        while(tmp != null || !stack.isEmpty()){
            while(tmp!=null){
                stack.add(tmp);
                tmp = tmp.left;
            }
            tmp = stack.pop();
            cnt++;
            if(cnt==k){
                return tmp;
            }
            tmp = tmp.right;
        }
        return null;
    }

}
