package leetcode;

public class No105 {


 public class TreeNode {
    int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

    public TreeNode buildTree(int[] preorder, int[] inorder) {


        TreeNode root = buildTreeSub(preorder,0,preorder.length-1,inorder,0,preorder.length-1);
        return root;
    }

    public TreeNode buildTreeSub(int[] preorder,int start_pre,int end_pre,int[] inorder,int start_in,int end_in){
        if(start_pre>end_pre||start_in>end_in) return null;
        TreeNode root = new TreeNode(preorder[start_pre]);
        int rootIndexInorde = findrootInoredIndex(preorder,start_pre,end_pre,inorder,start_in,end_in);
        root.left = buildTreeSub(preorder,start_pre+1,start_pre+rootIndexInorde-start_in,inorder,start_in,rootIndexInorde-1);
        root.right = buildTreeSub(preorder,start_pre+rootIndexInorde-start_in+1,end_pre,inorder,rootIndexInorde+1,end_in);
        return root;
    }

    public int findrootInoredIndex(int[] preorder,int start_pre,int end_pre,int[] inorder,int start_in,int end_in){
            for(int i=start_in;i<=end_in;i++){
                if(preorder[start_pre] == inorder[i]){
                    return i;
                }
            }
            return start_pre;
    }

}
