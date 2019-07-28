package other;

import javafx.util.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

public class QuickSort {

    void quickSort(int[] nums,int l,int h) {
        if (l < h) {
            int j = partition(nums, l, h);
            quickSort(nums, l, j - 1);
            quickSort(nums, j + 1, h);
        }
    }



    private int partition(int[] nums, int l, int h) {
        int i = l, j = h + 1;
        while (true) {
            while (nums[++i] < nums[l] && i < h) ;
            while (nums[--j] > nums[l] && j > l) ;
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }





    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test(){
        int[] nums= {1,23,4,5,23,5,65,7,128,89,129};
        quickSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
        int x = 1;
        System.out.println(5&4);





    }

}
