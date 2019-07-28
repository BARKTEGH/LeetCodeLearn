package leetcode;

import java.util.ArrayList;
import java.util.List;

public class No257 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val=x;}
    }
    public List<String> binaryTreePaths(TreeNode root) {

        List<String> pathList = new ArrayList<>();

        List<Integer>  path = new ArrayList<>();
        path.add(root.val);
        dfs(pathList,path,root);
        return pathList;
    }

    public void dfs(List<String> pathList,List<Integer>  path,TreeNode curNode){
        if(curNode!=null&&curNode.left==null&&curNode.right==null){
            StringBuffer stringBuffer = new StringBuffer();
            for(int val:path){
                stringBuffer.append(String.valueOf(val));
                stringBuffer.append("->");
            }
            stringBuffer.delete(stringBuffer.length()-2,stringBuffer.length()-1);
            System.out.println(stringBuffer);
            pathList.add(stringBuffer.toString());
            return;
        }
        if(curNode.left!=null){
            path.add(curNode.left.val);
            dfs(pathList,path,curNode.left);
            path.remove(path.size()-1);
        }
        if(curNode.right!=null){
            path.add(curNode.right.val);
            dfs(pathList,path,curNode.right);
            path.remove(path.size()-1);
        }
    }

}
