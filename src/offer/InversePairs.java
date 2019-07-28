package offer;

public class InversePairs {
    private int cnt = 0;
    private int[] tmp;

    public int inverseParirs1(int[] nums){
        tmp = new int[nums.length];
        mergeSort(nums,0,nums.length-1);
        return (int) (cnt%1000000007);
    }

    public void mergeSort(int[] nums,int l,int h){
        if(h-l<1){
            return;
        }
        int m = l +(h-l)/2;
        mergeSort(nums,l,m);
        mergeSort(nums,m+1,h);
        merge(nums,l,m,h);
    }

    public void merge(int[] nums,int l,int m,int h){
        int i=l,j = m+1,k=l;
        while(i<=m||j<=h){
            if(i>m){
                tmp[k] = nums[j++];
            }else if(j>h){
                tmp[k] = nums[i++];
            }else if(nums[i]<=nums[j]){
                tmp[k] = nums[i++];
            }else {
                tmp[k] = nums[j++];
                cnt +=  m-i+1;
                // nums[i] > nums[j]，说明 nums[i...mid] 都大于 nums[j]
            }
            k++;
        }
        //将排序后结果保存到原数组
        for(k =l;k<=h;k++){
            nums[k] = tmp[k];
        }
    }

    public static void main(String[] args) {
        InversePairs inversePairs = new InversePairs();
        int[] nums = {1,2,3,4,5,6,7,0};
        System.out.println(inversePairs.inverseParirs1(nums));
    }
}
