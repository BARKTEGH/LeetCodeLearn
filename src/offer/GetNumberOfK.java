package offer;

public class GetNumberOfK {
    public int getNumberOfK(int [] array , int k){
        int first = binarySearch(array, k);
        int last = binarySearch(array, k + 1);
        return (first == array.length || array[first] != k) ? 0 : last - first;
    }

    public int binarySearch(int[] array,int k){
        int l =0 ;
        int h = array.length;
        while(l<h){
            int m = l+(h-l)/2;
            if(array[m]>=k){
                h=m;
            }else {
                l=m+1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        GetNumberOfK getNumberOfK = new GetNumberOfK();
        int[] nums = {1, 2, 3, 3, 3, 3, 4, 6};
        System.out.println(getNumberOfK.getNumberOfK(nums,4));
    }
}
