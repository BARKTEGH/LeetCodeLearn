package leetcode;



import java.util.LinkedList;
import java.util.List;


public class No95 {
    public class TreeNode{
        int val;
        TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }

    public List<TreeNode> generateTrees(int n) {
        if(n<1){
            return new LinkedList<TreeNode>();
        }
        return generateSubtrees(1, n);
        
    }

    public List<TreeNode>  generateSubtrees(int start,int end){
        List<TreeNode> treeNodes = new LinkedList<>();
        if(start>end){
            treeNodes.add(null);
            return treeNodes;
        }
        for(int i=start;i<=end;i++){
            List<TreeNode> leftTrees = generateSubtrees(start, i - 1);
            List<TreeNode> rightTrees = generateSubtrees(i+1, end);
            for(TreeNode left:leftTrees){
                for(TreeNode right:rightTrees){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    treeNodes.add(root);
                }
            }
        }

        return treeNodes;
    }

    public static void main(String[] args) {
        No95 no95 = new No95();
        List<TreeNode>  lists = no95.generateTrees(3);

    }
}
