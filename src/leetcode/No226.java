package leetcode;

public class No226 {
     public TreeNode invertTree(TreeNode root){
         if(root==null) return null;
         TreeNode tmp = root.left;
         root.left = invertTree(root.right);
         root.right = invertTree(tmp);
         return root;
     }
}
