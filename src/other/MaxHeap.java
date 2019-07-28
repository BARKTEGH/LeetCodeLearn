package other;

public class MaxHeap {

    private int[] data;

    public MaxHeap(int[] data){
        this.data = data;
        buildHeap();
    }

    private void buildHeap(){
        for(int i= (data.length)/2 -1;i>=0;i--){
            heapify(i);
        }
    }

    private void heapify(int i){
        int max = i;
        int left = ((i+1)<<1) -1;
        int right = (i+1)<<1;
        if(left<data.length&&data[left]>data[max]){
            max = left;
        }
        if(right<data.length&&data[right]>data[max]){
            max = right;
        }
        if(max == i){
            return;
        }

        int temp = data[i];
        data[i] = data[max];
        data[max] = temp;

        heapify(max);
    }

    public int getRoot(){
        return data[0];
    }

    public void setRoot(int root){
        data[0] =root;
        heapify(0);
    }
}
