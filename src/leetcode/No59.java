package leetcode;/*
给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

示例:

输入: 3
输出:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */

/*
在54螺旋矩阵的情况下，将添加改为赋值，并添加一个计数器，来计算当前保存的数
 */

public class No59 {

     public int[][] generateMatrix(int n) {
        if(n==1) return new int[][]{{1}};
        int[][] list = new int[n][n];
        int left = 0;
        int down = n-1;
        int right = n-1;
        int up = 0;
        int k=1;
        while (up <= down && left <= right) {
            k = cround(list,k,up,down,left,right);
            up++;down--;left++;right--;
        }
        return list;

    }

    private int cround(int[][] list,int k,int up,int down,int left,int right){
        if(up==down){
            for (int i=left;i<=right;i++){
                list[up][i] =k ;
                k++;
            }
        }else if(left==right){
            for (int i=up;i<=down;i++){
                list[i][left] = k;
                k++;
            }
        }else {
            for(int i=left;i<=right;i++) {list[up][i]=k;k++;}
            for(int i=up+1;i<=down;i++) {list[i][right]=k;k++;}
            for(int i=right-1;i>=left;i--) {list[down][i]=k;k++;}
            for(int i=down-1;i>=up+1;i--) {list[i][left]=k;k++;}
        }
        return k;
    }
}
