package leetcode;

public class No112 {
     public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) return false;
        if(root.val == sum&& isLeaf(root)) return true;
        return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);
    }

    private boolean isLeaf(TreeNode root){
        return root!=null&&root.left==null&&root.right==null;
    }
}
