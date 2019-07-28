package leetcode;

import sun.awt.TracedEventQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No107 {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val=x;}
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if(root==null) return lists;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                temp.add(node.val);
                if(root.left!=null)queue.offer(node.left);
                if(root.right!=null)queue.offer(node.right);
            }
            lists.add(0,temp);
        }
        return lists;
    }


}
