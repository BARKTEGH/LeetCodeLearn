package other;

import java.util.ArrayList;
import java.util.Arrays;

public class HeapSort {

    private int[] data;

    public HeapSort(int[] nums){
        this.data = nums;
    }

    public void buildHeap(int len){
        for(int i=len/2 -1;i>=0;i--){
            heapify(i,len);
        }
    }

    public void heapify(int index,int len){
        // 左右节点的索引
        int right = (index+1)<<1;
        int left = ((index + 1) << 1) - 1;
        int samllindex = index;
        if(left<len&&data[left]>data[samllindex]){
            samllindex = left;
        }
        if(right<len&&data[right]>data[samllindex]){
            samllindex = right;
        }
        if(samllindex==index) return;
        int tmp = data[index];
        data[index] = data[samllindex];
        data[samllindex] = tmp;
        heapify(samllindex,len);
    }

    public int[] heapSort(){
        for(int i=data.length;i>1;i--){
            buildHeap(i);
            int tmp = data[i-1];
            data[i-1] = data[0];
            data[0] = tmp;
        }
        return data;
    }


    public static void main(String[] args) {
        int[] nums = {3,4,2,1};
        HeapSort heapSort = new HeapSort(nums);
        System.out.println(Arrays.toString(heapSort.heapSort()));
    }
}
