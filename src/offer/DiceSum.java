package offer;

import java.util.*;

/**
 * 题目描述
 * 把 n 个骰子仍在地上，求点数和为 s 的概率。
 */
public class DiceSum {
    private HashMap<Integer,Double> map = new HashMap<>();
    private int countAll = 0;
    public List<Map.Entry<Integer, Double>> dicesSum(int n){
        backracking(n,0);
        ArrayList<Map.Entry<Integer, Double>> lists = new ArrayList<>();
        int all = 0;
        for(Map.Entry<Integer,Double> entry: map.entrySet()){
            entry.setValue(entry.getValue()/countAll);
            lists.add(entry);
        }
        return  lists;
    }

    /**
     * 采用回溯保存每个结果出现的次数与总数
     * @param n
     * @param sum
     */
    public void backracking(int n,int sum){
        if(n==0) {
            map.put(sum,map.getOrDefault(sum,0.0)+1);
            countAll++;
            return;
        }
        for(int i=1;i<=6;i++){
            backracking(n-1,sum+i);
        }
    }


    /**
     * 动态规划，//dp[i][j] = dp[i-1][j-1]+dp[i-1][j-2]+...+dp[i-1][j-face]
     * 时间复杂度o（n^2）
     * @param n
     * @return
     */
    public List<Map.Entry<Integer, Double>> dicesSum2(int n){
        ArrayList<Map.Entry<Integer, Double>> lists = new ArrayList<>();
        int face = 6;
        int pointNum = n*face;
        double totalNum = Math.pow(face,n);
        int[][] dp = new int[n+1][pointNum+1];
        //dp[i][j] = dp[i-1][j-1]+dp[i-1][j-2]+...
        for(int i=1;i<=face;i++){
            dp[1][i]=1;
        }

        for(int i=2;i<=n;i++){
            for(int j=i;j<=pointNum;j++){
                for(int k=1;k<=face&&k<j;k++){
                    dp[i][j] += dp[i-1][j-k];
                }
            }
        }


        for(int i=n;i<=pointNum;i++){
            lists.add(new AbstractMap.SimpleEntry<>(i,dp[n][i]/totalNum));
        }
        return lists;
    }


    public static void main(String[] args) {
        DiceSum diceSum = new DiceSum();
//        System.out.println(diceSum.dicesSum(1));
        System.out.println(diceSum.dicesSum2(2));
    }

}
