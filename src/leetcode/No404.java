package leetcode;

import java.util.Collections;
import java.util.Scanner;

public class No404 {

     public int sumOfLeftLeaves(TreeNode root) {
        if(root==null) return 0;
        if(isLeaf(root.left)) return root.left.val + sumOfLeftLeaves(root.right);
        return sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);
    }

    public boolean isLeaf(TreeNode root){
         return root!=null&&root.left==null&&root.right==null;
    }

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.reverse();
    }
}
