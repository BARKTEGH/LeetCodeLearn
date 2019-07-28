package leetcode;

public class No543 {
        private int max = 0;
        public int diameterOfBinaryTree(TreeNode root){
            depth(root);
            return max;

        }

        private int depth(TreeNode root){
            if(root==null) return 0;
            int l = depth(root.left);
            int r = depth(root.right);
            max = Math.max(max,l+r);
            return Math.max(l,r)+1;
        }
}
