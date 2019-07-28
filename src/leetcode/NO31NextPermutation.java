package leetcode;

import org.junit.Test;

import java.util.Arrays;

/*
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */
public class NO31NextPermutation {
    //从最后一位开始检查，比较前一位那个大，知道前位比后卫小
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if(len==1){
            return;
        }
        int i =len-1;
        boolean flag = false;
        int temp1;
        while(i>0){
            temp1 = nums[i-1];
            if(temp1 < nums[i]){
                flag = true;
                int temp2 = 0;
                int endI = len-1;
                for(int j=i ;j<=len;j++){
                    if(j > endI){
                        break;
                    }
                    temp2 = nums[j];
                    nums[j] = nums[endI];
                    nums[endI] = temp2;
                    endI -=1;
                }
                int temp = nums[len-1];
                int k = i;
                for(int j= i;j<len;j++){
                    if(nums[j] > nums[i-1] && nums[j] <=temp){
                        temp = nums[j];
                        k = j;
                        break;
                    }
                }
                temp2 = nums[i-1];
                nums[i-1] = temp;
                nums[k] = temp2;
                break;
            }
            i -=1;
        }
        if(flag == false) {
            for(int j=0;j<len/2;j++){
                temp1 = nums[j];
                nums[j] = nums[len-1-j];
                nums[len-1-j] = temp1;
            }
        }
        System.out.println(Arrays.toString(nums));
        return;
    }

    public void sortnums(int[] num){
        int len = num.length;
        int temp = num[1];
        int k = 0;
        int temp2= 0;
        for(int i=1;i<=len/2;i++){
            temp2 = num[i];
            num[i] = num[len-i];
            num[len-i] = temp2;
            if(num[i]>num[0] && num[i]<temp){
                temp = num[i];
                k = i;
            }
            if(num[len-i]>num[0] && num[len-i]<temp){
                temp = num[len-i];
                k = len-i;
            }
        }
        temp2 = num[0];
        num[0] = temp;
        num[k] = temp2;
        return;
    }

    @Test
    public void test(){
        int[] nums = {1,3,2};
        nextPermutation(nums);
        System.out.println(nums);
    }
}
