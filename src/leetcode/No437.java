package leetcode;

import java.util.HashMap;
import java.util.Map;

public class No437 {

    /**
     * 双重递归，一个用来遍历树，一个用来遍历节点往下
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if(root==null) return 0;
        return pathSum(root.left,sum)+pathSum(root.right,sum)+pathSumStartWithRoot(root,sum);
    }

    public int pathSumStartWithRoot(TreeNode root,int sum){
        int res=0;
        if(root==null) return 0;
        if(root.val==sum) res++;
        return pathSumStartWithRoot(root.left,sum-root.val)+pathSumStartWithRoot(root.right,sum-root.val);
    }

     public int pathSum2(TreeNode root, int sum) {
        if(root==null) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        return dfs(root,sum,0,map);
    }

    public int dfs(TreeNode node, int sum,int pathSum, Map<Integer,Integer> map){
        int res = 0;
        if(node==null)return 0;
        pathSum += node.val;
        //计算累计和之间刚好差为sum的路径树
        res += map.getOrDefault(pathSum-sum,0);
        map.put(pathSum,map.getOrDefault(pathSum,0)+1);
        res = dfs(node.left,sum,pathSum,map)+dfs(node.right,sum,pathSum,map)+res;
        map.put(pathSum,map.get(pathSum)-1);
        return res;

    }





}
