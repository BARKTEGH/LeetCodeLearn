package other;

public class Heap {

    /**
     * 堆的存储结构--数组
     */
    private int[] data;

    public Heap(int[] data){
        this.data = data;
        buildHeap();
    }

    private void buildHeap(){
        for(int i=(data.length) /2 -1 ; i>=0;i--){
            heapify(i);
        }
    }

    private void heapify(int i){
        //临时变量，存放左右节点中值最小的索引
        int samllindex = i;
        // 左右节点的索引
        int right = (i+1)<<1;
        int left = ((i + 1) << 1) - 1;

        // 存在左结点，且左结点的值小于根结点的值
        if(left<data.length && data[left]<data[samllindex]){
            samllindex = left;
        }

        // 存在右结点，且右结点的值小于以上比较的较小值
        if(right<data.length && data[right]<data[samllindex]){
            samllindex = right;
        }

        // 左右结点的值都大于根节点，直接return，不做任何操作
        if(i==samllindex){
            return;
        }

        // 交换元素位置
        sawp(i,samllindex);

        // 由于替换后左右子树会被影响，所以要对受影响的子树再进行heapify
        heapify(samllindex);

    }

    private void sawp(int i,int j){
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     *获取对中的最小的元素，根元素
     */
    public int getRoot()
    {
    	    return data[0];
    }


    /**
    * 替换根元素，并重新heapify
     */
	public void setRoot(int root)
	{
		data[0] = root;
		heapify(0);
	}




}
