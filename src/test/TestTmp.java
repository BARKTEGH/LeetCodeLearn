package test;

import java.util.Arrays;

public class TestTmp {

    public int test(int[] num,int l,int r,int n){
        if(l>r) return  -1;
        if(l==r) return num[l] == n?l:-1;
        int mid = l+(r-l)/2;
        if(num[mid]==n) return mid;
        if(mid>0&&num[mid]>num[mid-1]){
            int i = partion(num,l,mid-1,n);
            if(num[i]==n) return i;
            return test(num,mid+1,r,n);
        }else {
            int i = partion(num,mid+1,r,n);
            if(num[i]==n) return i;
            return test(num,l,mid-1,n);
        }

    }

    public int partion(int[] num,int l,int r,int n){
        while(l<r){
            int mid = l+(r-l)/2;
            if(num[mid]==n) return mid;
            if(num[mid]>n) {
                r = mid;
            }else {
                l =mid+1;
            }
        }
        return l;
    }

    //快速选择
    public int parttition(int[] num,int l,int h){
        int i=l,j=h+1;
        while(true){
            while (num[++i] <num[l]&&i<h);
            while (num[--j]>num[l]&&j>l);
            if(i>=j) break;
            swap(num,i,j);
        }
        swap(num,l,j);
        System.out.println(Arrays.toString(num));
        return j;
    }

    public void swap(int[] num,int i,int j){
        int tmp=num[i];
        num[i] = num[j];
        num[j] = tmp;
    }

    public void quickSort(int[] num,int l,int h){
        if(l<h){
            int j= parttition(num,l,h);
            quickSort(num,l,j-1);
            quickSort(num,j+1,h);
        }
    }

    /**
     * 找出第k个大的数字
     * @param args
     */

    public  int findKth(int[] nums,int k){

        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = parttition(nums, l, h);
            if (j == k) {
                break;
            } else if (j < k) {
                l = j + 1;
            } else {
                h = j - 1;
            }
        }
        return nums[k];
    }

    public static void main(String[] args) {
        TestTmp testTmp = new TestTmp();
        int[] nums = {4, 23, 5, 1, 5, 23, 89, 65, 129, 128, 7};
        System.out.println(Arrays.toString(nums));
//        testTmp.quickSort(nums,0,nums.length-1);
//        System.out.println(testTmp.findKth(nums,6));
//        System.out.println(testTmp.mergeSort(nums));
        testTmp.heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 归并排序,空间复杂度较高
     */
    public int[] mergeSort(int[] array){
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    public int[] merge(int[] l1,int[] l2){
        int[] result = new int[l1.length + l2.length];
        int index1=0,index2=0;
        for(int i=0;i<result.length;i++){
            if(index1>=l1.length){
                result[i] = l2[index2++];
            } else if(index2>=l2.length){
                result[i] = l1[index1++];
            }else if(l1[index1]<=l2[index2]){
                    result[i] = l1[index1++];
            }else {
                    result[i] = l2[index2++];
            }
        }
        return result;
    }


   //堆排序

    private int[] data;

    public void buildHeap(int k){
        for(int i=k/2-1;i>=0;i--){
            heapfy(i,k);
        }
    }

    public void heapfy(int i,int len){
        int left =((i+1)<<1)-1;
        int right = (i+1)<<1;
        int samll = i;
        if(left<len&&data[left]>data[samll]) samll = left;
        if(right<len&&data[right]>data[samll]) samll = right;
        if(samll==i) return;
        int tmp = data[samll];
        data[samll] = data[i];
        data[i] = tmp;
        heapfy(samll,len);
    }

    public void heapSort(int[] datas){
        this.data= datas;
        for(int i=data.length;i>0;i--){
            buildHeap(i);
            int tmp = data[i-1];
            data[i-1] = data[0];
            data[0] = tmp;
        }
    }


}
