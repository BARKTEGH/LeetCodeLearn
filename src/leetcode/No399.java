package leetcode;

import java.util.*;

public class No399 {

    private double[][] matrix ;
    private Map<String,Integer> store;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        floyd(equations, values);
        double[] res = new double[queries.size()];
        for(int i=0;i<queries.size();i++){
            if(store.containsKey(queries.get(i).get(0))&&store.containsKey(queries.get(i).get(1))){
                int a=store.get(queries.get(i).get(0));
                int b=store.get(queries.get(i).get(1));
                if(matrix[a][b]!=Double.MAX_VALUE)
                {
                    res[i]=matrix[a][b];
                }
                else
                {
                    res[i]=-1.0;
                }

            }else {
                res[i] = -1.0;
            }
        }

        return res;
    }


    /**
     * 使用floyd算法得到距离的邻接矩阵,主要解决如果queries数组过大，使用图+dfs需要每次重新寻找路径
     */
    public void floyd(List<List<String>> equations, double[] values){
        store = new HashMap<>();
        Set<String> set = new HashSet<>();
        for(int i=0;i<equations.size();i++){
            set.add(equations.get(i).get(0));
            set.add(equations.get(i).get(1));
        }
        matrix = new double[set.size()][set.size()];
        for(int i=0;i<set.size();i++){
            Arrays.fill(matrix[i],Double.MAX_VALUE);
        }
        //构造邻接矩阵
        int index=0;
        for(int i=0;i<equations.size();i++){
            if(!store.containsKey(equations.get(i).get(0))){
                store.put(equations.get(i).get(0),index++);

            }
            if(!store.containsKey(equations.get(i).get(1))){
                store.put(equations.get(i).get(1),index++);
            }
            matrix[store.get(equations.get(i).get(0))][store.get(equations.get(i).get(1))] = values[i];
            matrix[store.get(equations.get(i).get(1))][store.get(equations.get(i).get(0))]= 1/values[i];
        }

        for(int i=0;i<index;i++){
            for(int j=0;j<index;j++){
                for(int k=0;k<index;k++){
                    //以k中介点
                    if(matrix[i][k]!=Double.MAX_VALUE && matrix[k][j]!=Double.MAX_VALUE && matrix[i][j]==Double.MAX_VALUE){
                        matrix[i][j] = matrix[i][k]*matrix[k][j];
                    }
                }
            }
        }
    }



}
