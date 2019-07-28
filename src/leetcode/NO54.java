package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NO54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 0) return list;
        int left = 0;
        int down = matrix.length;
        int right = matrix[0].length - 1;
        int up = 0;

        while (up <= down && left <= right) {
            cround(list,matrix,up,down,left,right);
            up++;down--;left++;right--;
        }
        return list;

    }

    private void cround(List<Integer> list,int[][] matrix,int up,int down,int left,int right){
        if(up==down){
            for (int i=left;i<=right;i++){
                list.add(matrix[up][i]);
            }
        }else if(left==right){
            for (int i=up;i<=down;i++){
                list.add(matrix[i][left]);
            }
        }else {
            for(int i=left;i<=right;i++) list.add(matrix[up][i]);
            for(int i=up+1;i<=down;i++) list.add(matrix[i][right]);
            for(int i=right-1;i>=left;i--) list.add(matrix[down][i]);
            for(int i=down-1;i>=up+1;i--) list.add(matrix[i][left]);
        }
    }


}
