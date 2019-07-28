package offer;

public class reConstructBinaryTree {
    public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root = reConstructBinaryTree2(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }

    private TreeNode reConstructBinaryTree2(int[] pre,int startPre,int endPre,
                                            int[] in,int startIn,int endIn){
        if(startPre>endPre||startIn>endIn) return null;
        TreeNode root =new  TreeNode(pre[startPre]);
        for(int i= startIn;i<=endIn;i++){
            if(in[i] == pre[startPre]){
                root.left = reConstructBinaryTree2(pre,startPre+1,startPre+i-startIn,
                        in,startIn,i-1);
                root.right = reConstructBinaryTree2(pre,startPre+i-startIn+1,endPre,
                        in,i+1,endIn);
                break;
            }
        }
        return root;

    }
}
