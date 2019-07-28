package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class No98 {
    /**
     * 中序遍历二叉搜索术后为升序
     */
    public boolean isValidBST(No257.TreeNode root) {
        double pre = -Double.MAX_VALUE;
        Stack<No257.TreeNode> stack = new Stack<>();
        No257.TreeNode temp = root;
        while(temp!=null || !stack.isEmpty()){
            while(temp!=null){
                stack.push(temp);
                temp = temp.left;
            }
            if(!stack.isEmpty()){
                temp = stack.pop();
                if(temp.val<=pre) return false;
                pre = temp.val;
                temp =temp.right;
            }

        }
        Queue<String> objects = new LinkedList<>();
        objects.poll();

        return true;
    }

}
